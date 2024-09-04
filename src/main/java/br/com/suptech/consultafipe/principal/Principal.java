package br.com.suptech.consultafipe.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.suptech.consultafipe.models.DataInitialCalls;
import br.com.suptech.consultafipe.models.DataModelos;
import br.com.suptech.consultafipe.models.Vehicle;
import br.com.suptech.consultafipe.services.ApiCaller;
import br.com.suptech.consultafipe.services.DataConverter;

public class Principal {

    private static final String MODEL = " Model: ";
    private static final String CODIGO = "Codigo: ";
    private final Scanner scan = new Scanner(System.in);
    private final DataConverter dataConverter = new DataConverter();
    private String urlUsed = "";
    private List<Vehicle> vehicles;

    public void getMenuInicial() {
        String reply;
        do {
            System.out.println("""
                               ==========================
                               Digite uma das opcoes abaixo:
                               Carro
                               Moto
                               Caminhao
                               Sair
                               
                               """);
            reply = scan.nextLine();
            checkReply(reply);
        } while (!reply.equalsIgnoreCase("sair"));

    }

    private void checkReply(String reply) {

        switch (reply.toLowerCase()) {
            case "carro" ->
                carOption();
            case "moto" ->
                bikeOption();
            case "caminhao" ->
                truckOption();
            case "sair" -> {
            }
            default ->
                System.out.println("\n\n|| Opcao invalida " + reply + ".||\n||Por favor digite uma das opcoes disponiveis. ||\n\n");
        }

    }

    private void carOption() {
        String apiURLCarModel = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        urlUsed = apiURLCarModel;
        System.out.println("=================== Modelos de Carros ================");
        printDataList(apiURLCarModel);
        getBrandCode();

    }

    private void bikeOption() {
        String apiURLBikeModel = "https://parallelum.com.br/fipe/api/v1/motos/marcas";
        urlUsed = apiURLBikeModel;
        System.out.println("=================== Modelos de Motos ================");
        printDataList(apiURLBikeModel);
        getBrandCode();

    }

    private void truckOption() {
        String apiURLTruckModel = "https://parallelum.com.br/fipe/api/v1/caminhoes/marcas";
        urlUsed = apiURLTruckModel;
        System.out.println("=================== Modelos de Caminhoes ================");
        printDataList(apiURLTruckModel);
        getBrandCode();
    }

    private void printDataList(String apiURL) {
        var dataList = dataConverter.convertDataList(ApiCaller.getAPI(apiURL), DataInitialCalls.class);
        dataList.stream()
                .sorted(Comparator.comparing(DataInitialCalls::code))
                .forEach(data -> System.out.println(CODIGO + data.code() + MODEL + data.name())
                );
    }

    private void getBrandCode() {
        String reply;
        System.out.println("""
                               ==========================
                               Informe o codigo da marca (Ou voltar):
                               """);
        reply = scan.nextLine();
        checkBrandCodeReply(reply);

    }

    private void checkBrandCodeReply(String reply) {

        if (!reply.equalsIgnoreCase("voltar")) {
            urlUsed = urlUsed + "/" + reply + "/modelos/";
            printModels(urlUsed);
        }

    }

    private void printModels(String apiURL) {
        var dataModels = dataConverter.convertData(ApiCaller.getAPI(apiURL), DataModelos.class);
        dataModels.dataInitialCalls().stream()
                .sorted(Comparator.comparing(DataInitialCalls::code))
                .forEach(data
                        -> System.out.println(CODIGO + data.code() + MODEL + data.name())
                );

        printModelsSearch(dataModels);
    }

    private void printModelsSearch(DataModelos dataModels) {
        String reply;
        System.out.println("""
                               ==========================
                               Digite um trecho do nome do veiculo para consulta:
                               """);
        reply = scan.nextLine();
        System.out.println("""

                               ========================== Models ==========================
                               
                               """);
        dataModels.dataInitialCalls().stream()
                .sorted(Comparator.comparing(DataInitialCalls::code))
                .filter(c -> c.name().toLowerCase().contains(reply.toLowerCase()))
                .collect(Collectors.toList()).forEach(data
                -> System.out.println(CODIGO + data.code() + MODEL + data.name())
        );

        printVehiclePrompt();
    }

    private void printVehiclePrompt() {
        String reply;
        System.out.println("""
                               ==========================
                               Digite o codigo do veiculo para consultar preco por ano:
                               """);
        reply = scan.nextLine();

        urlUsed = urlUsed + reply + "/anos/";

        var listaYears = dataConverter.convertDataList(ApiCaller.getAPI(urlUsed), DataInitialCalls.class);
        getVehicleDataByYear(listaYears);

        printVehicleValue();
    }

    private void getVehicleDataByYear(List<DataInitialCalls> listaYears) {
        vehicles = new ArrayList<>();

        listaYears.forEach(yearModel
                -> vehicles.add(dataConverter.convertData(ApiCaller.getAPI(urlUsed + yearModel.code()), Vehicle.class))
        );
    }

    private void printVehicleValue() {
        System.out.println("""
            ==================== Vehicles Price / Year ====================
            """);
        vehicles.forEach(System.out::println);
        System.out.println("""
            ===============================================================
            """);
    }

}

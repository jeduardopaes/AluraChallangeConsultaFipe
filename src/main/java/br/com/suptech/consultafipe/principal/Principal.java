package br.com.suptech.consultafipe.principal;

import java.util.Comparator;
import java.util.Scanner;

import br.com.suptech.consultafipe.models.DataBrand;
import br.com.suptech.consultafipe.services.ApiCaller;
import br.com.suptech.consultafipe.services.DataConverter;

public class Principal {

    private final Scanner scan = new Scanner(System.in);
    private final DataConverter dataConverter = new DataConverter();

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
        System.out.println("=================== Modelos de Carros ================");
        printBrands(apiURLCarModel);

    }

    private void bikeOption() {
        String apiURLBikeModel = "https://parallelum.com.br/fipe/api/v1/motos/marcas";
        System.out.println("=================== Modelos de Motos ================");
        printBrands(apiURLBikeModel);

    }

    private void truckOption() {
        String apiURLTruckModel = "https://parallelum.com.br/fipe/api/v1/caminhoes/marcas";
        System.out.println("=================== Modelos de Caminhoes ================");
        printBrands(apiURLTruckModel);
    }

    private void printBrands(String apiURL) {
        var bikeModels = dataConverter.convertDataList(ApiCaller.getAPI(apiURL), DataBrand.class);
        bikeModels.stream()
                .sorted(Comparator.comparing(DataBrand::code))
                .forEach(bike -> {
                    System.out.println("Codigo: " + bike.code() + " Model: " + bike.name());
                });
    }

}

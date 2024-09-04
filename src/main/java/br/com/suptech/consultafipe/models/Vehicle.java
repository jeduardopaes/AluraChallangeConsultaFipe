package br.com.suptech.consultafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(@JsonAlias("Valor")
        String value, @JsonAlias("Modelo")
        String model, @JsonAlias("AnoModelo")
        String year, @JsonAlias("Combustivel")
        String fuelType, @JsonAlias("Marca")
        String brand) {

}

package br.com.suptech.consultafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataInitialCalls(@JsonAlias("codigo")
        String code,
        @JsonAlias("nome")
        String name) {

}

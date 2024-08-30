package br.com.suptech.consultafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBrand(@JsonAlias("codigo")
        Integer code,
        @JsonAlias("nome")
        String name) {

}

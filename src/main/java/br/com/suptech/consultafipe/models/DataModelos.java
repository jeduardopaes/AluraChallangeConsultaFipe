package br.com.suptech.consultafipe.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataModelos(@JsonAlias("modelos")
        List<DataInitialCalls> dataInitialCalls) {

}

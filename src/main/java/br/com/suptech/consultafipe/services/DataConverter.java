package br.com.suptech.consultafipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IConvertData {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> typeClass) {

        try {
            return objectMapper.readValue(json, typeClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}

package br.com.suptech.consultafipe.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IConvertData {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> typeClass) {

        try {
            return objectMapper.readValue(json, typeClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public <T> List<T> convertDataList(String json, Class<T> typeClass) {

        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, typeClass));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}

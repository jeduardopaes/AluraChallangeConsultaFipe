package br.com.suptech.consultafipe.services;

import java.util.List;

public interface IConvertData {

    <T> T convertData(String json, Class<T> typeClass);

    <T> List<T> convertDataList(String json, Class<T> typeClass);
}

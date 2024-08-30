package br.com.suptech.consultafipe.services;

public interface IConvertData {

    <T> T convertData(String json, Class<T> typeClass);
}

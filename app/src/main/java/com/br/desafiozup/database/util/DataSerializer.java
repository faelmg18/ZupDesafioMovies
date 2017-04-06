package com.br.desafiozup.database.util;

public interface DataSerializer {
    public  String toJson(Object content);
    public <T>  T toObject(String json, Class<T> targetClass);
}
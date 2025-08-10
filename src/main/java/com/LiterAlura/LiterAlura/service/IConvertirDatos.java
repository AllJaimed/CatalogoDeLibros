package com.LiterAlura.LiterAlura.service;

public interface IConvertirDatos {

    <T> T obtenerDAtos(String json, Class<T> clase);

}
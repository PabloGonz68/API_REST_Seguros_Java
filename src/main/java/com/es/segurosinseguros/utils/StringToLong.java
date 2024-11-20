package com.es.segurosinseguros.utils;

public class StringToLong {

    public static Long stringToLong(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(id + " no es un número válido");
        }
    }
}

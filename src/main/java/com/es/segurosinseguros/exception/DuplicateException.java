package com.es.segurosinseguros.exception;

public class DuplicateException extends RuntimeException{
    private static final String DESCRIPCION = "Duplicate (409)";
    public DuplicateException(String mensaje) {
        super(DESCRIPCION + ": " + mensaje);
    }
}

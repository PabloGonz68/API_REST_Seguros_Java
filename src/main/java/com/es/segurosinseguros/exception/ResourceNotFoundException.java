package com.es.segurosinseguros.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final String DESCRIPCION = "Resource not found (404)";

    public ResourceNotFoundException(String mensaje) {
        super(DESCRIPCION + ": " + mensaje);
    }
}

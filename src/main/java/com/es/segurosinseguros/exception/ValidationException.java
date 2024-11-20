package com.es.segurosinseguros.exception;

public class ValidationException extends RuntimeException {
    private static final String DESCRIPCION = "Validation Error (400)";

    public ValidationException(String mensaje) {
        super(DESCRIPCION + ": " + mensaje);
    }
}

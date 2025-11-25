package com.company.empresaspring.excepciones;

public class DatosNoCorrectosException extends RuntimeException {
    public DatosNoCorrectosException(String message) {
        super(message);
    }
}

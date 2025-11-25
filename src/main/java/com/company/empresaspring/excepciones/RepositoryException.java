package com.company.empresaspring.excepciones;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super(message);
    }
}

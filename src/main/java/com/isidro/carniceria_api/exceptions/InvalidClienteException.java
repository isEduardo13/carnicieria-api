package com.isidro.carniceria_api.exceptions;

public class InvalidClienteException extends RuntimeException {
    public InvalidClienteException(String message) {
        super(message);
    }

    public InvalidClienteException(String message, Throwable cause) {
        super(message, cause);
    }
}


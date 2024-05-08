package com.healthmate.service.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super();
    }
    public IncorrectPasswordException(String message) {
        super(message);
    }
}

package com.healthmate.service.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super();
    }
    public InvalidInputException(String message) {
        super(message);
    }
}

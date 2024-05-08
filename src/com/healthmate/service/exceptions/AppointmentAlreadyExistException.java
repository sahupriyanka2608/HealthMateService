package com.healthmate.service.exceptions;

public class AppointmentAlreadyExistException extends RuntimeException {
    public AppointmentAlreadyExistException() {
        super();
    }
    public AppointmentAlreadyExistException(String message) {
        super(message);
    }
}

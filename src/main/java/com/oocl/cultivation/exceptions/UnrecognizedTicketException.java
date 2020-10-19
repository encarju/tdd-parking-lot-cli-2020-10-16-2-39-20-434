package com.oocl.cultivation.exceptions;

public class UnrecognizedTicketException extends RuntimeException {
    public UnrecognizedTicketException(String message) {
        super(message);
    }
}

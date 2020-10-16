package com.oocl.cultivation;

public class NotProvidedTicketException extends RuntimeException{
    public NotProvidedTicketException(String message) {
        super(message);
    }
}

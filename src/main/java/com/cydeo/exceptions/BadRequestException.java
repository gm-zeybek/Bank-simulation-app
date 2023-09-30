package com.cydeo.exceptions;

public class BadRequestException extends RuntimeException { // template of the exception 1) extended runtime exception class
    public BadRequestException(String message) {
        super(message);  // call inherited class with message
    }
}

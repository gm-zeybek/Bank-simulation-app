package com.cydeo.exceptions;

import java.util.function.Supplier;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}

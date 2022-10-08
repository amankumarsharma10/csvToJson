package com.test.csvtojson.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidDataException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

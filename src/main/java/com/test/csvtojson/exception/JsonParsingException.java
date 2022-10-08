package com.test.csvtojson.exception;

public class JsonParsingException extends RuntimeException {
    public JsonParsingException(String errorMessage) {
        super(errorMessage);
    }

    public JsonParsingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}

package com.test.csvtojson.exception;

public class CSVFileNotFoundException extends RuntimeException {
    public CSVFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public CSVFileNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

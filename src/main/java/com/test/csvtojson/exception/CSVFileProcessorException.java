package com.test.csvtojson.exception;

public class CSVFileProcessorException extends RuntimeException {
    public CSVFileProcessorException(String errorMessage) {
        super(errorMessage);
    }

    public CSVFileProcessorException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

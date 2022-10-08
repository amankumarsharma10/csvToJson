package com.test.csvtojson.service;

import com.test.csvtojson.exception.CSVFileNotFoundException;
import com.test.csvtojson.model.Order;

import java.util.List;

public interface CSVFileReader {
    List<Order> readFile(String fileName) throws CSVFileNotFoundException;
}

package com.test.csvtojson.service;

import com.test.csvtojson.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVFileProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileReader.class);
    @Autowired
    CSVFileReader csvFileReader;

    @Autowired
    OrderJsonService orderJsonService;

    public void csvFileProcessor(String file) {
        LOGGER.info("start csvFileProcessor");
        try {
            List<Order> orderList = csvFileReader.readFile(file);
            orderJsonService.convertToJson(orderList);
        } catch (Exception e) {
            throw e;
        }
    }
}

package com.test.csvtojson.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.csvtojson.exception.JsonParsingException;
import com.test.csvtojson.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class OrderJsonServiceImpl implements OrderJsonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderJsonService.class);
    @Autowired
    ObjectMapper mapper;

    @Override
    public String convertToJson(List<Order> orderList) throws JsonParsingException {
        LOGGER.info("start transforming orderList into json string");
        String json;
        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            json = mapper.writeValueAsString(orderList);

            LOGGER.info("orderList transformed into json string");

            writeToFile(json);

        } catch (JsonProcessingException e) {
            throw new JsonParsingException("Exception while parsing to json", e);
        }
        return json;
    }

    public void writeToFile(String json) {
        try (FileWriter jsonWriter = new FileWriter("order.json")) {
            jsonWriter.write(json);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

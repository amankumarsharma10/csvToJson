package com.test.csvtojson.service;

import com.test.csvtojson.exception.JsonParsingException;
import com.test.csvtojson.model.Order;

import java.util.List;

public interface OrderJsonService {
    String convertToJson(List<Order> order) throws JsonParsingException;
}

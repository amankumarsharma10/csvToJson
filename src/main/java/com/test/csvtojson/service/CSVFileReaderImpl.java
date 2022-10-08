package com.test.csvtojson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.csvtojson.exception.CSVFileNotFoundException;
import com.test.csvtojson.exception.InvalidDataException;
import com.test.csvtojson.model.ORDER_CSV_HEADER;
import com.test.csvtojson.model.Order;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class CSVFileReaderImpl implements CSVFileReader{
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFileReaderImpl.class);

    public List<Order> readFile(String fileName) throws CSVFileNotFoundException {
        LOGGER.info("Start reading the file : {}", fileName);
        File file = new File(fileName);
        List<Order> orderList = new LinkedList<>();
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.builder().setHeader(ORDER_CSV_HEADER.class).setIgnoreHeaderCase(true).setSkipHeaderRecord(true).build();
        try (Reader reader = new FileReader(file)) {
            Iterable<CSVRecord> records = csvFileFormat.parse(reader);
            AtomicLong count = new AtomicLong(1);

            // Using Stream for parallel processing
            Stream<CSVRecord> csvRecordStream = StreamSupport.stream(records.spliterator(), true);

            csvRecordStream.forEach(csvRecord -> {
                String orderId = csvRecord.get(ORDER_CSV_HEADER.ORDER_ID);
                String currency = csvRecord.get(ORDER_CSV_HEADER.CURRENCY);
                String amount = csvRecord.get(ORDER_CSV_HEADER.AMOUNT);
                String comments = csvRecord.get(ORDER_CSV_HEADER.COMMENTS);

                Order order = new Order.OrderBuilder().orderId(orderId)
                        .amount(amount).currency(currency).fileName(fileName)
                        .lineNumber(count.getAndIncrement()).comments(comments)
                        .build();
                orderList.add(order);
            });
            LOGGER.info("CSV file reading completed");
        } catch (JsonProcessingException e) {
            throw new InvalidDataException("Exception while processing CSV file", e);
        } catch (IOException e) {
            throw new CSVFileNotFoundException("File not found", e);
        }

        return orderList;
    }
}

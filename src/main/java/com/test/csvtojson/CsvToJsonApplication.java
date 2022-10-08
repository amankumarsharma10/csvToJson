package com.test.csvtojson;

import com.test.csvtojson.service.CSVFileProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CsvToJsonApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(CsvToJsonApplication.class, args);
        CSVFileProcessor csvFileProcessor = app.getBean(CSVFileProcessor.class);
        if (args.length >= 1) {
            System.out.println("CSV file name: "+args[0]);
            csvFileProcessor.csvFileProcessor(args[0]);
        } else {
            System.out.println("Please pass csv file name as cmd argument");
            System.exit(0);
        }
    }
}

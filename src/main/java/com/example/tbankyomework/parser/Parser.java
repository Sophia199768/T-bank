package com.example.tbankyomework.parser;

import com.example.tbankyomework.object.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Parser {
    private static Logger logger = LoggerFactory.getLogger(Parser.class);
    public City read(String fileName) {
        logger.info("Read function started");
        ObjectMapper objectMapper = new ObjectMapper();
        City city = null;
        
        try {
            File jsonFile = new File(fileName);
            city = objectMapper.readValue(jsonFile, City.class);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        logger.info("Read function finished");
        return city;
    }

}

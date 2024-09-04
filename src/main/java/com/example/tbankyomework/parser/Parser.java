package com.example.tbankyomework.parser;

import com.example.tbankyomework.object.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;


import java.io.File;
import java.io.IOException;
@Log4j2
public class Parser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public City read(String fileName) {
        City city = null;
        
        try {
            File jsonFile = new File(fileName);
            city = objectMapper.readValue(jsonFile, City.class);

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return city;
    }

}

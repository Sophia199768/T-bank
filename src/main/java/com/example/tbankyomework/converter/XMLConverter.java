package com.example.tbankyomework.converter;

import com.example.tbankyomework.object.City;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;


import java.io.File;

@Log4j2
public class XMLConverter {

    private final XmlMapper xmlMapper;

    public XMLConverter() {
        this.xmlMapper = new XmlMapper();
    }

    public void toXML(City city, String filePath) {
        log.info("Starting XML conversion for city: {} into file: {}", city.getSlug(), filePath);


        try {
            log.debug("Trying to write City object to XML: {}", city);

            xmlMapper.writeValue(new File(filePath), city);

            log.debug("Successfully wrote XML file to path: {}", filePath);
        } catch (Exception e) {
            log.warn("Failed to write to path: {}. Error: {}", filePath, e.getMessage());

            log.error("Exception occurred: ", e);
        }
    }
}

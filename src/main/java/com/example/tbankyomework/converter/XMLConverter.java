package com.example.tbankyomework.converter;

import com.example.tbankyomework.object.City;
import com.example.tbankyomework.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;

public class XMLConverter {
    private static Logger logger = LoggerFactory.getLogger(Parser.class);
    public void toXML(City city, String filePath) {
        logger.info("XML converter function started");

        try {
            logger.debug("Try to write City object to XML: {}", city);

            FileWriter writer = new FileWriter(new File(filePath));

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<City>\n");
            writer.write("    <Slug>" + city.getSlug() + "</Slug>\n");
            writer.write("    <Coords>\n" + "       <lat>" + city.getCoords().getLat() + "</lat>\n" +
                    "       <lon>" + city.getCoords().getLon() + "</lon>\n" + "    </Coords>\n");
            writer.write("</City>\n");

            writer.close();

            logger.debug("Successfully wrote XML file to path: {}", filePath);
        } catch (Exception e) {
            logger.warn("Failed to write to path: {}. Error: {}", filePath, e.getMessage());

            logger.error(e.getMessage());
        }

        logger.info("XML converter function finished");
    }
}

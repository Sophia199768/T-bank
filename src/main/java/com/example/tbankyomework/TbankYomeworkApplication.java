package com.example.tbankyomework;

import com.example.tbankyomework.converter.XMLConverter;
import com.example.tbankyomework.object.City;
import com.example.tbankyomework.object.Coords;
import com.example.tbankyomework.parser.Parser;


public class TbankYomeworkApplication {

    public static void main(String[] args) {

        Parser parser = new Parser();
        parser.read("city.json");

        XMLConverter xmlConverter = new XMLConverter();

        Coords coords = new Coords(59.939095, 30.315868);
        City city = new City("spb", coords);
        xmlConverter.toXML(city,"newXML");

    }

}

package com.example.tbankyomework;

import com.example.tbankyomework.converter.XMLConverter;
import com.example.tbankyomework.object.City;
import com.example.tbankyomework.parser.Parser;


public class TbankHomeworkApplication {

    public static void main(String[] args) {

        Parser parser = new Parser();

        City readCity = parser.read("city.json");
        System.out.println(readCity);

        XMLConverter xmlConverter = new XMLConverter();

        xmlConverter.toXML(readCity,"city.xml");
    }

}

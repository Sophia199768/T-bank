package com.example.tbanks.valute;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Valute {
   @JacksonXmlProperty(isAttribute = true)
   private String ID;
   @JacksonXmlProperty(localName = "NumCode")
   private Integer numCode;
   @JacksonXmlProperty(localName = "CharCode")
   private String charCode;
   @JacksonXmlProperty(localName = "Nominal")
   private Integer nominal;
   @JacksonXmlProperty(localName = "Name")
   private String name;
   @JacksonXmlProperty(localName = "Value")
   private String value;
   @JacksonXmlProperty(localName = "VunitRate")
   private String vunitRate;
}

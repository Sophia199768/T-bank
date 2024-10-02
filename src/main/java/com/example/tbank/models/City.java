package com.example.tbank.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class City {
    private String slug;
    private String name;
}

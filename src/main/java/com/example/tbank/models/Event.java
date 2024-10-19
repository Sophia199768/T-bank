package com.example.tbank.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Event {
    private String name;
    private double price;
    private LocalDate date;
}

package com.example.tbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class EventDto {
    private String name;
    private Double price;
    private LocalDate date;
    private Long placeId;
}

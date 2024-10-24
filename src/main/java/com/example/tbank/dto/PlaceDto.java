package com.example.tbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PlaceDto {
    private String slug;
    private String name;
    private List<EventDto> events;
}

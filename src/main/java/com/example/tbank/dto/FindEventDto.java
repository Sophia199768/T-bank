package com.example.tbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindEventDto {
    private String name;
    private Long place;
    private LocalDate fromDate;
    private LocalDate toDate;
}

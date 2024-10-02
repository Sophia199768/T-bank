package com.example.tbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    private String slug;
    private String name;
}

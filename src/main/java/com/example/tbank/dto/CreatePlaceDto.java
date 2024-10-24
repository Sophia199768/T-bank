package com.example.tbank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class CreatePlaceDto {
    @NotNull(message = "Slug cannot be null")
    @NotBlank(message = "Slug cannot be blank")
    private String slug;
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;
}

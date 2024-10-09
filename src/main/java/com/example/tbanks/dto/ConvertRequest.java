package com.example.tbanks.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class ConvertRequest {
    private String fromCurrency;
    private String toCurrency;
    private Double amount;
}

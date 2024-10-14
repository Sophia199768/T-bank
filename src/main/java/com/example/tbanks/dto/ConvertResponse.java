package com.example.tbanks.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ConvertResponse {
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal convertedAmount;
}

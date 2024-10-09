package com.example.tbanks.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ConvertResponse {
    private String fromCurrency;
    private String toCurrency;
    private Double convertedAmount;
}

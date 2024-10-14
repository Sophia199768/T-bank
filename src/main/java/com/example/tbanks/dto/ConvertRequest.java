package com.example.tbanks.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
public class ConvertRequest {
    @NotNull(message = "fromCurrency can't be null")
    private String fromCurrency;
    @NotNull(message = "toCurrency can't be null")
    private String toCurrency;
    @NotNull(message = "must not be null")
    @Positive(message = "amount can't be 0 or less than 0")
    private BigDecimal amount;
}

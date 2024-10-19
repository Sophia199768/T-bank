package com.example.tbank.services;

import com.example.tbank.dto.ConversionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

    private final WebClient webClient;

    public Mono<Double> convertToRub(double amount, String currency) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/convert")
                        .queryParam("amount", amount)
                        .queryParam("from", currency)
                        .queryParam("to", "rub")
                        .build())
                .retrieve()
                .bodyToMono(ConversionDto.class)
                .map(ConversionDto::getConvertedAmount);
    }
}

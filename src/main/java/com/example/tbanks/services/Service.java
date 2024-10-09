package com.example.tbanks.services;

import com.example.tbanks.exception.CurrException;
import com.example.tbanks.dto.GetResponse;
import com.example.tbanks.dto.ConvertRequest;
import com.example.tbanks.dto.ConvertResponse;
import com.example.tbanks.exception.ServerException;
import com.example.tbanks.mapper.CurrMapper;
import com.example.tbanks.valute.ValCurs;
import com.example.tbanks.valute.Valute;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.function.Supplier;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final RestTemplate restTemplate;
    private final CurrMapper currMapper;
    private final CircuitBreaker circuitBreaker;
    @Value("${url}")
    private String url;

    public GetResponse get(Integer code) {
        ValCurs valCurs = getCurrency();

        Valute valute = valCurs.getValute()
                .stream()
                .filter(curr -> Objects.equals(curr.getNumCode(), code))
                .findFirst().orElseThrow(() -> new CurrException("Not found currency code : " +  code));

        return currMapper.toGetResponse(valute);
    }

    public ConvertResponse convert(ConvertRequest convertRequest) {
        ValCurs valCurs = getCurrency();
        Valute valuteFrom = valCurs.getValute()
                .stream()
                .filter(curr -> Objects.equals(curr.getNumCode(), convertRequest.getFromCurrency()))
                .findFirst().orElseThrow(() -> new CurrException("Not found currency code : " + convertRequest.getFromCurrency()));

        Valute valuteTo = valCurs.getValute()
                .stream()
                .filter(curr -> Objects.equals(curr.getNumCode(), convertRequest.getToCurrency()))
                .findFirst().orElseThrow(() -> new CurrException("Not found currency code : " + convertRequest.getToCurrency()));

        Double amountInFromCurrency = convertRequest.getAmount();
        Double rateFrom = Double.parseDouble(valuteFrom.getVunitRate().replace(",", "."));
        Double rateTo = Double.parseDouble(valuteTo.getVunitRate().replace(",", "."));
        Double convertedAmount = (amountInFromCurrency * rateFrom) / rateTo;

        ConvertResponse convertResponse = new ConvertResponse(valuteFrom.getCharCode(), valuteTo.getCharCode(),
                convertedAmount);

        return convertResponse;
    }

    @Cacheable(value = "currencyCache")
    public ValCurs getCurrency() {

        Supplier<ValCurs> supplier = circuitBreaker.decorateSupplier(() -> {
            XmlMapper xmlMapper = new XmlMapper();
            String response;

            try {
                response = restTemplate.getForObject(url, String.class);
            } catch (RestClientException e) {
                throw new ServerException("Server unavailable");
            }

            ValCurs valCurs = null;
            try {
                valCurs = xmlMapper.readValue(response, ValCurs.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return valCurs;
        });

        return supplier.get();
    }
}

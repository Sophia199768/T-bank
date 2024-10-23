package com.example.tbanks.controller;

import com.example.tbanks.dto.ConvertRequest;
import com.example.tbanks.dto.ConvertResponse;
import com.example.tbanks.dto.GetResponse;
import com.example.tbanks.exception.ServerException;
import com.example.tbanks.services.Service;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CurrenciesController.class)
public class CurrenciesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Service service;


    @Test
    public void testGetCurrency_Success() throws Exception {
        GetResponse mockResponse = new GetResponse();

        when(service.get(840)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/currencies/rates/{code}", 840))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(notNullValue())));
    }


    @Test
    public void testConvertCurrency_ServiceUnavailable() throws Exception {
        ConvertRequest request = new ConvertRequest();
        request.setFromCurrency(String.valueOf(840));
        request.setToCurrency(String.valueOf(978));
        request.setAmount(BigDecimal.valueOf(100.0));

        when(service.convert(any(ConvertRequest.class))).thenThrow(new ServerException("Unavailable"));

        mockMvc.perform(MockMvcRequestBuilders.post("/currencies/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fromCurrency\":840, \"toCurrency\":978, \"amount\":100}"))
                .andExpect(status().isServiceUnavailable());
    }


    @Test
    public void testConvertCurrency_Success() throws Exception {
        ConvertRequest request = new ConvertRequest();
        request.setFromCurrency(String.valueOf(840));
        request.setToCurrency(String.valueOf(978));
        request.setAmount(BigDecimal.valueOf(100.0));

        ConvertResponse mockResponse = new ConvertResponse();
        mockResponse.setFromCurrency("USD");
        mockResponse.setToCurrency("EUR");
        mockResponse.setConvertedAmount(BigDecimal.valueOf(85.0));
        when(service.convert(any(ConvertRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/currencies/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fromCurrency\":840, \"toCurrency\":978, \"amount\":100}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.convertedAmount", is(85.0)));
    }


    @Test
    public void testConvertCurrency_NullToCurrency() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/currencies/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fromCurrency\":840, \"toCurrency\":null, \"amount\":100}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.toCurrency", is("toCurrency can't be null")));
    }

    @Test
    public void testConvertCurrency_NullFromCurrency() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/currencies/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fromCurrency\":null, \"toCurrency\":978, \"amount\":100}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fromCurrency", is("fromCurrency can't be null")));
    }

    @Test
    public void testConvertCurrency_NullAmount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/currencies/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fromCurrency\":840, \"toCurrency\":978, \"amount\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.amount", is("must not be null")));
    }

    @Test
    public void testConvertCurrency_ZeroAmount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/currencies/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fromCurrency\":840, \"toCurrency\":978, \"amount\":0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.amount", is("amount can't be 0 or less than 0")));
    }
}

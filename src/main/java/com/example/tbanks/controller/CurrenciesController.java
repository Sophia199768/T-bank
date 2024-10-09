package com.example.tbanks.controller;


import com.example.tbanks.exception.CurrException;
import com.example.tbanks.dto.GetResponse;
import com.example.tbanks.dto.ConvertRequest;
import com.example.tbanks.dto.ConvertResponse;
import com.example.tbanks.services.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies/")
@AllArgsConstructor
public class CurrenciesController {
    private final Service service;

    @GetMapping("rates/{code}")
    @Operation(summary = "Get information about currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Server problem")
    })
    public GetResponse get(@PathVariable Integer code) {
        if (code == null) {
            throw  new CurrException("Code can't be null");
        }
        return service.get(code);
    }

    @PostMapping("convert")
    @Operation(summary = "Convert currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Server problem")
    })
    public ConvertResponse post(@RequestBody ConvertRequest convertRequest) {
        if (convertRequest.getToCurrency() == null) {
            throw  new CurrException("toCurrency can't be null");
        }

        if (convertRequest.getFromCurrency() == null) {
            throw  new CurrException("fromCurrency can't be null");
        }

        if (convertRequest.getAmount() == null) {
            throw  new CurrException("amount can't be null");
        }

        if (convertRequest.getAmount() <= 0) {
            throw  new CurrException("amount can't be 0 or less then 0");
        }

        return service.convert(convertRequest);
    }
}

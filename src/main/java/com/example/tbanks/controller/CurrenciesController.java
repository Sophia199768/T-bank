package com.example.tbanks.controller;

import com.example.tbanks.dto.GetResponse;
import com.example.tbanks.dto.ConvertRequest;
import com.example.tbanks.dto.ConvertResponse;
import com.example.tbanks.services.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currencies/")
@AllArgsConstructor
public class CurrenciesController {
    private final Service service;

    @GetMapping("rates/{code}")
    @Operation(summary = "Get information about currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"error\": \"Invalid currency code\"}"))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"error\": \"Currency not found\"}"))),
            @ApiResponse(responseCode = "500", description = "Server problem",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"error\": \"Internal server error\"}")))
    })
    public GetResponse get(@PathVariable @NotNull Integer code) {
        return service.get(code);
    }

    @PostMapping("convert")
    @Operation(summary = "Convert currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ConvertResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"error\": \"Bad request\"}"))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"error\": \"Not found\"}"))),
            @ApiResponse(responseCode = "500", description = "Server problem",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(example = "{\"error\": \"Internal server error\"}")))
    })
    public ConvertResponse post(@Valid @RequestBody ConvertRequest convertRequest) {
        BigDecimal amount = convertRequest.getAmount();

        return service.convert(convertRequest);
    }
}

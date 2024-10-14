package com.example.tbank.services;

import com.example.tbank.dto.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWireMock(port = 9999)
public class CategoryServiceIntegrationTests {

    @Autowired
    private InitService service;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        this.service.setUrl("http://localhost:9999");
    }

    @Test
    public void contextLoads() {
        stubFor(get(urlEqualTo("/locations/"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"name\":\"New York\", \"slug\":\"new-york\"}]")));

        List<CategoryDto> categories = categoryService.findAllCategories();

        assertNotNull(categories);
        assertFalse(categories.isEmpty());

    }
}


package com.example.tbank.services;

import com.example.tbank.dto.CategoryDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {CategoryServiceIntegrationTests.Initializer.class})
class CategoryServiceIntegrationTests {

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:13-alpine")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("password");

    private static WireMockServer wireMockServer;

    @Autowired
    private CategoryService categoryService;

    @BeforeAll
    static void setupWireMock() {
        wireMockServer = new WireMockServer(9562); // Используем другой порт для WireMock
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @AfterAll
    static void teardownWireMock() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @BeforeEach
    void setup() {
        wireMockServer.resetAll();
    }

    @Test
    void findAllCategories_shouldReturnListOfCategories_whenCategoriesExist() {
         List<CategoryDto> categories = categoryService.findAllCategories();

         Assertions.assertNotNull(categories);
         Assertions.assertFalse(categories.isEmpty());
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresContainer.getUsername(),
                    "spring.datasource.password=" + postgresContainer.getPassword()
            ).applyTo(context.getEnvironment());
        }
    }
}
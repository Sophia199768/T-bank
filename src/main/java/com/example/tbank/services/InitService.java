package com.example.tbank.services;

import com.example.tbank.models.Category;
import com.example.tbank.models.City;
import com.example.tbank.repository.CategoryRepository;
import com.example.tbank.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitService {

    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    @Qualifier("initTaskExecutor")
    private final ExecutorService initTaskExecutor;
    @Qualifier("scheduledExecutor")
    private final ScheduledExecutorService scheduledExecutor;
    @Value("${init.frequency}")
    private Integer frequency;

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStarted() {
        Duration scheduleInterval = Duration.ofMinutes(frequency);
        scheduledExecutor.scheduleAtFixedRate(this::initData, 0, scheduleInterval.toMinutes(), TimeUnit.MINUTES);
    }

    private void initData() {
        log.info("Starting data initialization");
        CompletableFuture<Void> citiesFuture = CompletableFuture.runAsync(this::initCities, initTaskExecutor);
        CompletableFuture<Void> categoriesFuture = CompletableFuture.runAsync(this::initCategories, initTaskExecutor);

        CompletableFuture.allOf(citiesFuture, categoriesFuture).join();
        log.info("Data initialization finished");
    }

    private void initCities() {
        String url = "https://kudago.com/public-api/v1.4/locations/";
        try {
            City[] cities = restTemplate.getForObject(url, City[].class);
            if (cities == null) {
                log.error("Cannot init cities");
            } else {
                Arrays.stream(cities).forEach(locationRepository::create);
            }
        } catch (RestClientException e) {
            log.error("Failed to fetch cities");
        }
    }

    private void initCategories() {
        String url = "https://kudago.com/public-api/v1.4/event-categories/";
        try {
            Category[] categories = restTemplate.getForObject(url, Category[].class);
            if (categories == null) {
                log.error("Cannot init category");
            } else {
                Arrays.stream(categories).forEach(categoryRepository::create);
            }
        } catch (RestClientException e) {
            log.error("Failed to fetch categories");
        }
    }
}

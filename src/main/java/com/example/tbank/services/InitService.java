package com.example.tbank.services;

import com.example.tbank.aop.Loggable;
import com.example.tbank.models.Category;
import com.example.tbank.models.City;
import com.example.tbank.repository.CategoryRepository;
import com.example.tbank.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@Slf4j
@Setter
@RequiredArgsConstructor
public class InitService {

    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    @Autowired
    private String url;

    @Loggable
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

        try {
            City[] cities = restTemplate.getForObject(url + "locations/", City[].class);
            if (cities == null) {
                log.error("Cannot init cities");
            } else {
                Arrays.stream(cities).forEach(locationRepository::create);
            }
        } catch (RestClientException e) {
            log.error("Failed to fetch cities");
        }

        try {
            Category[] categories = restTemplate.getForObject(url + "event-categories/", Category[].class);
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

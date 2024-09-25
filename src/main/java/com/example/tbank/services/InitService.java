package com.example.tbank.services;

import com.example.tbank.aop.Loggable;
import com.example.tbank.models.Category;
import com.example.tbank.models.City;
import com.example.tbank.repository.CategoryRepository;
import com.example.tbank.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitService {

    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    @Loggable
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String url = "https://kudago.com/public-api/v1.4/";
        City[] cities = restTemplate.getForObject(url + "locations/", City[].class);
        if (cities == null) log.error("Cannot init cities repo from " + url);
        Arrays.stream(cities).forEach(locationRepository::create);

        Category[] categories = restTemplate.getForObject("https://kudago.com/public-api/v1.4/event-categories/", Category[].class);
        if (categories == null) log.error("Cannot init category repo from " + url);
        Arrays.stream(categories).forEach(categoryRepository::create);
    }
}
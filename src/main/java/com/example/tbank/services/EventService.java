package com.example.tbank.services;

import com.example.tbank.models.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {

    private final WebClient webClient;

    public Mono<List<Event>> getEvents(LocalDate dateFrom, LocalDate dateTo) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/events")
                        .queryParam("dateFrom", dateFrom)
                        .queryParam("dateTo", dateTo)
                        .build())
                .retrieve()
                .bodyToFlux(Event.class)
                .collectList();
    }
}
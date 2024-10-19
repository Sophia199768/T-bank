package com.example.tbank.restControllers;

import com.example.tbank.models.Event;
import com.example.tbank.services.CurrencyConversionService;
import com.example.tbank.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;
    private final CurrencyConversionService currencyConversionService;

    @GetMapping
    public Mono<ResponseEntity<List<Event>>> getEvents(
            @RequestParam("budget") double budget,
            @RequestParam("currency") String currency,
            @RequestParam(value = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(value = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

        LocalDate from = dateFrom != null ? dateFrom : LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDate to = dateTo != null ? dateTo : LocalDate.now().with(DayOfWeek.SUNDAY);

        Mono<List<Event>> eventsMono = eventService.getEvents(from, to);
        Mono<Double> convertedBudgetMono = currencyConversionService.convertToRub(budget, currency);

        return Mono.zip(eventsMono, convertedBudgetMono)
                .flatMap(tuple -> {
                    List<Event> events = tuple.getT1();
                    Double convertedBudget = tuple.getT2();
                    List<Event> suitableEvents = events.stream()
                            .filter(event -> event.getPrice() <= convertedBudget)
                            .collect(Collectors.toList());
                    return Mono.just(ResponseEntity.ok(suitableEvents));
                });
    }
}

package com.example.tbank.services;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.Semaphore;

@Component
@RequiredArgsConstructor
public class ApiService {

    private final WebClient webClient;
    private final Semaphore semaphore;

    public Mono<Response> fetchData(String url) {
        return Mono.fromCallable(() -> {
                    semaphore.acquire();
                    return url;
                })
                .flatMap(finalUrl -> webClient.get().uri(finalUrl).retrieve().bodyToMono(Response.class))
                .doFinally(signalType -> semaphore.release());
    }
}

package com.example.tbank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "initTaskExecutor")
    public ExecutorService fixedThreadPool(@Value("${init.thread.pool.size}") int poolSize) {
        return Executors.newFixedThreadPool(poolSize, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setName("InitWorkerThread");
            return thread;
        });
    }

    @Bean(name = "scheduledExecutor")
    public ScheduledExecutorService scheduledThreadPool(@Value("${scheduler.thread.pool.size}") int poolSize) {
        return Executors.newScheduledThreadPool(poolSize, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setName("SchedulerThread");
            return thread;
        });
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://kudago.com/public-api/v1.4/")
                .build();
    }

    @Bean
    public Semaphore semaphore(@Value("${api.kudago.max-concurrent-requests}") int maxConcurrentRequests) {
        return new Semaphore(maxConcurrentRequests);
    }
}

package com.wafflehammer.ServiceA.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAService {
    private final Logger log = LogManager.getLogger(ServiceAService.class);

    private static final String BASE_URL = "http://localhost:8081/";

    @Autowired
    private ServiceBRestTemplate restTemplate;

    public String callRandomServiceB(String test) {
        String url = BASE_URL + "b/random-errors?test=" + test;
        String response = restTemplate.fetchAll(url);
        log.info("response: " + response);
        return response;
    }
}

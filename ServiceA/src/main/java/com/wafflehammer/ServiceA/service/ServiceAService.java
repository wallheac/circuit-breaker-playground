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
    private static final String SERVICE_A = "serviceA";

    @Autowired
    private ServiceBRestTemplate restTemplate;

    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    @Retry(name = SERVICE_A)
    public void callServiceB(int numRequests, double failurePercentage) {
        double failureIndicator = Math.ceil(100 / failurePercentage);
        String url = BASE_URL + "b/service-errors";
        String response = "weird";
        for (int counter = 1; counter <= numRequests; counter++) {
            boolean shouldFail = counter % failureIndicator == 0;
            response = restTemplate.getForObject(url + "?throwError=" + shouldFail, String.class);
            log.info("response: " + response);
        }
    }

    public String callRandomServiceB() {
        String url = BASE_URL + "b/random-errors";
        String response = restTemplate.fetchAll(url);
        log.info("response: " + response);
        return response;
    }
}

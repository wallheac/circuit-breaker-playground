package com.wafflehammer.ServiceA.controller;

import com.wafflehammer.ServiceA.service.ServiceAService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/a")
public class ServiceAController {

    final static Logger log = LogManager.getLogger(ServiceAController.class);

    private static final String BASE_URL = "http://localhost:8081/";
    private static final String SERVICE_A = "serviceA";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServiceAService serviceAService;

    @GetMapping("/random-errors")
    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    @Retry(name = SERVICE_A)
    public String randomErrors(@RequestParam String test) {
        String result = serviceAService.callRandomServiceB(test);
        return result;
    }


    public String serviceAFallback(String test, Exception e) {
        //this could save off to the async call log pattern
        log.info("Service A fallback. Cause: {}. Has param test set to: {}" , e, test);

        return "this is a fallback method for service A failing to reach service B";
    }

    public String serviceAFallback(String test, CallNotPermittedException e) {
        log.info("CIRCUIT BREAKER Service A fallback. Cause: {}. Has param test set to: {}", e.getCause(), test);

        return "this is a CIRCUIT BREAKER fallback method for service A failing to reach service B";
    }
}

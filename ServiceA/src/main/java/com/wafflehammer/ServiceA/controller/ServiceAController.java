package com.wafflehammer.ServiceA.controller;

import com.wafflehammer.ServiceA.service.ServiceAService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @GetMapping
    @CircuitBreaker(name=SERVICE_A, fallbackMethod = "serviceAFallback")
    public String serviceA() {
        String url = BASE_URL + "b";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/service-errors")
    public String serviceErrors(
            @RequestParam int numRequests,
            @RequestParam double failurePercentage) {
        serviceAService.callServiceB(numRequests, failurePercentage);
        return "service errors completed";
    }



    public String serviceAFallback(Exception e) {
        return "this is a fallback method for service A failing to reach service B";
    }
}

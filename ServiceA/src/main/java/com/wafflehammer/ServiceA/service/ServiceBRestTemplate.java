package com.wafflehammer.ServiceA.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceBRestTemplate extends RestTemplate {

    private final RestTemplate restTemplate;

    public ServiceBRestTemplate() {
        this.restTemplate = new RestTemplateBuilder()
                .build();
    }

    public String fetchAll(String uri) {
        return restTemplate.getForObject(uri, String.class);
    }

}

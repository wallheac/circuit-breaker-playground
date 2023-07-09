package com.wafflehammer.KickoffService.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceARestTemplate extends RestTemplate {

    private final RestTemplate restTemplate;

    public ServiceARestTemplate() {
        this.restTemplate = new RestTemplateBuilder()
                .build();
    }

    public String fetchAll(String uri) {
        return restTemplate.getForObject(uri, String.class);
    }

}

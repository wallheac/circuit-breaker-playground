package com.wafflehammer.KickoffService.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KickoffService {
    private final Logger log = LogManager.getLogger(KickoffService.class);

    private static final String BASE_URL = "http://localhost:8080/";

    @Autowired
    private ServiceARestTemplate restTemplate;

    public void callRandomServiceA() {
        for(int i = 0; i < 1000; i++) {
            String url = getUrl(i);
            String response = restTemplate.fetchAll(url);
            log.info("response: " + response);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                //meh
            }
        }
    }

    private String getUrl(int count) {
        return BASE_URL + "a/random-errors?test=test" + count;
    }
}

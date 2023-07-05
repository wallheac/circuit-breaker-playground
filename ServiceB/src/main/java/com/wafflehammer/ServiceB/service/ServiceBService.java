package com.wafflehammer.ServiceB.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceBService {
    final static Logger log = LogManager.getLogger(ServiceBService.class);

    public ResponseEntity<String> createResponse() {
        double random = Math.random() * 100;
        if (random < 11) {
            log.info("throwing 500");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (random > 10 && random < 21) {
            log.info("throwing 429");
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        } else if (random > 20 && random < 31) {
            log.info("throwing 400");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("Success from ServiceB!");
            return new ResponseEntity<>("Success from ServiceB!", HttpStatus.OK);
        }
    }
}

package com.wafflehammer.ServiceB.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceBService {
    final static Logger log = LogManager.getLogger(ServiceBService.class);

    public ResponseEntity<String> createResponse(String test) {
        double random = Math.random() * 100;
        if (random < 25) {
            log.info("throwing 500 for test {}", test);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else if (random > 24 && random < 50) {
            log.info("throwing 429 for test {}", test);
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        } else if (random > 49 && random < 75) {
            log.info("throwing 400 for test {}", test);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("Success from ServiceB! for test {}", test);
            return new ResponseEntity<>("Success from ServiceB!for test " + test, HttpStatus.OK);
        }
    }
}

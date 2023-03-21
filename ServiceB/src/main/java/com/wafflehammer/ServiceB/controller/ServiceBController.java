package com.wafflehammer.ServiceB.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/b")
public class ServiceBController {

    final static Logger log = LogManager.getLogger(ServiceBController.class);

    @GetMapping
    public String serviceB() {
        return "Service B is called from Service A";
    }

    @GetMapping("/service-errors")
    public String serviceErrors(
            @RequestParam boolean throwError
    ) {
        if (throwError) {
            log.info("throwing 500");
            throw new HttpServerErrorException(HttpStatusCode.valueOf(500));
        } else {
            log.info("intermittent 500 err endpoint success.");
            return "intermittent 500 err endpoint success";
        }
    }
}

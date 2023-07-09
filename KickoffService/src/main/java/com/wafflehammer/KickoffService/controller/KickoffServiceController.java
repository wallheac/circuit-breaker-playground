package com.wafflehammer.KickoffService.controller;

import com.wafflehammer.KickoffService.service.KickoffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kickoff")
public class KickoffServiceController {

    final static Logger log = LogManager.getLogger(KickoffServiceController.class);

    @Autowired
    private KickoffService kickoffService;

    @GetMapping("/random-errors")

    public String randomErrors() {
        kickoffService.callRandomServiceA();
        return "Started";
    }
}

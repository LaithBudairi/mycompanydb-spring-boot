package com.exalt.mycompany.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoggingController {

    private final static Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @RequestMapping("/")
    public String getLogs() {
        logger.error("I am error");
        logger.warn("I am warning");
        logger.info("I am info");
        logger.debug("I am debug");
        logger.debug("I am trace");

        return "Welcome to API";
    }
}

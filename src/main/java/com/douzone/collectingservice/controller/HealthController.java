package com.douzone.collectingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collecting-service")
@RequiredArgsConstructor
public class HealthController {
    private final Environment env;

    @GetMapping("/health-check")
    public String healthCheck(){
        return String.format("this server port is : %s", env.getProperty("local.server.port"));
    }
}

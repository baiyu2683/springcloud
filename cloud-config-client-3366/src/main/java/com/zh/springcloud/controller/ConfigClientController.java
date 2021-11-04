package com.zh.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigClientController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;


    @GetMapping("/applicationname")
    public String getApplicationName() {
        return applicationName;
    }

    @GetMapping("/serverport")
    public Integer getServerPort() {
        return serverPort;
    }
}

package com.zh.cloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
// 动态刷新
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public String getConfigInfo() {
        return configInfo;
    }
}

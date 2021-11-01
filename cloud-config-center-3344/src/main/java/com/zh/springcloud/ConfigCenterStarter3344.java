package com.zh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterStarter3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterStarter3344.class, args);
    }
}

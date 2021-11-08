package com.zh.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelStarter8401 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelStarter8401.class, args);
    }

}

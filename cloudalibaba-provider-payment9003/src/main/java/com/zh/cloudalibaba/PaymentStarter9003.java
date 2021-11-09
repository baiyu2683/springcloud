package com.zh.cloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 测试sentinel整合ribbon feign
 * 实现服务熔断
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentStarter9003 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentStarter9003.class, args);
    }
}

package com.zh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentStarter8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentStarter8002.class, args);
    }
}

package com.zh.springcloud;

import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboradStarter9001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboradStarter9001.class, args);
    }
}

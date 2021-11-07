package com.zh.cloudalibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/echo")
public class NacosController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${service.provider.payment}")
    private String paymentService;

    @GetMapping("/app-name")
    public String echoAppName() {
//        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
//        String path = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
//        System.out.println("request path: " + path);
        return restTemplate.getForObject(paymentService + "/echo/" + appName, String.class);
    }

}

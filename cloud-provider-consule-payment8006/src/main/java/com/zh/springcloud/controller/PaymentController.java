package com.zh.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/consul")
    public String paymentConsule() {
        return "spring cloud consul: " + serverPort + ".";
    }
}

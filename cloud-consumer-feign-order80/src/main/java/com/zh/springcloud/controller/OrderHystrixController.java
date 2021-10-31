package com.zh.springcloud.controller;

import com.zh.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/ok/{id}")
    public String getPaymentById(@PathVariable("id") Integer id) {
        return paymentHystrixService.ok(id);
    }

    @GetMapping("/payment/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.timeout(id);
    }
}

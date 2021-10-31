package com.zh.springcloud.controller;

import com.zh.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hystrix/ok/{id}")
    public String ok(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id) {
        return paymentService.paymentInfo_Timeout(id);
    }

    @GetMapping("/hystrix/breaker/{id}")
    public String breaker(@PathVariable("id") Integer id) {
        return paymentService.paymentCircuitBreaker(id);
    }
}

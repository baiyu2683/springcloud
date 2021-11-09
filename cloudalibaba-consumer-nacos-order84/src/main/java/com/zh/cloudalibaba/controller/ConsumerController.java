package com.zh.cloudalibaba.controller;

import com.zh.cloudalibaba.service.PaymentService;
import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/payment/get/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id) {
        return paymentService.get(id);
    }
}

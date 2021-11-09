package com.zh.cloudalibaba.service;

import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloudalibaba-sentinel-service", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> get(@PathVariable("id") Long id);
}

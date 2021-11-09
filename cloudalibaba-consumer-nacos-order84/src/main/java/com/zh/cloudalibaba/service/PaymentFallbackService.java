package com.zh.cloudalibaba.service;

import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> get(Long id) {
        return new CommonResult<>(404, "服务降级返回, payment fallback get : " + id);
    }
}

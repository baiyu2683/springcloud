package com.zh.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * payment服务的降级类
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String ok(Integer id) {
        return "paymentFallbackService ok";
    }

    @Override
    public String timeout(Integer id) {
        return "paymentFallbackService timeout";
    }

    @Override
    public String paymentZipkin() {
        return "paymentFallback Zipkin";
    }
}

package com.zh.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.zh.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "payment_global_fallback_handler")
@Slf4j
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/ok/{id}")
    public String getPaymentById(@PathVariable("id") Integer id) {
        return paymentHystrixService.ok(id);
    }

    @GetMapping("/payment/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeout_handler", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "2000")
    })
    public String paymentTimeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.timeout(id);
    }
    public String paymentTimeout_handler(@PathVariable("id") Integer id, Throwable throwable) {
        log.error("timeout异常： ", throwable);
        return "线程池: " + Thread.currentThread().getName() + "调用 paymentHystrixService.timeout()方法出现超时或者错误，id: " + id;
    }

    @GetMapping("/payment/exception/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeout_handler", commandProperties = {
//            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "2000")
//    })
    @HystrixCommand
    public String paymentException(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return paymentHystrixService.timeout(id);
    }

    public String payment_global_fallback_handler(Throwable throwable) {
        log.error("错误", throwable);
        return "全局异常处理。。。";
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return paymentHystrixService.paymentZipkin();
    }
}

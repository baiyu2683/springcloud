package com.zh.springcloud.controller;

import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.zh.springcloud.service.PaymentService;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommonResult<Long> create(@RequestBody Payment payment) {
        Long result = paymentService.create(payment);
        if (result > 0) {
            log.info("payment插入结果: " + result);
            return new CommonResult<>(200, "创建成功", payment.getId());
        }
        return new CommonResult<>(503, "创建失败");
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        log.info("调用服务: 8002");
        Payment payment = paymentService.getPaymentById(id);
        return new CommonResult<Payment>(200, "8002查询成功", payment);
    }

    @GetMapping("/lb")
    public String lb() {
        return "8002 lb";
    }

    @GetMapping("/feign/timeout")
    public String paymentTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "8002 timeout";
    }

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return "i am paymentzipkin server fall back, welcome, port: " + serverPort;
    }
}

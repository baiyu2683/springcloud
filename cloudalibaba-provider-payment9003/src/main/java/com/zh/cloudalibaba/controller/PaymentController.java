package com.zh.cloudalibaba.controller;

import com.zh.cloudalibaba.service.PaymentService;
import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        log.info("调用服务: " + serverPort);
        Payment payment = paymentService.getPaymentById(id);
        return new CommonResult<Payment>(200, serverPort + "查询成功", payment);
    }
}

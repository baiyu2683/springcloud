package com.zh.springcloud.controller;

import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zh.springcloud.service.PaymentService;

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
        return new CommonResult<Payment>(200, "查询成功", payment);
    }
}

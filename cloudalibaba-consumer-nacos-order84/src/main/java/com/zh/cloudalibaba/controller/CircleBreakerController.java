package com.zh.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zh.cloudalibaba.service.PaymentService;
import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class CircleBreakerController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/fallback/{id}")
//    @SentinelResource(value = "fallback")  // 没有兜底方法，宜昌市会走异常处理逻辑
//    @SentinelResource(value = "fallback", fallback = "fallbackHandler") // 仅配置fallback, fallback可以管理运行时异常，blockHandler管理配置违规（BlockException）
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") // 仅配置blockHandler,blockHandler可以管理sentinel配置违规(BlockException)
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "fallbackHandler")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = paymentService.get(id);
        if (id < 0) {
            throw new IllegalArgumentException("非法参数， id: " + id);
        } else if (result.getData() == null) {
            throw new NullPointerException("没有对应记录，id: " + id);
        }
        return result;
    }

    public CommonResult<Payment> blockHandler(@PathVariable("id") Long id, BlockException blockException) {
        return new CommonResult<>(404, "出现限流异常: " + blockException);
    }
    public CommonResult<Payment> fallbackHandler(@PathVariable("id") Long id, Throwable throwable) {
        return new CommonResult<>(404, "出现运行时异常: " + throwable);
    }
}

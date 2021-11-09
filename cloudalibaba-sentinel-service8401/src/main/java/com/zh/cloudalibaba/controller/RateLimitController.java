package com.zh.cloudalibaba.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zh.cloudalibaba.myhandler.CustomerBlockHandler;
import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按照资源名称限流测试ok", new Payment(1l, IdUtil.simpleUUID()));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName());
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按照资源名称限流测试ok", new Payment(1l, IdUtil.simpleUUID()));
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerExceptionSecond")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "自定义", new Payment(1l, IdUtil.simpleUUID()));
    }
}

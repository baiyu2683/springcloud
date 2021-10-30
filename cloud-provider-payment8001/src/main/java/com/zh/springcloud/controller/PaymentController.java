package com.zh.springcloud.controller;

import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;
import com.zh.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.List;
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
        log.info("调用服务: 8001");
        Payment payment = paymentService.getPaymentById(id);
        return new CommonResult<Payment>(200, "8001查询成功", payment);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String paymentInstanceName;

    @GetMapping("/discory")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service: " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(paymentInstanceName);
        for (ServiceInstance serviceInstance : instances) {
            log.info("instance: " + serviceInstance.getInstanceId() + "\n" + serviceInstance.getServiceId() + "\n" + serviceInstance.getHost() + "\n" + serviceInstance.getPort() + "\n" + serviceInstance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/lb")
    public String lb() {
        return "8001 lb";
    }

    @GetMapping("/feign/timeout")
    public String paymentTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "8001 timeout";
    }
}

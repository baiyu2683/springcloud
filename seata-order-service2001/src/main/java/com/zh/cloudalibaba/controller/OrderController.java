package com.zh.cloudalibaba.controller;

import com.zh.cloudalibaba.entities.Order;
import com.zh.cloudalibaba.service.OrderService;
import com.zh.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get/{id}")
    public CommonResult<Order> get(@PathVariable("id") Long id) {
        Order order = orderService.get(id);
        return new CommonResult<>(200, "ok", order);
    }
}

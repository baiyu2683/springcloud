package com.zh.cloudalibaba.controller;

import com.zh.cloudalibaba.entities.Order;
import com.zh.cloudalibaba.service.AccountService;
import com.zh.cloudalibaba.service.OrderService;
import com.zh.cloudalibaba.service.StorageService;
import com.zh.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create")
    public CommonResult<Long> create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功", order.getId());
    }
}

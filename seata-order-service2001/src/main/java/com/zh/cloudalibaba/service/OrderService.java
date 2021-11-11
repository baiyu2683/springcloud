package com.zh.cloudalibaba.service;

import com.zh.cloudalibaba.entities.Order;

/**
 * 订单服务
 */
public interface OrderService {

    Order get(Long id);

    void create(Order order);
}

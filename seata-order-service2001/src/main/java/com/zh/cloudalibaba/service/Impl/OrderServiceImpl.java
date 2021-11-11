package com.zh.cloudalibaba.service.Impl;

import com.zh.cloudalibaba.dao.OrderDao;
import com.zh.cloudalibaba.entities.Order;
import com.zh.cloudalibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order get(Long id) {
        return orderDao.get(1l);
    }

    @Override
    public void create(Order order) {

    }
}

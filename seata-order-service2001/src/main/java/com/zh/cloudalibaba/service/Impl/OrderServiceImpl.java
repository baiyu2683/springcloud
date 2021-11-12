package com.zh.cloudalibaba.service.Impl;

import com.zh.cloudalibaba.dao.OrderDao;
import com.zh.cloudalibaba.entities.Order;
import com.zh.cloudalibaba.service.AccountService;
import com.zh.cloudalibaba.service.OrderService;
import com.zh.cloudalibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StorageService storageService;

    @Override
    public Order get(Long id) {
        return orderDao.get(1l);
    }

    @Override
    @GlobalTransactional(name = "order-create", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("---> 开始新建订单");
        orderDao.create(order);
        log.info("---> 订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("---> 订单微服务调用库存，做扣减结束");
        log.info("---> 订单微服务调用账户服务, 扣钱");
        // 报错，测试全局事务是否成功
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("---> 订单微服务调用账户服务, 扣钱结束");

        // 修改订单状态
        log.info("----> 修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("----> 修改订单状态结束");

        log.info("----> 下订单成功结束。。");
    }
}

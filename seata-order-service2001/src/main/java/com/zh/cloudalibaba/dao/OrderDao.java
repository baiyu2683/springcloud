package com.zh.cloudalibaba.dao;

import com.zh.cloudalibaba.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    Order get(Long id);
    // 新建订单
    void create(Order order);
    // 修改订单状态，0 -> 1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}

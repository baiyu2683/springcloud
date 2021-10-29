package com.zh.springcloud.dao;

import com.zh.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    Long create(Payment payment);
    Payment getPaymentById(@Param("id") Long id);
}

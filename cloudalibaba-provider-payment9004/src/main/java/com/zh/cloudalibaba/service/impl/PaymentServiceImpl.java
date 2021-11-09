package com.zh.cloudalibaba.service.impl;

import com.zh.cloudalibaba.dao.PaymentDao;
import com.zh.cloudalibaba.service.PaymentService;
import com.zh.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public Long create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}

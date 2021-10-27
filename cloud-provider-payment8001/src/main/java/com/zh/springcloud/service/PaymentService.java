package com.zh.springcloud.service;

import com.zh.springcloud.entities.Payment;

public interface PaymentService {
   Long create(Payment payment);
   Payment getPaymentById(Long id);
}

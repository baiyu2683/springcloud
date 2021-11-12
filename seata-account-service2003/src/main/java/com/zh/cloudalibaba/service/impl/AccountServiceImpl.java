package com.zh.cloudalibaba.service.impl;

import com.zh.cloudalibaba.dao.AccountDao;
import com.zh.cloudalibaba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        // 报错，测试全局事务
        throw new RuntimeException("");
//        accountDao.decrease(userId, money);
    }
}

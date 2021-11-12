package com.zh.cloudalibaba.service.impl;

import com.zh.cloudalibaba.dao.StorageDao;
import com.zh.cloudalibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }
}

package com.zh.cloudalibaba.myhandler;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zh.springcloud.entities.CommonResult;
import com.zh.springcloud.entities.Payment;

public class CustomerBlockHandler {

    public static CommonResult handlerExceptionFirst(BlockException e) {
        return new CommonResult(444, "客户自定义，global - 1", new Payment(1l, IdUtil.simpleUUID()));
    }

    public static CommonResult handlerExceptionSecond(BlockException e) {
        return new CommonResult(444, "客户自定义，global - 2", new Payment(2l, IdUtil.simpleUUID()));
    }

}

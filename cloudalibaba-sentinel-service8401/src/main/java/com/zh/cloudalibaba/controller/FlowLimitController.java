package com.zh.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "----testA";
    }

    @GetMapping("/testB")
    public String testB() {
        System.out.println("时间戳: " + System.currentTimeMillis() + "线程: " + Thread.currentThread().getName());
        return "-----testB";
    }

    /**
     * 测试Sentinel的熔断降级规则中的RT(平均时间)规则
     * 在时间窗口内，qps>=5 且 请求时间大于阈值，则熔断
     * @return
     */
    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "-----testD";
    }

    /**
     * 测试Sentinel的熔断降级规则中的异常比例规则
     * 在时间窗口内，异常超过比例，则熔断。
     * @return
     */
    @GetMapping("/testC")
    public String testC() {
        int a = 10 / 0;
        return "-----testC";
    }

    @GetMapping("/hotkey")
    @SentinelResource(value = "hotKey", blockHandler = "deal_testHotKey") // 指定资源名
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "-----testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException e) {
        return "----deal_testHotKey" + p1 + ", " + p2 + ", exception: " + e;
    }
}

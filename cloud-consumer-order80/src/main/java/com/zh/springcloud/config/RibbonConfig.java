package com.zh.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.zh.springcloud.ribbon.rule.CustomRibbonRule;
import org.springframework.context.annotation.Bean;

/**
 * 需要条件引入，所以这里不引入
 */
public class RibbonConfig {

    @Bean
    public IRule myRule() {
        return new CustomRibbonRule();
    }
}

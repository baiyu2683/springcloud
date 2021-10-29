package com.zh.springcloud.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/**
 * RibbonConfig配置的自动配置类
 */
@Configuration
@ConditionalOnProperty(value = "custom.dev.enable", havingValue = "true")
@RibbonClients(defaultConfiguration = RibbonConfig.class)
public class RibbonConfiguration {
}

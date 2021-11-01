package com.zh.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用注入方式都见路由
 */
@Configuration
public class GateWayConfig {
    /**
     * 配置一个路由名称是path_rout_baidu的路由
     * 路由规则： 当访问/guonei时，转发到http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_baidu",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
        return routes.build();
    }
}

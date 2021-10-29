package com.zh.springcloud.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomRibbonRule extends AbstractLoadBalancerRule {

    private AtomicInteger counter = new AtomicInteger();

    private int getAndIncrement(int size) {
        for (;;) {
            int current = counter.get();
            int next = current >= Integer.MAX_VALUE ? 0 : (current + 1) % size;
            if (counter.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    public Server choose(ILoadBalancer loadBalancer, Object key) {
        if (loadBalancer == null) {
            return null;
        }
        Server server = null;
        int count = 0;
        while (server == null || count++ < 10) {
            List<Server> reachableServers = loadBalancer.getReachableServers();
            int reachableServerCount = reachableServers.size();
            if (reachableServerCount == 0) {
                return null;
            }
            int index = getAndIncrement(reachableServerCount);
            server = reachableServers.get(index);
            if (server == null) {
                Thread.yield();
                continue;
            }
            if (server.isAlive() && server.isReadyToServe()) {
                return server;
            }
        }
        if (count >= 10) {
            System.out.println("十次查询没查到可用服务器");
        }
        return server;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }
}

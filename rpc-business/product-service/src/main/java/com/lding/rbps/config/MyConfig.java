package com.lding.rbps.config;

import com.lding.rarr.common.RpcRegistry;
import com.lding.rars.common.RpcServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    private static final String ZK_SERVER_ADDR = "192.168.0.10:2181,192.168.0.11:2181,192.168.0.12:2181";

    @Bean
    public RpcRegistry rpcRegistry(){
        return new RpcRegistry(ZK_SERVER_ADDR);
    }

    @Bean
    public RpcServer rpcServer(ApplicationContext applicationContext, RpcRegistry rpcRegistry) {
        // 启动rpcServer服务
        // 这里可以更改端口（最好连tomcat的端口都改了）等信息模拟多个服务器
        // 当然肯定实现需要开启zookeeper集群的
        return new RpcServer("127.0.0.1", 9000, applicationContext, rpcRegistry);
    }
}

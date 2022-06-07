package com.lding.rbps.config;

import com.lding.rars.common.RpcServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public RpcServer rpcServer(ApplicationContext applicationContext) {
        // 启动rpcServer服务
        return new RpcServer("127.0.0.1",9000, applicationContext);
    }
}

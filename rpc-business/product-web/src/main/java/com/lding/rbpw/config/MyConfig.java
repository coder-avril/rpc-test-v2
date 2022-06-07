package com.lding.rbpw.config;

import com.lding.rarct.common.RpcClient;
import com.lding.rarct.common.RpcProxy;
import com.lding.rbpc.service.IProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public RpcClient rpcClient(){
        return new RpcClient("127.0.0.1", 9000);
    }

    @Bean
    public RpcProxy rpcProxy(RpcClient rpcClient){
        return new RpcProxy(rpcClient);
    }

    @Bean
    public IProductService productService(RpcProxy rpcProxy){
        return rpcProxy.getProxy(IProductService.class);
    }
}

package com.lding.rbpw.config;

import com.lding.rarct.common.RpcClient;
import com.lding.rarct.common.RpcProxy;
import com.lding.rarr.common.RpcDiscover;
import com.lding.rbpc.service.IProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    private static final String ZK_SERVER_ADDR = "192.168.0.10:2181,192.168.0.11:2181,192.168.0.12:2181";

    @Bean
    public RpcDiscover rpcDiscover() throws Exception {
        return new RpcDiscover(ZK_SERVER_ADDR);
    }

    @Bean
    public RpcClient rpcClient(RpcDiscover rpcDiscover){
        return new RpcClient(rpcDiscover);
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

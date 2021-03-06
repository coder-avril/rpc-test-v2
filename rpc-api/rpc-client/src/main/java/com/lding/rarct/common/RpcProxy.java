package com.lding.rarct.common;

import com.lding.rarc.common.RpcRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcProxy {
    private RpcClient rpcClient;

    //通用的代理对象,可以代理任何接口
    public <T> T getProxy(Class<T> interfaceClass){
        T objectProxy = (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),//类加载器
                new Class<?>[]{interfaceClass}, // 目标对象的接口
                new InvocationHandler() {
                    //proxy 代理对象  method 代理的方法  args 实际参数列表
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 1 创建客户端连接
                        // 2 构造请求参数
                        RpcRequest rpcRequest = new RpcRequest();
                        rpcRequest.setClassName(interfaceClass.getSimpleName());
                        rpcRequest.setMethodName(method.getName());
                        rpcRequest.setParameters(args);
                        rpcRequest.setParameterTypes(method.getParameterTypes());
                        // 3 发送请求
                        // 4 返回请求结果
                        Object result = rpcClient.send(rpcRequest);
                        return result;
                    }
                }
        );
        return objectProxy;
    }
}

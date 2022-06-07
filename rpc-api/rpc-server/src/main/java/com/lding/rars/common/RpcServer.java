package com.lding.rars.common;

import com.lding.rars.handle.RpcServerHandler;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import javax.annotation.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@NoArgsConstructor
public class RpcServer {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private int port ;
    private String hostname;
    private ApplicationContext applicationContext;

    public RpcServer(String hostname,int port, ApplicationContext applicationContext) {
        this.hostname = hostname;
        this.port = port;
        this.applicationContext = applicationContext;
    }

    //服务启动类--> 启动一个ServerSocket
    @PostConstruct
    public void startup(){
        try {
            //1 创建一个socket通信
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动成功, 等待客户端连接.....");
            //2 接收客户端请求
            while(true){
                Socket socket = serverSocket.accept();//等待客户端连接 如果没有客户端连接, 会阻塞在这里
                //处理请求--> 使用多线程技术--> 线程池技术
                executorService.submit(new RpcServerHandler(socket, applicationContext));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

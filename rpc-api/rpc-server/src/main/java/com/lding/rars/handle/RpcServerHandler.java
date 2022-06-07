package com.lding.rars.handle;

import com.lding.rarc.common.RpcRequest;
import com.lding.rarc.common.RpcResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcServerHandler implements Runnable  {
    private Socket socket;
    private ApplicationContext applicationContext;

    @Override
    public void run() {
        try(
            ObjectInputStream ois =new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
        ){
            //读取内容
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            System.out.println("服务端收到客户端的请求: obj = " + rpcRequest);
            //调用业务方法执行代码
            //1 通过类名找到对应的Bean对象
            //IProductService
            String className = rpcRequest.getClassName();
            Object bean = applicationContext.getBean(className);
            //字节码对象
            Class<?> clazz = bean.getClass();
            //2 通过反射调用类的对应方法
            String methodName = rpcRequest.getMethodName();
            Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
            Object[] parameters = rpcRequest.getParameters();
            //方法对象
            Method method = null;
            //反射的返回结果
            Object result = null;
            if(parameterTypes==null || parameterTypes.length==0){
                method=clazz.getMethod(methodName);
                result =method.invoke(bean);
            }else{
                method = clazz.getMethod(methodName, parameterTypes);
                result=method.invoke(bean, parameters);
            }
            //发送数据到客户端
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.setResult(true);
            rpcResponse.setResult(result);
            oos.writeObject(rpcResponse);
            //刷新缓冲区域
            oos.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

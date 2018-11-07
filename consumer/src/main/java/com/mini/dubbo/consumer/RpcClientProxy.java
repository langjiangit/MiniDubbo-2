package com.mini.dubbo.consumer;

import com.mini.dubbo.api.IHelloService;
import com.mini.dubbo.core.netty.NettyClient;
import com.mini.dubbo.core.netty.Request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yzy on 2018/11/6.
 */
public class RpcClientProxy {
    IServiceDiscovery serviceDiscovery;
    public RpcClientProxy(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public <T> T  create(final Class<T> interfaceClazz) {

         return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(),
                 new Class<?>[]{interfaceClazz}, new InvocationHandler() {
                     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                         Request request = new Request();
                         request.setClassName(interfaceClazz.getName());
                         request.setMethodName(method.getName());
                         request.setTypes(method.getParameterTypes());
                         request.setParams(args);

                         String url = serviceDiscovery.discover(interfaceClazz.getName());

                         NettyClient nettyClient = new NettyClient();
                         String ip = url.split(":")[0];
                         String port = url.split(":")[1];

                         String respone = nettyClient.send(Integer.parseInt(port),ip,request);

                         return respone;
                     }
                 });

    }
}

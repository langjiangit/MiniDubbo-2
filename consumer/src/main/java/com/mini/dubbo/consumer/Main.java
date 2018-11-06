package com.mini.dubbo.consumer;

import com.mini.dubbo.api.IGoodbyeService;
import com.mini.dubbo.api.IHelloService;

/**
 * Created by yzy on 2018/11/3.
 */
public class Main {
    public static void main(String[] args){

        IServiceDiscovery serviceDiscovery = new ServiceDiscovery();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(serviceDiscovery);


        IHelloService helloService = rpcClientProxy.create(IHelloService.class);
        IGoodbyeService goodbyeService = rpcClientProxy.create(IGoodbyeService.class);



        helloService.sayHello("spring");
        helloService.sayHello("cloud");

        goodbyeService.sayByeBye("struts");

    }
}

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



       //  helloService.sayHello("spring");

       String str1 =  helloService.sayHello("cloud");
        System.out.println("str="+str1);

        String resp = goodbyeService.sayByeBye("struts");


        System.out.println("resp="+resp);

    }
}

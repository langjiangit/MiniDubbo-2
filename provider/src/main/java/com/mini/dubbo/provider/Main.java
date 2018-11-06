package com.mini.dubbo.provider;

import com.mini.dubbo.api.IGoodbyeService;
import com.mini.dubbo.api.IHelloService;
import com.mini.dubbo.core.ServiceContainer;
import com.mini.dubbo.core.netty.NettyServer;

/**
 * Created by yzy on 2018/11/5.
 */
public class Main {
    public static void main(String[] args) throws Exception {


        IRegisterCenter registerCenter  = new RegisterCenterImpl();
        IHelloService helloService = new HelloService();
        IGoodbyeService goodbyeService = new GoodbyeService();

        //缓存服务实例,key必须是IHelloService.class.getName(),和HelloService的注解保持一致
        ServiceContainer.set(IHelloService.class.getName(),helloService);
        ServiceContainer.set(IGoodbyeService.class.getName(),goodbyeService);

        //发布服务
        registerCenter.publishService(helloService,goodbyeService);

        NettyServer nettyServer = new NettyServer();
        //启动服务监听
        nettyServer.bind(9999);



    }
}

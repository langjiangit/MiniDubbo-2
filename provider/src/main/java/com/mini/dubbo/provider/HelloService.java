package com.mini.dubbo.provider;

import com.mini.dubbo.api.IHelloService;
import com.mini.dubbo.api.RpcAnnotation;

/**
 * Created by yzy on 2018/11/3.
 */

@RpcAnnotation(IHelloService.class)
public class HelloService implements IHelloService {


    public String sayHello(String str) {
        return "hello resp:" + str;
    }
}

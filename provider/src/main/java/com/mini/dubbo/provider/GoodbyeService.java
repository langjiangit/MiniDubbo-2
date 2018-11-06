package com.mini.dubbo.provider;

import com.mini.dubbo.api.IGoodbyeService;
import com.mini.dubbo.api.RpcAnnotation;

/**
 * Created by yzy on 2018/11/6.
 */
@RpcAnnotation(IGoodbyeService.class)
public class GoodbyeService implements IGoodbyeService {
    public String sayByeBye(String str) {
        return "byebye resp:" + str;
    }
}

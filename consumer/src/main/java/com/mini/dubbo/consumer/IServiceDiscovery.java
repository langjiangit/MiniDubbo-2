package com.mini.dubbo.consumer;

/**
 * Created by yzy on 2018/11/6.
 */
public interface IServiceDiscovery {

    public String discover(String serviceName);
}

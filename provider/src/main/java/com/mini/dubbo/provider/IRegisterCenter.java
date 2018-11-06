package com.mini.dubbo.provider;

/**
 * Created by yzy on 2018/11/5.
 */
public interface IRegisterCenter {
    void register(String serviceName, String address);
    void publishService(Object... services);

}

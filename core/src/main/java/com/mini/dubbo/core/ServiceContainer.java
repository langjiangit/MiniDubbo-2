package com.mini.dubbo.core;

import java.util.HashMap;

/**
 * Created by yzy on 2018/11/6.
 */
public class ServiceContainer {

    private  static HashMap<String ,Object> map = new HashMap();
    public static void set(String serviceName,Object object){
        map.put(serviceName,object);
    }

    public static Object get(String name){
        return map.get(name);
    }
}

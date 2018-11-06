package com.mini.dubbo.provider;

import com.mini.dubbo.api.RpcAnnotation;
import com.mini.dubbo.api.ZkConfig;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by yzy on 2018/11/5.
 */
public class RegisterCenterImpl implements IRegisterCenter {


    String serviceAddress = "127.0.0.1:9999";

    RetryPolicy retryPolicy;
    CuratorFramework client;

    RegisterCenterImpl(){
        retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder().connectString(ZkConfig.ZK_SERVER)
                .sessionTimeoutMs(2000)
                .connectionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace(ZkConfig.ZK_NAME_SPACE)
                .build();
        client.start();
    }

    public void register(String serviceName, String address) {

        String servicePath = ZkConfig.ZK_REGISTER_PATH + serviceName;
        try {
            if(client.checkExists().forPath(servicePath) == null){

                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath,"0".getBytes());

            }

            String addressPath = servicePath + "/" + address;
            String rsNode = client.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath,"0".getBytes());
            System.out.println("服务"+serviceName+"注册成功"+rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void publishService(Object... services){
        for(Object service : services){
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            register(serviceName,serviceAddress);
        }
    }
}

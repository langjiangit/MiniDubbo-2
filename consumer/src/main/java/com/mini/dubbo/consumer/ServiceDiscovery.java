package com.mini.dubbo.consumer;

import com.mini.dubbo.api.ZkConfig;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Collections;
import java.util.List;

/**
 * Created by yzy on 2018/11/6.
 */
public class ServiceDiscovery implements IServiceDiscovery {

    RetryPolicy retryPolicy;
    CuratorFramework client;
    List<String> repos;

    ServiceDiscovery(){
        retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder().connectString(ZkConfig.ZK_SERVER)
                .sessionTimeoutMs(2000)
                .connectionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace(ZkConfig.ZK_NAME_SPACE)
                .build();
        client.start();
    }

    public String discover(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH + serviceName;
        try {
            //  /registrys/服务的包名----->下面有多个子节点，因为一个服务会部署多台机器,通过负载均衡算法返回ip:port
            repos = client.getChildren().forPath(path);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //动态感知服务器节点的上下线

        if(repos == null || repos.size() == 0 ){
            System.out.println("ERROR:discover fail,serviceName is " + serviceName);
            return null;
        }

        LoadBalance  loadBalance = new RandomLoadBalance();
        return  loadBalance.select(repos);
    }
}

package com.mini.dubbo.consumer;

import java.util.List;
import java.util.Random;

/**
 * Created by yzy on 2018/11/6.
 */
public class RandomLoadBalance implements  LoadBalance
{
    public String select(List<String> repos) {
        Random random = new Random();
        int index = random.nextInt(repos.size());
        return repos.get(index);
    }
}

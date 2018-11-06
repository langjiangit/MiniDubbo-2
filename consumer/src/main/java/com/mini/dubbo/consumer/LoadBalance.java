package com.mini.dubbo.consumer;

import java.util.List;

/**
 * Created by yzy on 2018/11/6.
 */
public interface LoadBalance {
    String select(List<String> repos);
}

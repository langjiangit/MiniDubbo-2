package com.mini.dubbo.core.netty;

/**
 * Created by yzy on 2018/11/4.
 */
import java.io.Serializable;

public class Response implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6236340795725143988L;
    public String body;

    public Response(String body) {
        this.body = body;
    }
}


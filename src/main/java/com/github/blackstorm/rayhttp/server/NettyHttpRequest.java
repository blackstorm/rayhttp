package com.github.blackstorm.rayhttp.server;

import io.netty.handler.codec.http.HttpRequest;

public class NettyHttpRequest {

    private final HttpRequest httpRequest;

    public NettyHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }
}

package com.github.blackstorm.rayhttp.server;

import com.github.blackstorm.rayhttp.core.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;

public class HttpRequestHandler extends SimpleChannelInboundHandler<HttpObject> {

    private final Router router;

    public HttpRequestHandler(Router router) {
        this.router = router;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof io.netty.handler.codec.http.HttpRequest) {
            NettyHttpRequest request = new NettyHttpRequest((HttpRequest) msg);
            return;
        }

    }
}

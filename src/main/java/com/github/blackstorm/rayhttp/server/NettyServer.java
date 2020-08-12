package com.github.blackstorm.rayhttp.server;

import com.github.blackstorm.rayhttp.core.Router;
import com.github.blackstorm.rayhttp.core.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer implements Server {

    /**
     * configs
     */
    private final int port;
    private final int bossPoolSize;
    private final int workPoolSize;
    private final Router router;

    private Channel channel;

    public NettyServer(int port,
                       int bossPoolSize,
                       int workPoolSize,
                       Router router) {
        this.port = port;
        this.bossPoolSize = bossPoolSize;
        this.workPoolSize = workPoolSize;
        this.router = router;
    }

    @Override
    public void start() {
        try {
            start0();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        channel.close();
    }

    private void start0() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        // boos and work
        EventLoopGroup boss = new NioEventLoopGroup(this.bossPoolSize);
        EventLoopGroup work = new NioEventLoopGroup(this.workPoolSize);
        bootstrap.group(boss, work).channel(NioServerSocketChannel.class);
        // handle
        bootstrap.childHandler(new HttpServerInitializer(router));
        // bind
        channel = bootstrap.bind(this.port).sync().channel();
    }


}

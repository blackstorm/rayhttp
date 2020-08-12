package com.github.blackstorm.rayhttp;

import com.github.blackstorm.rayhttp.server.NettyServer;
import com.github.blackstorm.rayhttp.core.Server;

public final class RayHttp {

    public static Server create(int port) {
        final int cores = Runtime.getRuntime().availableProcessors();
        return new NettyServer(port, 1, cores + 1);
    }

    public static void server(int port) {
        create(port).start();
    }

}

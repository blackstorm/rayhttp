package com.github.blackstorm.rayhttp;

import com.github.blackstorm.rayhttp.core.Route;
import com.github.blackstorm.rayhttp.core.Router;
import com.github.blackstorm.rayhttp.router.HashRouter;
import com.github.blackstorm.rayhttp.server.NettyServer;
import com.github.blackstorm.rayhttp.core.Server;

public final class RayHttp {

    public static Server create(int port, Router router) {
        final int cores = Runtime.getRuntime().availableProcessors();
        return new NettyServer(port, 1, cores + 1, router);
    }

    public static void server() {

    }


    private static class RayHttpBuilder {
        private int port = 8080;
        private Router router;

        public NettyServer build() {
            checkInitRouter();
            final int cores = Runtime.getRuntime().availableProcessors();
            return  new NettyServer(port, 1, cores + 1, router);
        }

        public void addRoute(Route route) {
            checkInitRouter();
            router.addRoute(route);
        }

        public void addInterceptor(Router.Interceptor interceptor) {
            checkInitRouter();
        }

        void checkInitRouter() {
            if (router == null) router = new HashRouter();
        }

        boolean volidate() {
            return true;
        }

    }


}

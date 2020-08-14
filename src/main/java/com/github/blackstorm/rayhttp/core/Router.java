package com.github.blackstorm.rayhttp.core;

public interface Router {

    Router addRoute(Method method, String path, Handler<?> handler);

    Router addInterceptor(String path, Interceptor interceptor);

    interface Interceptor {
        boolean before(RequestContext ctx);
        default boolean after(RequestContext ctx) {
            return true;
        }
    }

    interface Handler<T> {
        T service(RequestContext ctx);
    }

}

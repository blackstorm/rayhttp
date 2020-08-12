package com.github.blackstorm.rayhttp.core;

public interface Router {

    Router addRoute();

    Router addInterceptor();

    interface Interceptor {
        boolean before(RequestContext ctx);
        default boolean after(RequestContext ctx) {
            return true;
        }
    }

}

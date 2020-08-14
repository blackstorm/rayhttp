package com.github.blackstorm.rayhttp.core;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class DefaultRouter implements Router {

    private final Map<String, FilterHandler[]> handlers = new HashMap<>();

    @Override
    public Router addRoute(Method method, String path, Handler<?> handler) {
        FilterHandler[] filterHandlers;
        if ((filterHandlers = handlers.get(path)) == null) {
            filterHandlers = new FilterHandler[Method.len()];
        }
        if (filterHandlers[method.index()] != null) {
            log.warn("{} old handler has been removed", path);
            filterHandlers[method.index()] = new FilterHandler(handler);
        }
        return this;
    }

    @Override
    public Router addInterceptor(String path, Interceptor interceptor) {
        final String prefix = path.endsWith("/**") ? path.substring(0, path.length() - 3) : path;
        handlers.forEach((k, v) -> {
            if (k.startsWith(prefix)) {
                for (FilterHandler handler : v) {
                    handler.addInterceptor(interceptor);
                }
            }
        });
        return this;
    }

    public Optional<FilterHandler> match(Method method, String path) {
        FilterHandler[] filterHandles;
        if ((filterHandles = handlers.get(path))== null) return Optional.empty();
        FilterHandler handler;
        if ((handler = filterHandles[method.index()]) == null) return Optional.empty();
        return Optional.of(handler);
    }

    abstract static class BaseHandler {
        Handler<?> handler;

        private  BaseHandler(Handler<?> handler) {
            this.handler = handler;
        }

        public Object handle(RequestContext ctx) {
            return handler.service(ctx);
        }
    }

    static class FilterHandler extends BaseHandler {
        List<Interceptor> interceptors;

        FilterHandler(Handler<?> handler) {
            super(handler);
        }

        private void addInterceptor(Interceptor interceptor) {
            if (interceptors == null) interceptors = new LinkedList<>();
            interceptors.add(interceptor);
        }

        private void doBefore(RequestContext ctx) {
            if (interceptors != null) {
                for (Interceptor interceptor : interceptors) {
                    if (!interceptor.before(ctx)) break;
                }
            }
        }

        private void doAfter(RequestContext ctx) {
            if (interceptors != null) {
                for (Interceptor interceptor : interceptors) {
                    if (!interceptor.after(ctx)) break;
                }
            }
        }

    }

}

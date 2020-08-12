package com.github.blackstorm.rayhttp.core;

public class Route {

    private final String path;
    private final String method;

    public Route(String path, String method) {
        this.path = path;
        this.method = method;
    }
}

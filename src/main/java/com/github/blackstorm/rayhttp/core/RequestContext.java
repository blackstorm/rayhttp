package com.github.blackstorm.rayhttp.core;

public class RequestContext {

    private final Request request;

    public RequestContext(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return this.request;
    }

}

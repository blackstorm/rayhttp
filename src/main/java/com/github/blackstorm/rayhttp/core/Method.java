package com.github.blackstorm.rayhttp.core;

public enum Method {
    /**
     * http get
     */
    GET(0),
    /**
     * http post
     */
    POST(1);

    private final int index;

    Method(int index) {
        this.index = index;
    }

    int index() {
        return this.index;
    }

    static int len() {
        return values().length;
    }

}

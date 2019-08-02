package com.chen.app_ec.data;

public enum Foodtype {
    MORNING("早餐"),
    NOON("午餐"),
    DINNER("晚餐");
    public final String name;
    Foodtype(String name) {
        this.name = name;
    }
}

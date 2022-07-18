package com.balarawool;

public class Flow {

    static <T> Value<T> startWith(T t) {
        return new Value<>(t);
    }
}
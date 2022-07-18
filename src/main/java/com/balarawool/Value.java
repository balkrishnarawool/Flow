package com.balarawool;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Value<T> {
    private T t;

    Value(T t) {
        this.t = t;
    }

    public <T2> Value2<T, T2> andThenAdd(T2 t2) {
        return new Value2<>(t, t2);
    }

    public <U> LazyFunction<T, U> andThen(Function<T, U> f) {
        return new LazyFunction<>(t, f);
    }

    public <V> FutureFunction<T, V> andThenAsync(Function<T, CompletableFuture<V>> f) {
        return new FutureFunction<>(t, f);
    }

    public T done() {
        return t;
    }
}
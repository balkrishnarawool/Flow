package com.balarawool;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Value2<T, T2> {
    private T t;
    private T2 t2;

    Value2(T t, T2 t2) {
        this.t = t;
        this.t2 = t2;
    }

    public <U> LazyBiFunction<T, T2, U> andThen(BiFunction<T, T2, U> f) {
        return new LazyBiFunction<>(t, t2, f);
    }

    public <V> FutureBiFunction<T, T2, V> andThenAsync(BiFunction<T, T2, CompletableFuture<V>> f) {
        return new FutureBiFunction<>(t, t2, f);
    }

    public T done() {
        return t;
    }
}
package com.balarawool;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LazyBiFunction<T, T2, U> {
    private T t;
    private T2 t2;
    private BiFunction<T, T2, U> f;
    LazyBiFunction(T t, T2 t2, BiFunction<T, T2, U> f) {
        this.t = t;
        this.t2 = t2;
        this.f = f;
    }

    public <V> LazyBiFunction<T, T2, V> andThen(Function<U, V> f) {
        BiFunction<T, T2, V> f2 = this.f.andThen(f);
        return new LazyBiFunction<>(t, t2, f2);
    }

    public <V> FutureBiFunction<T, T2, V> andThenAsync(Function<U, CompletableFuture<V>> f) {
        BiFunction<T, T2, CompletableFuture<V>> f2 = this.f.andThen(f);
        return new FutureBiFunction<>(t, t2, f2);
    }
    public U done() {
        return f.apply(t, t2);
    }
}

package com.balarawool;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FutureBiFunction<T, T2, U> {
    private T t;
    private T2 t2;

    private BiFunction<T, T2, CompletableFuture<U>> f;

    FutureBiFunction(T t, T2 t2, BiFunction<T, T2, CompletableFuture<U>> f) {
        this.t = t;
        this.t2 = t2;
        this.f = f;
    }

    public <V> FutureBiFunction<T, T2, V> andThen(Function<U, V> f) {
        BiFunction<T, T2, CompletableFuture<V>> f2 = (t, t2) -> this.f.apply(t, t2).thenApply(f);
        return new FutureBiFunction<>(t, t2, f2);
    }

    public <V> FutureBiFunction<T, T2, V> andThenAsync(Function<U, CompletableFuture<V>> f) {
        BiFunction<T, T2, CompletableFuture<V>> f2 = (t, t2) -> this.f.apply(t, t2).thenCompose(f);
        return new FutureBiFunction<>(t, t2, f2);
    }

    public CompletableFuture<U> done() {
        return f.apply(t, t2);
    }
}

package com.balarawool;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class FutureFunction<T, U> {
    private T t;

    private Function<T, CompletableFuture<U>> f;

    FutureFunction(T t, Function<T, CompletableFuture<U>> f) {
        this.t = t;
        this.f = f;
    }

    public <V> FutureFunction<T, V> andThen(Function<U, V> f) {
        Function<T, CompletableFuture<V>> f2 = t -> this.f.apply(t).thenApply(f);
        return new FutureFunction<>(t, f2);
    }

    public <V> FutureFunction<T, V> andThenAsync(Function<U, CompletableFuture<V>> f) {
        Function<T, CompletableFuture<V>> f2 = t -> this.f.apply(t).thenCompose(f);
        return new FutureFunction<>(t, f2);
    }

    public CompletableFuture<U> done() {
        return f.apply(t);
    }
}

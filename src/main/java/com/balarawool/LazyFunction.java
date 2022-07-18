package com.balarawool;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class LazyFunction<T, U> {
    private T t;
    private Function<T, U> f;
    LazyFunction(T t, Function<T, U> f) {
        this.t = t;
        this.f = f;
    }

    public <V> LazyFunction<T, V> andThen(Function<U, V> f) {
        Function<T, V> f2 = this.f.andThen(f);
        return new LazyFunction<>(t, f2);
    }

    public <V> FutureFunction<T, V> andThenAsync(Function<U, CompletableFuture<V>> f) {
        Function<T, CompletableFuture<V>> f2 = this.f.andThen(f);
        return new FutureFunction<>(t, f2);
    }
    public U done() {
        return f.apply(t);
    }
}

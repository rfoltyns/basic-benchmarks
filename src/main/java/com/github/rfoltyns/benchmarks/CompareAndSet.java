package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.atomic.AtomicLong;

public class CompareAndSet implements Test {

    private final AtomicLong state = new AtomicLong();

    @Override
    public void test(final Blackhole fox) {
        fox.consume(test());
    }

    @Override
    public long test() {

        long current;
        do {
            current = state.get();
        } while (!state.compareAndSet(current, current + 1));

        return current + 1;

    }

    @Override
    public long getState() {
        return state.get();
    }

}
package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public final class Volatile implements BasicTest {

    private volatile long state = 0L;

    @Override
    public void test(final Blackhole fox) {
        fox.consume(state++);
    }

    @Override
    public long test() {
        return ++state;
    }

    @Override
    public long getState() {
        return state;
    }
}

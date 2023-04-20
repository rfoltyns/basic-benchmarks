package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public final class Monitor implements BasicTest {

    private long state = 0L;
    private final Object lock = new Object();

    @Override
    public void test(final Blackhole fox) {
        fox.consume(test());
    }

    @Override
    public long test() {

        synchronized (lock) {
            return ++state;
        }

    }

    public long getState() {
        return state;
    }
}

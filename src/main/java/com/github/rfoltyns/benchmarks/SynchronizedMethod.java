package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public final class SynchronizedMethod implements Test {

    private long state = 0L;

    @Override
    public synchronized void test(final Blackhole fox) {
        fox.consume(test());
    }

    @Override
    public synchronized long test() {
        return ++state;
    }

    @Override
    public long getState() {
        return state;
    }

}

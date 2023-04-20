package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public final class SynchronizedClass implements BasicTest {

    private long state = 0L;

    @Override
    public void test(final Blackhole fox) {
        fox.consume(test());
    }

    @Override
    public long test() {

        synchronized (SynchronizedClass.class)
        {
            return ++state;
        }

    }

    @Override
    public long getState() {
        return state;
    }

}

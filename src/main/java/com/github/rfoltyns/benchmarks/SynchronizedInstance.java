package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public final class SynchronizedInstance implements Test {

    private long state = 0L;

    @Override
    public void test(final Blackhole fox) {

        synchronized (this)
        {
            fox.consume(test());
        }

    }

    @Override
    public long test() {

        synchronized (this)
        {
            return ++state;
        }

    }

    @Override
    public long getState() {
        return state;
    }

}

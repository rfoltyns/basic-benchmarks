package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public final class NonVolatile implements Test {

    private long state = 0L;

    @Override
    public void test(final Blackhole fox) {
        fox.consume(test());
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

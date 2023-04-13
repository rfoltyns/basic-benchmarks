package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

public interface Test {
    void test(Blackhole fox);

    long getState();

    long test();
}

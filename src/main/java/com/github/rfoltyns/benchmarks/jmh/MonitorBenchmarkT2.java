package com.github.rfoltyns.benchmarks.jmh;

import com.github.rfoltyns.benchmarks.Monitor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(jvmArgsAppend = {
        "-ea",
        "-Xmx64m",
        "-Xms64m",
        "-XX:+AlwaysPreTouch",
        "-Djmh.pinned=true"
}
)
@Threads(2)
public class MonitorBenchmarkT2 {

    private final Monitor m = new Monitor();

    @Benchmark
    public void test(final Blackhole fox) {
        m.test(fox);
    }

    @TearDown
    public void tearDown() {
        System.out.println("Count: " + m.getState());
    }
}

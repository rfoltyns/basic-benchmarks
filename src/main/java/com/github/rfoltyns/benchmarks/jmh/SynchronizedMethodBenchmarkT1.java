package com.github.rfoltyns.benchmarks.jmh;

import com.github.rfoltyns.benchmarks.SynchronizedMethod;
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
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(jvmArgsAppend = {
        "-ea",
        "-Xmx64m",
        "-Xms64m",
        "-XX:+AlwaysPreTouch",
        "-Djmh.pinned=true"
}
)
@Threads(1)
public class SynchronizedMethodBenchmarkT1 {

    private final SynchronizedMethod sm = new SynchronizedMethod();

    @Benchmark
    public void test(final Blackhole fox) {
        sm.test(fox);
    }

    @TearDown
    public void tearDown() {
        System.out.println("Count: " + sm.getState());
    }

}

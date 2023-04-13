package com.github.rfoltyns.benchmarks.jmh;

/*-
 * All credits to Nitsan
 * Source: https://gist.githubusercontent.com/nitsanw/5522013/raw/f5cfe3e4e31edce2eb8496296cccd6eb77572153/gistfile1.java
 * Adapted to more recent JMH version
 */

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
public class FalseSharingBenchmarkPlain {
    final static int LONGS_IN_CACHELINE = Integer.getInteger("longs.in.cacheline", 8);
    final static int NUMBER_OF_THREADS = Integer.getInteger("threads", 2);
    final static AtomicInteger THREAD_INDEX = new AtomicInteger(0);

    @State(Scope.Benchmark)
    public static class SharedCounters {
        private final long[] array = new long[LONGS_IN_CACHELINE * (NUMBER_OF_THREADS + 2)];
    }

    @State(Scope.Thread)
    public static class ThreadIndex {
        /**
         * First shared access index points to location which is buffered to the
         * left by a cacheline but all others are lined up sequentially causing
         * false sharing between them. Last index points to a location which is
         * buffered to the right.
         */
        private final int falseSharedIndex = LONGS_IN_CACHELINE + THREAD_INDEX.getAndIncrement();

        /**
         * Each index is buffered by a cacheline to it's left and the last by a
         * cacheline to the right.
         */
        private final int noSharingIndex = LONGS_IN_CACHELINE + (falseSharedIndex - LONGS_IN_CACHELINE) * LONGS_IN_CACHELINE;
    }

    @Benchmark
    public void notFalseShared(SharedCounters counters, ThreadIndex index) {
        long value = counters.array[index.noSharingIndex];
        counters.array[index.noSharingIndex] = value + 1;
    }

    @Benchmark
    public void falseShared(SharedCounters counters, ThreadIndex index) {
        long value = counters.array[index.falseSharedIndex];
        counters.array[index.falseSharedIndex] = value + 1;
    }

}

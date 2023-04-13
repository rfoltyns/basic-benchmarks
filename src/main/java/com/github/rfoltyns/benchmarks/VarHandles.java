package com.github.rfoltyns.benchmarks;

import org.openjdk.jmh.infra.Blackhole;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public final class VarHandles implements Test {

    private long value;
    private static final VarHandle VALUE;

    static {
        try {
            final MethodHandles.Lookup l = MethodHandles.privateLookupIn(VarHandles.class, MethodHandles.lookup());
            VALUE = l.findVarHandle(VarHandles.class, "value", long.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public void test(final Blackhole fox) {
        fox.consume(test());
    }

    @Override
    public long test() {

        long current;

        do {
            current = (long) VALUE.get(this);
        } while (!VALUE.compareAndSet(this, current, current + 1));

        return current + 1;
    }

    @Override
    public long getState() {
        return value;
    }
}

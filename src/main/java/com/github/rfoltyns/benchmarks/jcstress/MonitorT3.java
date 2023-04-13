package com.github.rfoltyns.benchmarks.jcstress;

import com.github.rfoltyns.benchmarks.CompareAndSet;
import com.github.rfoltyns.benchmarks.Monitor;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.LLL_Result;

@JCStressTest
@Outcome.Outcomes({
        @Outcome(id="1, 2, 3", expect = Expect.ACCEPTABLE, desc="OK"),
        @Outcome(id="2, 1, 3", expect = Expect.ACCEPTABLE, desc="OK"),
        @Outcome(id="2, 3, 1", expect = Expect.ACCEPTABLE, desc="OK"),
        @Outcome(id="1, 3, 2", expect = Expect.ACCEPTABLE, desc="OK"),
        @Outcome(id="3, 2, 1", expect = Expect.ACCEPTABLE, desc="OK"),
        @Outcome(id="3, 1, 2", expect = Expect.ACCEPTABLE, desc="OK"),
})
@State
public class MonitorT3 {

    private final Monitor v = new Monitor();

    @Actor
    public void test1(final LLL_Result r) {
        r.r1 = v.test();
    }

    @Actor
    public void test2(final LLL_Result r) {
        r.r2 = v.test();
    }

    @Actor
    public void test3(final LLL_Result r) {
        r.r3 = v.test();
    }

}

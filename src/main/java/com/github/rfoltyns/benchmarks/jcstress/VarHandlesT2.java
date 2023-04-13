package com.github.rfoltyns.benchmarks.jcstress;

import com.github.rfoltyns.benchmarks.VarHandles;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.LL_Result;

@JCStressTest
@Outcome.Outcomes({
        @Outcome(id="1, 2", expect = Expect.ACCEPTABLE, desc="OK"),
        @Outcome(id="2, 1", expect = Expect.ACCEPTABLE, desc="OK"),
})
@State
public class VarHandlesT2 {

    private final VarHandles v = new VarHandles();

    @Actor
    public void test1(final LL_Result r) {
        r.r1 = v.test();
    }

    @Actor
    public void test2(final LL_Result r) {
        r.r2 = v.test();
    }

}

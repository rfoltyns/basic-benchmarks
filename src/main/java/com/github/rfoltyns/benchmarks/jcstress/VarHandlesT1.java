package com.github.rfoltyns.benchmarks.jcstress;

import com.github.rfoltyns.benchmarks.SynchronizedMethod;
import com.github.rfoltyns.benchmarks.VarHandles;
import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.L_Result;

@JCStressTest
@Outcome.Outcomes({
        @Outcome(id="1", expect = Expect.ACCEPTABLE, desc="OK"),
})
@State
public class VarHandlesT1 {

    private final VarHandles v = new VarHandles();

    @Actor
    public void test1(final L_Result r) {
        r.r1 = v.test();
    }

}

package c.m.impl;

/**
 * Created by Pavlo on 17.11.2015.
 */
public interface FF {

    long result(String bits, long[] input, long[] targets);

    long[] sampleInputs();

    long[] sampleTargets();

    long[] outputs();
}


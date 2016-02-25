package c.m.impl;

/**
 * Created by Pavlo on 17.11.2015.
 */
public interface FitnessFunction {

    long result(String bits, long[] input, double[] targets);

    long[] sampleInputs();

    double[] sampleTargets();

    double[] outputs();
}


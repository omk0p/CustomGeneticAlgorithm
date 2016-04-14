package c.m.ff;

/**
 * Created by Pavlo on 17.11.2015.
 */
public interface FitnessFunction {

	long evaluate(String bits, long[] input, double[] target);

	long[] input();

	double[] target();

	double[] output();

	int getGenomeSize();
}

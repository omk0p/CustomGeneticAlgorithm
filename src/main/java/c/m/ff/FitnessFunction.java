package c.m.ff;

/**
 * Created by Pavlo on 17.11.2015.
 */
public interface FitnessFunction {

	double evaluate(String bits, double[][] target);

	double[][] input();

	double[][] target();

	double[][] output();

	int getGenomeSize();
}

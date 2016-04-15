package c.m.ff;

import c.m.utils.Utils;

public class LinearFF implements FitnessFunction {

	private static final int GENOME_SIZE = 11;
	long k;

	public long[][] input() {
		long[][] r = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } };
		return r;
	}

	public double[][] target() {
		double[][] r = { { 465, 930, 1395, 1860, 2325, 2790, 3255, 3720, 4185, 4650 } };
		return r;
	}

	@Override
	public long evaluate(String bits, long[][] inputs, double[][] targets) {
		char sign = bits.charAt(0);
		bits = bits.substring(1);
		this.k = Integer.parseInt(bits, 2);
		if (sign == '1') {
			this.k = -this.k;
		}
		double[][] output = outputsInner(inputs);
		return Utils.mse(output, targets);
	}

	public double[][] outputsInner(long[][] inputs) {
		double[][] output = new double[1][Utils.size(inputs)];
		int i = 0;
		for (long in : inputs[0]) {
			output[0][i] = in * k;
			i++;
		}
		return output;
	}

	@Override
	public double[][] output() {
		return outputsInner(input());
	}

	@Override
	public int getGenomeSize() {
		return GENOME_SIZE;
	}

}

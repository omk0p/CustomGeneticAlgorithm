package c.m.ff;

import c.m.utils.Utils;

public class LinearFFConcat implements FitnessFunction {

	private static final int GENOME_SIZE = 14;

	public long[] input() {
		long[] r = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		return r;
	}

	public double[] target() {
		double[] r = { 77, 154, 231, 308, 385, 462, 539, 616, 693, 770 };
		return r;
	}

	long k;
	long l;

	@Override
	public long evaluate(String bits, long[] inputs, double[] targets) {
		String bStart = bits.substring(0, bits.length() / 2);
		String bEnd = bits.substring(bits.length() / 2, bits.length());

		char sign1 = bStart.charAt(0);
		char sign2 = bEnd.charAt(0);

		bStart = bStart.substring(1);
		bEnd = bEnd.substring(1);

		this.k = Integer.parseInt(bStart, 2);
		this.l = Integer.parseInt(bEnd, 2);

		if (sign1 == '1') {
			this.k = -this.k;
		}
		if (sign2 == '1') {
			this.l = -this.l;
		}

		double[] output = outputsInner(inputs);
		return Utils.mse(output, targets);
	}

	public double[] outputsInner(long[] inputs) {
		double[] output = new double[inputs.length];
		int i = 0;
		for (long in : inputs) {
			output[i] = in * k + in * l;
			i++;
		}
		return output;
	}

	@Override
	public double[] output() {
		return outputsInner(input());
	}

	@Override
	public int getGenomeSize() {
		return GENOME_SIZE;
	}

}

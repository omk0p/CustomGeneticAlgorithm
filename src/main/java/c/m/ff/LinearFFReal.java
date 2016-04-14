package c.m.ff;

import c.m.utils.Utils;
import c.m.utils.Matrix;
import c.m.utils.StringSplitter;

public class LinearFFReal implements FitnessFunction {

	int genomeSize = 341;
	int genomeParts = 31;
	double[] gNums;// genomeNumbers: weights,biases,etc.

	public long[] input() {
		long[] r = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		return r;
	}

	public double[] target() {
		double[] r = { 432, 930, 1705, 1216, 2326, 2790, 3255, 4184, 4185, 4419 };
		return r;
	}

	@Override
	public long evaluate(String bits, long[] input, double[] targets) {
		gNums = StringSplitter.signIntSplit(bits, genomeParts);
		for (int i = 0; i < gNums.length; i++) {
			gNums[i] = gNums[i] / 1000;
		}

		double[] output = outputsInner(input);
		return Utils.mse(output, targets);
	}

	@Override
	public double[] output() {
		return outputsInner(input());
	}

	public double[] outputsInner(long[] inputs) {

		double[][] x = { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } };
		double[][] t = { { 465, 930, 1395, 1860, 2325, 2790, 3255, 3720, 4185, 4650 } };
		double[][] b1 = { { gNums[0] }, { gNums[1] }, { gNums[2] }, { gNums[3] }, { gNums[4] }, { gNums[5] },
				{ gNums[6] }, { gNums[7] }, { gNums[8] }, { gNums[9] } };
		double[][] IW = { { gNums[10] }, { gNums[11] }, { gNums[12] }, { gNums[13] }, { gNums[14] }, { gNums[15] },
				{ gNums[16] }, { gNums[17] }, { gNums[18] }, { gNums[19] } };
		double[][] LW = { { gNums[20], gNums[21], gNums[22], gNums[23], gNums[24], gNums[25], gNums[26], gNums[27],
				gNums[28], gNums[29] } };

		double[][] b2 = { { gNums[30] } };

		double[][] xn = Utils.mapMinMax(x);
		int size = Utils.size(xn);
		double[][] ones = Utils.ones(size);
		double[][] B1 = Matrix.multiply(b1, ones);
		double[][] IWxn = Matrix.multiply(IW, xn);
		double[][] h = Utils.tansig(Matrix.add(B1, IWxn));
		double[][] LWh = Matrix.multiply(LW, h);
		double[][] yn = Matrix.addMy(b2, LWh);
		// TODO min must be by row
		double[][] y = Utils.reverseMapMinMax(yn, Utils.min(t[0]), Utils.max(t[0]));

		return y[0];
	}

	@Override
	public int getGenomeSize() {
		return genomeSize;
	}

}

package c.m.ff;

import c.m.utils.Matrix;
import c.m.utils.StringSplitter;
import c.m.utils.Utils;

public class NonLinearFF implements FitnessFunction {
	private static final int PART_BITS = 6;
	private static final int GENOME_PARTS = 62;
	private static final int GENOME_SIZE = GENOME_PARTS * (PART_BITS + 1);// sign
																			// bit
	double[] gNums;// genomeNumbers: weights,biases,etc.
	double[][] input = {
			{ -1000, -900, -800, -700, -600, -500, -400, -300, -200, -100, 0, 100, 200, 300, 400, 500, 600, 700, 800,
					900, 1000 },
			{ -2000, -1900, -1800, -1700, -1600, -1500, -1400, -1300, -1200, -1100, -1000, -900, -800, -700, -600, -500,
					-400, -300, -200, -100, 0 },
			{ 0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800,
					1900, 2000 } };

	double[][] target = {
			{ -1000, -1000, -1000, -1000, -1000, -1000, -900, -800, -700, -600, -500, -400, -300, -200, -100, 0, 100,
					200, 300, 400, 500 },
			{ -500, -400, -300, -200, -100, 00, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1000, 1000, 1000,
					1000, 1000 } };

	@Override
	public double[][] input() {
		return input;
	}

	@Override
	public double[][] target() {
		return target;
	}

	@Override
	public long evaluate(String bits, double[][] target) {
		gNums = StringSplitter.signIntSplit(bits, GENOME_PARTS);
		for (int i = 0; i < gNums.length; i++) {
			gNums[i] = gNums[i] / 100;
		}

		double[][] output = output();
		return Utils.mse(output, target);
	}

	@Override
	public double[][] output() {
		double[][] x = input();
		double[][] t = target();

		double[][] b1 = { { gNums[0] }, { gNums[1] }, { gNums[2] }, { gNums[3] }, { gNums[4] }, { gNums[5] },
				{ gNums[6] }, { gNums[7] }, { gNums[8] }, { gNums[9] } };
		double[][] IW = { { gNums[10], gNums[11], gNums[12] }, { gNums[13], gNums[14], gNums[15] },
				{ gNums[16], gNums[17], gNums[18] }, { gNums[19], gNums[20], gNums[21] },
				{ gNums[22], gNums[23], gNums[24] }, { gNums[25], gNums[26], gNums[27] },
				{ gNums[28], gNums[29], gNums[30] }, { gNums[31], gNums[32], gNums[33] },
				{ gNums[34], gNums[35], gNums[36] }, { gNums[37], gNums[38], gNums[39] } };
		double[][] LW = {
				{ gNums[40], gNums[41], gNums[42], gNums[43], gNums[44], gNums[45], gNums[46], gNums[47], gNums[48],
						gNums[49] },
				{ gNums[50], gNums[51], gNums[52], gNums[53], gNums[54], gNums[55], gNums[56], gNums[57], gNums[58],
						gNums[59] } };

		double[][] b2 = { { gNums[60] }, { gNums[61] } };

		double[][] xn = Utils.mapMinMax(x);
		double[][] tn = Utils.mapMinMax(t);
		int size = Utils.size(xn);
		double[][] ones = Utils.ones(size);
		double[][] B1 = Matrix.multiply(b1, ones);
		double[][] IWxn = Matrix.multiply(IW, xn);
		double[][] h = Utils.tansig(Matrix.add(B1, IWxn));
		double[][] LWh = Matrix.multiply(LW, h);
		double[][] yn = Matrix.add(Matrix.multiply(b2, ones), LWh);
		// TODO min must be by row
		double[][] y = reverseMapMinMax(yn, t);

		// Utils.print("y", y);
		// Utils.print("t", t);

		return y;
	}

	@Override
	public int getGenomeSize() {
		return GENOME_SIZE;
	}

	public static double[][] reverseMapMinMax(double[][] x, double[][] t) {
		if (x.length <= 0) {
			throw new IllegalArgumentException();
		}
		double[][] r = new double[x.length][x[0].length];

		for (int i = 0; i < x.length; i++) {
			double[] a = x[i];
			double tmin = Utils.min(t[i]);
			double tmax = Utils.max(t[i]);
			for (int j = 0; j < a.length; j++) {
				double b = a[j];
				r[i][j] = Utils.reverseMapMinMax(b, tmin, tmax);
			}
		}
		return r;
	}

}

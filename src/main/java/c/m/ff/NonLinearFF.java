package c.m.ff;

import c.m.utils.Matrix;
import c.m.utils.StringSplitter;
import c.m.utils.Utils;

public class NonLinearFF implements FitnessFunction {
	private static final int GENOME_SIZE = 682;
	int genomeParts = 62;
	double[] gNums;// genomeNumbers: weights,biases,etc.

	@Override
	public long[][] input() {
		long[][] result = { { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0 },
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
		return result;
	}

	@Override
	public long evaluate(String bits, long[][] input, double[][] target) {
		gNums = StringSplitter.signIntSplit(bits, genomeParts);
		for (int i = 0; i < gNums.length; i++) {
			gNums[i] = gNums[i] / 1000;
		}

		double[][] output = output();
		return Utils.mse(output, target);
	}

	@Override
	public double[][] target() {
		double[][] t = { { -10, -10, -10, -10, -10, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 },
				{ -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 10 } };
		return t;
	}

	@Override
	public double[][] output() {
		double[][] x = { { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0 },
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
		double[][] t = { { -10, -10, -10, -10, -10, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 },
				{ -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 10 } };

		double[][] b1 = { { gNums[0] }, { gNums[1] }, { gNums[2] }, { gNums[3] }, { gNums[4] }, { gNums[5] }, { gNums[6] },
				{ gNums[7] }, { gNums[8] }, { gNums[9] } };
		double[][] IW = { { gNums[10], gNums[11], gNums[12] }, { gNums[13], gNums[14], gNums[15] }, { gNums[16], gNums[17], gNums[18] },
				{ gNums[19], gNums[20], gNums[21] }, { gNums[22], gNums[23], gNums[24] }, { gNums[25], gNums[26], gNums[27] },
				{ gNums[28], gNums[29], gNums[30] }, { gNums[31], gNums[32], gNums[33] }, { gNums[34], gNums[35], gNums[36] },
				{ gNums[37], gNums[38], gNums[39] } };
		double[][] LW = { { gNums[40], gNums[41], gNums[42], gNums[43], gNums[44], gNums[45], gNums[46], gNums[47], gNums[48], gNums[49] },
				{ gNums[50], gNums[51], gNums[52], gNums[53], gNums[54], gNums[55], gNums[56], gNums[57], gNums[58], gNums[59] } };

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

		//Utils.print("y", y);
		//Utils.print("t", t);

		return y;
	}
/*public static void main(String[] args) {
	new NonLinearFF().output();
}*/
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

package c.m.ff;

import c.m.utils.Matrix;
import c.m.utils.Utils;

public class NonLinearFFReal implements FitnessFunction {
	public static void main(String[] args) {

		double[][] x = { { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0 },
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
		double[][] t = { { -10, -10, -10, -10, -10, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 },
				{ -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 10 } };
		double[][] b1 = { { 2.9595 }, { 2.7126 }, { -0.2243 }, { -0.5059 }, { -0.1973 }, { 0.2747 }, { 0.7702 },
				{ 1.0527 }, { -3.0403 }, { 1.8191 } };
		double[][] IW = { { -2.2815, -0.9726, 1.1814 }, { -0.5330, 2.8628, -0.7842 }, { -1.4739, -1.2010, 1.5747 },
				{ 1.8339, -0.1195, -2.0712 }, { 0.6413, -0.7896, 3.3739 }, { 3.0960, 1.8197, 2.8362 },
				{ 1.4977, -2.0843, 1.5729 }, { 0.5516, -2.2975, 1.8629 }, { -2.0791, 1.9430, -0.5591 },
				{ 2.7659, -0.9416, 0.6986 } };
		double[][] LW = { { -0.8148, -0.5811, -1.3493, -0.5924, -0.2262, -0.0297, 1.3445, 0.8506, 0.7188, -1.0531 },
				{ 0.2793, 0.4023, -0.2089, 0.2368, 0.5598, -0.1247, 0.4345, -0.9693, 0.2991, 0.3319 } };

		double[][] b2 = { { 0.6351 }, { 0.3001 } };

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

		Utils.print("y", y);
		Utils.print("t", t);
	}


	@Override
	public long[][] input() {
		long[][] result = { { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0 },
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
		return result;
	}

	@Override
	public long evaluate(String bits, long[][] input, double[][] target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[][] target() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[][] output() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGenomeSize() {
		// TODO Auto-generated method stub
		return 0;
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

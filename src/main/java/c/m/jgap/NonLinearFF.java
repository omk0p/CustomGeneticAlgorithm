package c.m.jgap;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.DoubleGene;

import c.m.utils.Matrix;
import c.m.utils.Utils;

public class NonLinearFF extends FitnessFunction {

	private static final long serialVersionUID = 1L;
	public static final double LOWER_LIMIT = -2.0;
	public static final double UPPER_LIMIT = 2.0;
	public static final int INPUT_NUM = 3;
	public static final int HIDDEN_NUM = 9;
	public static final int OUTPUT_NUM = 2;
	public static final int CHROME_SIZE = HIDDEN_NUM * 1 + HIDDEN_NUM * INPUT_NUM + OUTPUT_NUM * HIDDEN_NUM
			+ OUTPUT_NUM * 1;
	public static final double MSE_UPPER_LIMIT = 200.0;

	double[] gNums = new double[CHROME_SIZE];

	double[][] input = { { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0 },
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };

	double[][] target = { { -10, -10, -10, -10, -10, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 },
			{ -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 10 } };

	public NonLinearFF() {

	}

	public NonLinearFF(double[][] input, double[][] target) {
		this.input = input;
		this.target = target;
	}

	@Override
	public double evaluate(IChromosome a_subject) {

		for (int i = 0; i < a_subject.size(); i++) {
			DoubleGene value = (DoubleGene) a_subject.getGene(a_subject.size() - (i + 1));
			gNums[i] = value.doubleValue();
		}

		double[][] output = output(input);
		double mse = Utils.mse(output, target);
		// Utils.print("OUT=", output);
		// System.out.println("MSE=" + mse);
		return MSE_UPPER_LIMIT - mse;
	}

	public double[][] output(double[][] input) {
		double[][] x = input;
		double[][] t = target;

		double[][] b1 = new double[HIDDEN_NUM][1];
		int gCount = 0;
		for (int i = 0; i < b1.length; i++) {
			for (int j = 0; j < Utils.size(b1); j++) {
				b1[i][j] = gNums[gCount];
				gCount++;
			}
		}
		double[][] IW = new double[HIDDEN_NUM][INPUT_NUM];
		for (int i = 0; i < IW.length; i++) {
			for (int j = 0; j < Utils.size(IW); j++) {
				IW[i][j] = gNums[gCount];
				gCount++;
			}
		}
		double[][] LW = new double[OUTPUT_NUM][HIDDEN_NUM];
		for (int i = 0; i < LW.length; i++) {
			for (int j = 0; j < Utils.size(LW); j++) {
				LW[i][j] = gNums[gCount];
				gCount++;
			}
		}

		double[][] b2 = new double[OUTPUT_NUM][1];
		for (int i = 0; i < b2.length; i++) {
			for (int j = 0; j < Utils.size(b2); j++) {
				b2[i][j] = gNums[gCount];
				gCount++;
			}
		}

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

package c.m.jgap;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.DoubleGene;

import c.m.utils.Matrix;
import c.m.utils.Utils;

public class NonLinearFFRewritten extends FitnessFunction {
	private static final long serialVersionUID = 1L;
	public static final double LOWER_LIMIT = -2.0;
	public static final double UPPER_LIMIT = 2.0;
	public static final int CHROME_SIZE = 62;
	public static final double MSE_UPPER_LIMIT = 100.0;
	double[] gNums = new double[62];

	double[][] input;

	double[][] target;

	public NonLinearFFRewritten(double[][] input, double[][] target) {
		this.input = input;
		this.target = target;
	}

	@Override
	public double evaluate(IChromosome a_subject) {

		for (int i = 0; i < a_subject.size(); i++) {
			DoubleGene value = (DoubleGene) a_subject.getGene(a_subject.size() - (i + 1));
			gNums[i] = value.doubleValue();
		}

		double[][] output = output();
		double mse = Utils.mse(output, target);
		// Utils.print("OUT=", output);
		// System.out.println("MSE=" + mse);
		return MSE_UPPER_LIMIT - mse;
	}

	public double[][] output() {
		double[][] x = input;
		double[][] t = target;

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

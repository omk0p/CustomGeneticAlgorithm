package c.m.system;

import c.m.utils.Utils;

public class CO implements BlockCO {
	int outputs = 2;
	int dataSeqSize = 21;
	double k = 1;

	public void influenceF() {
		k = 1.1;
	}

	@Override
	public double[][] output(double[][] in) {
		double[][] out = new double[outputs][dataSeqSize];
		for (int i = 0; i < Utils.size(in); i++) {
			// -20, -10
			out[0][i] = Utils.saturation(-5, 5, in[1][i], k) + Utils.saturation(-5, 5, in[0][i], k);
			// -10, 0
			out[1][i] = Utils.saturation(-5, 5, in[0][i], k) + Utils.saturation(-5, 5, in[2][i], k);
		}
		return out;
	}
}

package c.m.system;

import c.m.utils.Utils;

public class CO implements Block {
	int outputs;
	int dataSeqSize;

	public CO(int outputs, int dataSeqSize) {
		super();
		this.outputs = outputs;
		this.dataSeqSize = dataSeqSize;
	}

	public void influenceF() {

	}

	@Override
	public double[][] output(double[][] in) {
		double[][] out = new double[outputs][dataSeqSize];
		for (int i = 0; i < Utils.size(in); i++) {
			// -20, -10
			out[0][i] = Utils.saturation(-5, 5, in[1][i]) + Utils.saturation(-5, 5, in[0][i]);
			// -10, 0
			out[1][i] = Utils.saturation(-5, 5, in[0][i]) + Utils.saturation(-5, 5, in[2][i]);
		}
		return out;
	}
}

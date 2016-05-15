package c.m.system;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.primitives.Doubles;

import c.m.utils.Utils;

public class DigStateSpaceCO implements BlockCO {
	private double[][] A = { { 0.9077, 0 }, { 0, 0.5363 } };
	private double[][] B = { { 1 }, { 1 } };
	private double[][] C = { { 1.4491, -1.1315 } };
	private double[] D = { 1 };
	private int U_length = 600;
	private static double reduction = 0.1;
	private int[] rss = { 3, 1, 4, 2, 1 };
	private int inputs_size = A.length;
	private int outputs_size = C.length;
	private double[][] U = generateURSS(rss);
	private int scope_h = Utils.size(U);

	private double[][] x = new double[inputs_size][scope_h];
	private double[][] y = new double[outputs_size][scope_h];
	int output_length = (int) (scope_h * reduction);
	double[][] dU = new double[outputs_size][output_length];
	private final int distLength = output_length;
	private static final double distMax = 4;
	private static final int distApplyTime = 0;

	@Before
	public void before() {

	}

	public double[][] getX() {
		return reduction(x, reduction);
	}

	private double[][] reduction(double[][] x, double reduction) {
		int divider = (int) (reduction * 100);
		double[][] result = new double[x.length][(Utils.size(x) - 1) / divider + 1];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < Utils.size(x); j++) {
				if (j % divider == 0) {
					result[i][j / divider] = x[i][j];
				}
			}
		}
		return result;
	}

	@Test
	public void reductionTest() {
		double[][] x = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } };
		Utils.print("Reduction test", reduction(x, 0.1));
	}

	public double[][] getY() {
		return reduction(y, reduction);
	}

	private double[][] generateURSS(int[] rssSample) {
		double[][] result = new double[inputs_size][U_length];
		int step = U_length / rssSample.length;
		for (int i = 0; i < U_length; i++) {
			int out = i / step;
			for (int j = 0; j < inputs_size; j++) {
				result[j][i] = rssSample[out];
			}
		}
		return result;
	}

	@Test
	public void generateURSSTest() {
		Utils.print("URSS", generateURSS(rss));
	}

	public DigStateSpaceCO() {
		calcOutput();
	}

	@Test
	@Ignore
	public void calcOutput() {
		for (int i = 0; i < scope_h; i++) {
			for (int j = 0; j < inputs_size; j++) {
				double inputs_sum = 0;
				for (int k = 0; k < inputs_size; k++) {
					inputs_sum += x[k][i] * A[j][k];
				}
				if (i != scope_h - 1)
					x[j][i + 1] = inputs_sum + B[j][0] * U[j][i];
			}
			for (int j = 0; j < outputs_size; j++) {
				double inputs_sum = 0;
				for (int k = 0; k < inputs_size; k++) {
					inputs_sum += x[k][i] * C[j][k];
				}
				y[j][i] = inputs_sum + D[0] * U[j][i];
			}

		}
		Utils.print("DSS CO x:", x);
		Utils.print("DSS CO y:", y);
	}

	@Override
	public double[][] output(double[][] in) {
		double[][] x = in;
		int max = (int) (scope_h * reduction);
		int multiplier = (int) (reduction * 100);
		double[][] y = new double[outputs_size][max];
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < outputs_size; j++) {
				double inputs_sum = 0;
				for (int k = 0; k < inputs_size; k++) {
					inputs_sum += x[k][i] * C[j][k];
				}
				y[j][i] = inputs_sum + D[0] * U[j][i * multiplier] + dU[j][i];
			}

		}
		return y;
	}

	@Override
	public void influenceF() {
		dU = random(distMax, distLength);
	}

	private double[][] random(double distmax2, int distlength2) {
		double[][] result = new double[outputs_size][output_length];
		double[] random = new double[distlength2];
		for (int j = 0; j < random.length; j++) {
			random[j] = ThreadLocalRandom.current().nextDouble(-1, 1) * distmax2;
		}
		//Arrays.sort(random);
		//Collections.reverse(Doubles.asList(random));

		for (int i = 0; i < outputs_size; i++) {
			int k = 0;
			for (int j = distApplyTime; j < distApplyTime + distlength2; j++, k++) {
				result[i][j] = random[k];
			}

		}

		return result;
	}

	@Test
	public void randomTest() {
		Utils.print("Random seq", random(distMax, distLength));
	}

}

package c.m;

import org.junit.Assert;
import org.junit.Test;

import c.m.utils.Utils;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class UtilsTest extends Utils {

	@Test
	public void mseTest2() {
		double[][] i1 = { { 0, 0, 14, 14 } };
		double[][] i2 = { { 0, 6, 8, 14 } };
		double[][] i3 = { { 6, 6, 8, 8 } };
		double[][] targets = { { 7, 7, 7, 7 } };
		int[] anwers = { 7, 5, 1 };

		Assert.assertEquals(anwers[0], mse(i1, targets), 0.01);
		Assert.assertEquals(anwers[1], mse(i2, targets), 0.01);
		Assert.assertEquals(anwers[2], mse(i3, targets), 0.01);
	}

	/*
	 * @Test public void mseTest(){ int[] i1 = {0, 0, 14, 14}; int[] i2 = {0, 6,
	 * 8, 14}; int[] i3 = {6, 6, 8, 8}; int[] a1 = {7,4,1};
	 * 
	 * Assert.assertEquals(a1[0], mse(i1)); Assert.assertEquals(a1[1], mse(i2));
	 * Assert.assertEquals(a1[2], mse(i3)); }
	 */

	@Test
	public void arithMeanTest() {
		double[] i1 = { 0, 0, 14, 14 };
		double[] i2 = { 0, 6, 8, 14 };
		double[] i3 = { 6, 6, 8, 8 };

		int[] a1 = { 7, 7, 7 };

		Assert.assertEquals(a1[0], arithMean(i1), 0.1);
		Assert.assertEquals(a1[1], arithMean(i2), 0.1);
		Assert.assertEquals(a1[2], arithMean(i3), 0.1);
	}

	@Test
	public void binaryStringToDoubleTest() {
		Assert.assertTrue(new Double(0).equals(binaryStringToDouble("0")));
		Assert.assertTrue(new Double(1)
				.equals(binaryStringToDouble("11111111110000000000000000000000000000000000000000000000000000")));
		Assert.assertTrue(new Double(2)
				.equals(binaryStringToDouble("100000000000000000000000000000000000000000000000000000000000000")));
	}

	@Test
	public void doubleToBinaryStringTest() {
		Assert.assertTrue("0".equals(doubleToBinaryString(0)));
		Assert.assertTrue(
				"11111111110000000000000000000000000000000000000000000000000000".equals(doubleToBinaryString(1)));
		Assert.assertTrue(
				"100000000000000000000000000000000000000000000000000000000000000".equals(doubleToBinaryString(2)));
	}

	@Test
	public void rampSignalTest() {
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i, ramp(0, 1, i));
		}
	}

	@Test
	public void rampSignalTest2() {
		for (int i = 0; i < 20; i++) {
			Assert.assertEquals(i * 2, ramp(0, 2, i));
		}
	}

	@Test
	public void rampSignalTest3() {
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i * 1 - 10, ramp(-10, 1, i));
		}
	}

}

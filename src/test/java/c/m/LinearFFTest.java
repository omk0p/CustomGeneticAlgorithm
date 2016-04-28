package c.m;

import org.junit.Assert;
import org.junit.Test;

import c.m.ff.FitnessFunction;
import c.m.ff.LinearFF;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class LinearFFTest extends LinearFF implements FitnessFunction {

	@Test
	public void mseTest() {
		setInput(new double[][] { { 1, 2, 3, 4, 5 } });
		Assert.assertEquals(0, evaluate("0101", new double[][] { { 5, 10, 15, 20, 25 } }),
				BaseTest.NN_DOUBLE_PRECISION);
	}

	@Test
	public void mseTest2() {
		setInput(new double[][] { { 1, 1, 1, 1, 1 } });
		Assert.assertEquals(0, evaluate("0101", new double[][] { { 5, 5, 5, 5, 5 } }), BaseTest.NN_DOUBLE_PRECISION);
	}

	@Test
	public void mseTest3() {
		setInput(new double[][] { { 1, 1, 1, 1, 1 } });
		Assert.assertEquals(2, evaluate("010", new double[][] { { 4, 4, 4, 4, 4 } }), BaseTest.NN_DOUBLE_PRECISION);
	}

}

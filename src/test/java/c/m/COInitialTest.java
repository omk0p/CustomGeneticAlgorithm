package c.m;

import org.junit.Assert;
import org.junit.Test;

import c.m.system.COInitial;

/**
 * Created by Pavlo on 19.09.2015.
 */
public class COInitialTest {
	// tests
	@Test
	public void timeMustBeGeneratedCorrectlyTest() {
		int[] expected = { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Assert.assertArrayEquals(expected, COInitial.time(-10, 10));
	}

	@Test
	public void timeMustBeGeneratedCorrectlyTest2() {
		int[] expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Assert.assertArrayEquals(expected, COInitial.time(0, 10));
	}
}

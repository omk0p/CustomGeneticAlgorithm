package c.m.utils;

import org.junit.Assert;
import org.junit.Test;

import c.m.utils.DoubleUtils;

public class OtherTests {
	@Test
	public void binaryStringToDoubleTest() {
		Assert.assertTrue(new Double(0).equals(DoubleUtils.binaryStringToDouble("0")));
		Assert.assertTrue(new Double(1).equals(DoubleUtils.binaryStringToDouble("11111111110000000000000000000000000000000000000000000000000000")));
		Assert.assertTrue(new Double(2).equals(DoubleUtils.binaryStringToDouble("100000000000000000000000000000000000000000000000000000000000000")));
	}
	
	@Test
	public void doubleToBinaryStringTest() {
		Assert.assertTrue("0".equals(DoubleUtils.doubleToBinaryString(0)));
		Assert.assertTrue("11111111110000000000000000000000000000000000000000000000000000".equals(DoubleUtils.doubleToBinaryString(1)));
		Assert.assertTrue("100000000000000000000000000000000000000000000000000000000000000".equals(DoubleUtils.doubleToBinaryString(2)));
	}
	

	
}

package c.m.impl;

import org.junit.Ignore;
import org.junit.Test;

public class OtherTests {
	@Test
	public void binaryStringToFloatTest() {
		int intBits = Integer.parseInt("00111110001000000000000000000000", 2);
		float myFloat = Float.intBitsToFloat(intBits);
		System.out.println(myFloat);
	}
	
	@Test
	public void floatToBinaryStringTest() {
		int intBits = Float.floatToIntBits(-0.15625f); 
		String binary = Integer.toBinaryString(intBits);
		System.out.println(binary);
	}
	

	
}

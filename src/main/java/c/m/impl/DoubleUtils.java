package c.m.impl;

import java.math.BigInteger;

public class DoubleUtils {
	
	public static double binaryStringToDouble(String str) {
		return Double.longBitsToDouble(new BigInteger(str, 2).longValue());
	}
	
	
	public static String doubleToBinaryString(double d) {
		return Long.toBinaryString(Double.doubleToRawLongBits(d));
	}
}

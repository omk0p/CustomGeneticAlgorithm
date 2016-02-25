package c.m.impl;

public class FloatUtils {
	
	public float binaryStringToFloat(String str) {
		int intBits = Integer.parseInt(str, 2);
		return Float.intBitsToFloat(intBits);
	}
	
	
	public String floatToBinaryString(float f) {
		int intBits = Float.floatToIntBits(f); 
		return Integer.toBinaryString(intBits);
	}
}

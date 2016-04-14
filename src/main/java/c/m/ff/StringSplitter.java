package c.m.ff;

public class StringSplitter {
	public static double[] signIntSplit(String bits, int parts) {

		if (bits.length() % parts != 0) {
			throw new RuntimeException("Number of bits should be divided on parts without remain");
		}

		// split string
		int length = bits.length() / parts;
		String[] bitsSplitted = bits.split("(?<=\\G.{" + length + "})");
		char[] signs = new char[bitsSplitted.length];
		double[] result = new double[bitsSplitted.length];

		// extract signs
		for (int i = 0; i < bitsSplitted.length; i++) {
			signs[i] = bitsSplitted[i].charAt(0);
			bitsSplitted[i] = bitsSplitted[i].substring(1);
			result[i] = Integer.parseInt(bitsSplitted[i], 2);
			if (signs[i] == '1') {
				result[i] = -result[i];
			}
		}

		return result;
	}
}

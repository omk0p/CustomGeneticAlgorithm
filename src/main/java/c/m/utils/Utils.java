package c.m.utils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.DoubleStream;

public class Utils {

	// single number or array functions
	public static double min(double[] x) {
		if (x.length > 0) {
			double min = x[0];
			for (int i = 0; i < x.length; i++) {
				if (x[i] < min) {
					min = x[i];
				}
			}
			return min;
		}
		throw new IllegalArgumentException("Empty array disallowed");
	}

	public static double max(double[] x) {
		if (x.length > 0) {
			double max = x[0];
			for (int i = 0; i < x.length; i++) {
				if (x[i] > max) {
					max = x[i];
				}
			}
			return max;
		}
		throw new IllegalArgumentException("Empty array disallowed");
	}

	public static double tansig(double t) {
		return (Math.pow(Math.E, t) - Math.pow(Math.E, -t)) / (Math.pow(Math.E, t) + Math.pow(Math.E, -t));
	}

	public static double reverseMapMinMax(double xn, double ymin, double ymax) {
		double xmin = -1;
		double xmax = 1;
		return (ymax - ymin) * (xn - xmin) / (xmax - xmin) + ymin;
	}

	public static double mapMinMax(double x, double xmin, double xmax) {
		double ymin = -1;
		double ymax = 1;
		return (ymax - ymin) * (x - xmin) / (xmax - xmin) + ymin;
	}

	// matrix functions
	public static double[][] ones(int n) {
		return Matrix.ones(1, n);
	}

	public static int size(double[][] x) {
		if (x.length <= 0) {
			throw new IllegalArgumentException();
		}
		return x[0].length;
	}

	public static int size(long[][] x) {
		if (x.length <= 0) {
			throw new IllegalArgumentException();
		}
		return x[0].length;
	}

	public static double[][] tansig(double[][] x) {
		if (x.length > 0) {
			double[][] r = new double[x.length][x[0].length];

			for (int i = 0; i < x.length; i++) {
				double[] a = x[i];
				for (int j = 0; j < a.length; j++) {
					double b = a[j];
					r[i][j] = Utils.tansig(b);
				}
			}
			return r;
		}
		throw new IllegalArgumentException();
	}

	public static double[][] mapMinMax(double[][] x) {
		if (x.length <= 0) {
			throw new IllegalArgumentException();
		}
		double[][] r = new double[x.length][x[0].length];

		for (int i = 0; i < x.length; i++) {
			double[] a = x[i];
			double xmin = Utils.min(a);
			double xmax = Utils.max(a);
			for (int j = 0; j < a.length; j++) {
				double b = a[j];
				r[i][j] = Utils.mapMinMax(b, xmin, xmax);
			}
		}
		return r;
	}

	public static double[][] reverseMapMinMax(double[][] x, double tmin, double tmax) {
		if (x.length <= 0) {
			throw new IllegalArgumentException();
		}
		double[][] r = new double[x.length][x[0].length];

		for (int i = 0; i < x.length; i++) {
			double[] a = x[i];
			for (int j = 0; j < a.length; j++) {
				double b = a[j];
				r[i][j] = Utils.reverseMapMinMax(b, tmin, tmax);
			}
		}
		return r;
	}

	public static void print(String s) {
		System.out.println(s);
	}

	public static void print(long[] arr) {
		System.out.print(Arrays.toString(arr) + " ");
	}

	public static String arr2DOneLineString(double[][] arr) {
		StringBuilder result = new StringBuilder();
		result.append('[');
		for (double[] a : arr) {
			result.append('[');
			for (double d : a) {
				result.append(String.format(Locale.US, "%.2f", d) + ",");
			}
			result.append(']');
		}
		result.append(']');
		return result.toString();
	}

	public static void print(String n, double a) {
		System.out.print(n + ": ");
		System.out.println(a);
	}

	public static void print(String n, double[][] a) {
		System.out.println(n + ":");
		for (double[] arr : a) {
			System.out.println(Arrays.toString(arr));
		}
	}

	public static String split(int[] arr) {
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			result += arr[i];
		}
		return result;
	}

	public static double mse(double[][] output, double[][] targets) {
		double[] mses = new double[output.length];
		for (int i = 0; i < output.length; i++) {
			double[] diffs = new double[size(output)];
			int j = 0;
			for (double in : output[i]) {
				double diff = in - targets[i][j];
				diff = diff * diff;// ^2
				diffs[j] = diff;
				j++;
			}
			mses[i] = Math.sqrt(arithMean(diffs));
		}
		return arithMean(mses);
	}

	public static double arithMean(double... doubles) {
		return DoubleStream.of(doubles).sum() / doubles.length;
	}

	public static double[][] copyFromLongArray(long[][] source) {
		double[][] dest = new double[source.length][size(source)];
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < size(source); j++) {
				dest[i][j] = source[i][j];
			}
		}
		return dest;
	}

	public static int multiply(int a, int b) {
		return a * b;
	}

	public static int add(int a, int b) {
		return a + b;
	}

	public static int constant(int a) {
		return a;
	}

	public static double saturation(int lo, int up, double u, double k) {
		u = k * u;
		if (u < lo)
			return lo;
		if (u > up)
			return up;
		return u;
	}

	public static int ramp(int initialOutput, int slope, int t) {
		return slope * t + initialOutput;
	}

	public static double binaryStringToDouble(String str) {
		return Double.longBitsToDouble(new BigInteger(str, 2).longValue());
	}

	public static String doubleToBinaryString(double d) {
		return Long.toBinaryString(Double.doubleToRawLongBits(d));
	}
}

package c.m.utils;

import java.util.Arrays;
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

	public static long mse(double[] longs, double[] targets) {
		double[] diffs = new double[longs.length];
		int i = 0;
		for (double in : longs) {
			double diff = in - targets[i];
			diff = diff * diff;// ^2
			diffs[i] = diff;
			i++;
		}
		return (long) Math.sqrt(arithMean(diffs));
	}

	public static double arithMean(double... doubles) {
		return DoubleStream.of(doubles).sum() / doubles.length;
	}

}

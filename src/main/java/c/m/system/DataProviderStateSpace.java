package c.m.system;

public class DataProviderStateSpace extends DataProvider {
	// double[][] input = { { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2,
	// 3, 4, 5, 6, 7, 8, 9, 10 },
	// { -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6,
	// -5, -4, -3, -2, -1, 0 },
	// { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
	// 20 } };
	//
	// double[][] target = { { -10, -10, -10, -10, -10, -10, -9, -8, -7, -6, -5,
	// -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 },
	// { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10,
	// 10 } };
	DigStateSpaceCO ss = new DigStateSpaceCO();

	@Override
	public double[][] getInput() {
		return ss.getX();
	}

	@Override
	public double[][] getTarget() {
		return ss.getY();
	}

}

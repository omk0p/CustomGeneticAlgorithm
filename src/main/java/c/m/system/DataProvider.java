package c.m.system;

public abstract class DataProvider {
	public abstract double[][] getInput();

	public abstract double[][] getTarget();

	public static DataProvider getProvider() {
		return new DataProviderStateSpace();//new DataProviderMultidimensional();
	}

	public double[][] getReverseTarget() {
		return getInput();
	}

	public double[][] getReverseInput() {
		return getTarget();
	}
}

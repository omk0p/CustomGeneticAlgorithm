package c.m.system;

public interface NN {
	public void learnCompletely(double[][] input, double[][] target);

	public void learnOnce(double[][] input, double[][] target);
}

package c.m.impl;


public class LinearFF implements FitnessFunction{

    public long[] sampleInputs(){
      long[] r = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      return r;
    }
    
    public double[] sampleTargets(){
      double[] r = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
      return r;
    }
  
    long k;
    
    @Override
    public long result(String bits, long[] inputs, double[] targets) {
        this.k = Integer.parseInt(bits, 2);
        double[] output = outputsInner(inputs);
        return Utils.mse(output,targets);
    }

	public double[] outputsInner(long[] inputs) {
		double[] output = new double[inputs.length];
		int i = 0;
		for (long in : inputs) {
			output[i] = in * k;
			i++;
		}
		return output;
	}
    
    @Override
    public double[] outputs() {
      return outputsInner(sampleInputs());
    }






}

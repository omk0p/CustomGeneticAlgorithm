package c.m.impl;


public class LinearFF implements FitnessFunction{

    public long[] sampleInputs(){
      long[] r = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      return r;
    }
    
    public long[] sampleTargets(){
      long[] r = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
      return r;
    }
  
    long k;
    
    @Override
    public long result(String bits, long[] inputs, long[] targets) {
        this.k = Integer.parseInt(bits, 2);
        long[] output = outputsInner(inputs);
        return Utils.mse(output,targets);
    }

	public long[] outputsInner(long[] inputs) {
		long[] output = new long[inputs.length];
		int i = 0;
		for (long in : inputs) {
			output[i] = in * k;
			i++;
		}
		return output;
	}
    
    @Override
    public long[] outputs() {
      return outputsInner(sampleInputs());
    }






}

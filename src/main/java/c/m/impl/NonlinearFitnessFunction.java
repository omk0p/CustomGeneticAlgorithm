package c.m.impl;


/**
 * Created by Pavlo on 17.11.2015.
 */
public class NonlinearFitnessFunction implements FitnessFunction{

    public long[] sampleInputs(){
      long[] r = {1, 2, 3, 4, 5};
      return r;
    }
    
    public long[] sampleTargets(){
      long[] r = {5, 10, 15, 20, 25};
      return r;
    }
  
    long k;
    
    @Override
    public long result(String bits, long[] inputs, long[] targets) {
        this.k = Integer.parseInt(bits, 2);
        long[] output = new long[inputs.length];
        int i = 0;
        for (long in : inputs){
            output[i] = in*k;
            i++;
        }
        return Utils.mse(output,targets);
    }

    @Override
    public long[] outputs() {
      long[] output = new long[sampleInputs().length];
      int i = 0;
      for (long in : sampleInputs()){
        output[i] = in*k;
        i++;
      }
      return output;
    }






}

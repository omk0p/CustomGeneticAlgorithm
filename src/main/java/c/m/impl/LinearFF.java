package c.m.impl;


/**
 * Created by Pavlo on 17.11.2015.
 */
public class LinearFF implements FF{

    public int[] sampleInputs(){
      int[] r = {1000, 2000, 3000, 4000, 5000};
      return r;
    }
    
    public int[] sampleTargets(){
      int[] r = {5000, 10000, 15000, 20000, 25000};
      return r;
    }
  
    int k;
    
    @Override
    public int result(String bits, int[] inputs, int[] targets) {
        this.k = Integer.parseInt(bits, 2);
        int[] output = new int[inputs.length];
        int i = 0;
        for (int in : inputs){
            output[i] = in*k;
            i++;
        }
        return Funcs.mse(output,targets);
    }

    @Override
    public int[] outputs() {
      int[] output = new int[sampleInputs().length];
      int i = 0;
      for (int in : sampleInputs()){
        output[i] = in*k;
        i++;
      }
      return output;
    }






}

package c.m.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class LinearFF implements FF{

    //input
    //1 2 3 4 5
    //output
    //x5
    //5 10 15 20 25

    @Override
    public int result(String bits, int[] inputs, int[] targets) {
        int k = Integer.parseInt(bits, 2);
        int[] output = new int[inputs.length];
        int i = 0;
        for (int in : inputs){
            output[i] = in*k;
            i++;
        }
        return Funcs.mse(output,targets);
    }

    @Test
    public void mseTest(){
        Assert.assertEquals(0, result("101", new int[]{1, 2, 3, 4, 5}, new int[]{5, 10, 15, 20, 25}));
    }

    @Test
    public void mseTest2(){
        Assert.assertEquals(0, result("101", new int[]{1, 1, 1, 1, 1}, new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void mseTest3(){
        Assert.assertEquals(2, result("010", new int[]{1, 1, 1, 1, 1}, new int[]{4, 4, 4, 4, 4}));
    }






}

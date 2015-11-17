package c.m.impl;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class Funcs {
    public static int mse(int... ints) {
        int arMean = arithMean(ints);
        int[] diffs = new int[ints.length];
        int i = 0;
        for (int in : ints){
            int diff = Math.abs(in - arMean);
            diffs[i] = diff;
            i++;
        }
        return arithMean(diffs);
    }

    public static int mse(int[] ints, int[] targets) {
        int[] diffs = new int[ints.length];
        int i = 0;
        for (int in : ints){
            int diff = Math.abs(in - targets[i]);
            diffs[i] = diff;
            i++;
        }
        return arithMean(diffs);
    }

    @Test
    public void mseTest2(){
        int[] i1 = {0, 0, 14, 14};
        int[] i2 = {0, 6, 8, 14};
        int[] i3 = {6, 6, 8, 8};
        int[] t1 = {7,7,7,7};
        int[] a1 = {7,4,1};

        Assert.assertEquals(a1[0], mse(i1, t1));
        Assert.assertEquals(a1[1], mse(i2, t1));
        Assert.assertEquals(a1[2], mse(i3, t1));
    }

    @Test
    public void mseTest(){
        int[] i1 = {0, 0, 14, 14};
        int[] i2 = {0, 6, 8, 14};
        int[] i3 = {6, 6, 8, 8};
        int[] a1 = {7,4,1};

        Assert.assertEquals(a1[0], mse(i1));
        Assert.assertEquals(a1[1], mse(i2));
        Assert.assertEquals(a1[2], mse(i3));
    }

    @Test
    public void arithMeanTest(){
        int[] i1 = {0, 0, 14, 14};
        int[] i2 = {0, 6, 8, 14};
        int[] i3 = {6, 6, 8, 8};

        int[] a1 = {7,7,7};

        Assert.assertEquals(a1[0], arithMean(i1));
        Assert.assertEquals(a1[1], arithMean(i2));
        Assert.assertEquals(a1[2], arithMean(i3));



    }

    static int arithMean(int... ints){
        return IntStream.of(ints).sum()/ints.length;
    }
}

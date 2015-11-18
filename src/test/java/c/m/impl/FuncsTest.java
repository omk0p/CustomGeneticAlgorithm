package c.m.impl;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class FuncsTest extends Funcs{

    @Test
    public void mseTest2(){
        long[] i1 = {0, 0, 14, 14};
        long[] i2 = {0, 6, 8, 14};
        long[] i3 = {6, 6, 8, 8};
        long[] t1 = {7,7,7,7};
        int[] a1 = {7,5,1};

        Assert.assertEquals(a1[0], mse(i1, t1));
        Assert.assertEquals(a1[1], mse(i2, t1));
        Assert.assertEquals(a1[2], mse(i3, t1));
    }

    /*@Test
    public void mseTest(){
        int[] i1 = {0, 0, 14, 14};
        int[] i2 = {0, 6, 8, 14};
        int[] i3 = {6, 6, 8, 8};
        int[] a1 = {7,4,1};

        Assert.assertEquals(a1[0], mse(i1));
        Assert.assertEquals(a1[1], mse(i2));
        Assert.assertEquals(a1[2], mse(i3));
    }*/

    @Test
    public void arithMeanTest(){
        long[] i1 = {0, 0, 14, 14};
        long[] i2 = {0, 6, 8, 14};
        long[] i3 = {6, 6, 8, 8};

        int[] a1 = {7,7,7};

        Assert.assertEquals(a1[0], arithMean(i1));
        Assert.assertEquals(a1[1], arithMean(i2));
        Assert.assertEquals(a1[2], arithMean(i3));
    }

}

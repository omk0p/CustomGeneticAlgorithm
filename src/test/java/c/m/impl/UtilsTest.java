package c.m.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class UtilsTest extends Utils{

    @Test
    public void mseTest2(){
    	double[] i1 = {0, 0, 14, 14};
    	double[] i2 = {0, 6, 8, 14};
    	double[] i3 = {6, 6, 8, 8};
        double[] targets = {7,7,7,7};
        int[] anwers = {7,5,1};

        Assert.assertEquals(anwers[0], mse(i1, targets));
        Assert.assertEquals(anwers[1], mse(i2, targets));
        Assert.assertEquals(anwers[2], mse(i3, targets));
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
    	double[] i1 = {0, 0, 14, 14};
    	double[] i2 = {0, 6, 8, 14};
    	double[] i3 = {6, 6, 8, 8};

        int[] a1 = {7,7,7};

        Assert.assertEquals(a1[0], arithMean(i1), 0.1);
        Assert.assertEquals(a1[1], arithMean(i2), 0.1);
        Assert.assertEquals(a1[2], arithMean(i3), 0.1);
    }

}

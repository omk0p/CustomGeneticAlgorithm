package c.m.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class LinearFFTest extends LinearFF implements FitnessFunction{

    @Test
    public void mseTest(){
        Assert.assertEquals(0, result("101", new long[]{1, 2, 3, 4, 5}, new double[]{5, 10, 15, 20, 25}));
    }

    @Test
    public void mseTest2(){
        Assert.assertEquals(0, result("101", new long[]{1, 1, 1, 1, 1}, new double[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void mseTest3(){
        Assert.assertEquals(2, result("010", new long[]{1, 1, 1, 1, 1}, new double[]{4, 4, 4, 4, 4}));
    }

}

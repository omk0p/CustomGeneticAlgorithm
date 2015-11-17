package c.m.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class LinearFFTest extends LinearFF implements FF{

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

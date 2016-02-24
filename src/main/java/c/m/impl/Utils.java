package c.m.impl;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class Utils {
  
  public static long[] fillArray(int size, int maxRand){
    long[] genes = new long[size];
    Random m_rand = new Random();
    for (int i = 0; i < genes.length; i++) {
      genes[i] = m_rand.nextInt(maxRand);
    }
    return genes;
  }
  
  public static void print(String s){
    System.out.println(s);
  }
  
  public static void print(long[] arr){
    System.out.print(Arrays.toString(arr) + " ");
  }
  
  public static String split(int[] arr){
    String result = "";
    for (int i = 0; i < arr.length; i++) {
      result += arr[i];
    }
    return result;
  }

    //TODO needs rework - see function below (^2, sqrt - for mse)
    /*public static int mse(int... ints) {
        int arMean = arithMean(ints);
        int[] diffs = new int[ints.length];
        int i = 0;
        for (int in : ints){
            int diff = Math.abs(in - arMean);
            diffs[i] = diff;
            i++;
        }
        return arithMean(diffs);
    }*/

    public static long mse(long[] longs, long[] targets) {
        long[] diffs = new long[longs.length];
        int i = 0;
        for (long in : longs){
            long diff = in - targets[i];
            diff = diff*diff;//^2
            diffs[i] = diff;
            i++;
        }
        return (long) Math.sqrt(arithMean(diffs));
    }

    static long arithMean(long... longs){
        return LongStream.of(longs).sum()/longs.length;
    }
}

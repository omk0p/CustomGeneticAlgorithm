package c.m.impl;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Pavlo on 17.11.2015.
 */
public class Funcs {
  
  public static int[] fillArray(int size, int maxRand){
    int[] genes = new int[size];
    Random m_rand = new Random();
    for (int i = 0; i < genes.length; i++) {
      genes[i] = m_rand.nextInt(maxRand);
    }
    return genes;
  }
  
  public static void print(String s){
    System.out.println(s);
  }
  
  public static void print(int[] arr){
    System.out.println(Arrays.toString(arr));
  }
  
  public static String split(int[] arr){
    String result = "";
    for (int i = 0; i < arr.length; i++) {
      result += arr[i];
    }
    return result;
  }
  
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

    static int arithMean(int... ints){
        return IntStream.of(ints).sum()/ints.length;
    }
}

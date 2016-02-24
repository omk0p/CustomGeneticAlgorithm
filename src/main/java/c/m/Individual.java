package c.m;

import java.util.Random;

import c.m.impl.FitnessFunction;
import c.m.impl.Utils;

public class Individual
{
    public static final int SIZE = 10;
    private int[] genes = new int[SIZE];
    private int fitnessValue;
    private FitnessFunction ff;

    public Individual(FitnessFunction ff) {
      this.ff = ff;
    }

    public FitnessFunction getFf() {
      return ff;
    }

    public void setFf(FitnessFunction ff) {
      this.ff = ff;
    }

    public int getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(int fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public int getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, int gene) {
        this.genes[index] = gene;
    }

    public void randGenes() {
        Random rand = new Random();
        for(int i=0; i<SIZE; ++i) {
            this.setGene(i, rand.nextInt(2));
        }
    }

    public void mutate() {
        Random rand = new Random();
        int index = rand.nextInt(SIZE);
        this.setGene(index, 1-this.getGene(index));    // flip
    }

    public int evaluate() {
        int fitness = 0;
        /*for(int i=0; i<SIZE; ++i) {
            fitness += this.getGene(i);
        }*/
        fitness = (int) ff.result(Utils.split(genes), ff.sampleInputs(), ff.sampleTargets());//TODO lossy conversion
        this.setFitnessValue(fitness);

        return fitness;
    }
}
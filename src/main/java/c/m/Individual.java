package c.m;

import java.util.Random;

import c.m.ff.FitnessFunction;
import c.m.utils.Utils;

public class Individual {
	// bits (genom)
	public static int SIZE = 341;
	private int[] genes = new int[SIZE];
	private double fitnessValue;
	private FitnessFunction ff;

	public static void setSize(int size) {
		SIZE = size;
	}

	public Individual(FitnessFunction ff) {
		this.ff = ff;
	}

	public FitnessFunction getFf() {
		return ff;
	}

	public void setFf(FitnessFunction ff) {
		this.ff = ff;
	}

	public double getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(double fitnessValue) {
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
		for (int i = 0; i < SIZE; ++i) {
			this.setGene(i, rand.nextInt(2));
		}
	}

	public void mutate() {
		Random rand = new Random();
		int index = rand.nextInt(SIZE);
		this.setGene(index, 1 - this.getGene(index)); // flip
	}

	public double evaluate() {
		double fitness = 0;
		/*
		 * for(int i=0; i<SIZE; ++i) { fitness += this.getGene(i); }
		 */
		fitness = ff.evaluate(Utils.split(genes), ff.target());// TODO
																// lossy
																// conversion
		this.setFitnessValue(fitness);

		return fitness;
	}
}
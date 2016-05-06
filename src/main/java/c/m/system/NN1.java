package c.m.system;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

import c.m.jgap.NonLinearFFReverse;

public class NN1 implements Block, NN {

	IChromosome fittest;
	NonLinearFFReverse ff;
	String name;
	Genotype genotype = null;
	int numEvolutions = 800;

	public NN1(String name) {
		this.name = name;

		ff = new NonLinearFFReverse();

		Configuration gaConf = new DefaultConfiguration(name, name);
		gaConf.setPreservFittestIndividual(true);
		gaConf.setKeepPopulationSizeConstant(false);

		try {
			DoubleGene gene = new DoubleGene(gaConf, -1.5, 1.5);

			IChromosome sampleChromosome = new Chromosome(gaConf, gene, NonLinearFFReverse.CHROME_SIZE);
			gaConf.setSampleChromosome(sampleChromosome);
			gaConf.setPopulationSize(20);

			gaConf.setFitnessFunction(ff);
			genotype = Genotype.randomInitialGenotype(gaConf);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			System.exit(-2);
		}
	}

	@Override
	public double[][] output(double[][] in) {
		return ff.output(in);
	}

	@Override
	public void learnCompletely(double[][] input, double[][] target) {
		int percentEvolution = numEvolutions / 100;
		for (int i = 0; i < numEvolutions; i++) {
			genotype.evolve();
			// Print progress.
			// ---------------
			/*if (percentEvolution > 0 && i % percentEvolution == 0) {
				IChromosome fittest = genotype.getFittestChromosome();
				double fitness = fittest.getFitnessValue();
				System.out.println("Currently fittest Chromosome has fitness " + fitness);

			}*/
		}
		// Print summary.
		// --------------
		this.fittest = genotype.getFittestChromosome();

		//ff.evaluate(fittest);
		System.out.println("Fittest Chromosome has fitness " + fittest.getFitnessValue());
	}

	@Override
	public void learnOnce(double[][] input, double[][] target) {
		genotype.evolve();
		// Print progress.
		/*
		 * IChromosome fittest = genotype.getFittestChromosome(); double fitness
		 * = fittest.getFitnessValue(); System.out.println(
		 * "Currently fittest Chromosome has fitness " + fitness);
		 */
	}

	public double[][] outputBest(double[][] input) {
		ff.evaluate(genotype.getFittestChromosome());
		return ff.output(input);
	}

}

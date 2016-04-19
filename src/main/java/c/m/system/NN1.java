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
	NonLinearFFReverse ff = new NonLinearFFReverse();

	@Override
	public double[][] output(double[][] in) {
		return ff.output(in);
	}

	@Override
	public void learn() {
		int numEvolutions = 2000;
		Configuration gaConf = new DefaultConfiguration();
		gaConf.setPreservFittestIndividual(true);
		gaConf.setKeepPopulationSizeConstant(false);
		Genotype genotype = null;
		int chromeSize = 62;

		try {
			DoubleGene gene = new DoubleGene(gaConf, -1.0, 1.0);

			IChromosome sampleChromosome = new Chromosome(gaConf, gene, chromeSize);
			gaConf.setSampleChromosome(sampleChromosome);
			gaConf.setPopulationSize(20);

			gaConf.setFitnessFunction(ff);
			genotype = Genotype.randomInitialGenotype(gaConf);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			System.exit(-2);
		}
		int progress = 0;
		int percentEvolution = numEvolutions / 100;
		for (int i = 0; i < numEvolutions; i++) {
			genotype.evolve();
			// Print progress.
			// ---------------
			if (percentEvolution > 0 && i % percentEvolution == 0) {
				progress++;
				IChromosome fittest = genotype.getFittestChromosome();
				double fitness = fittest.getFitnessValue();
				System.out.println("Currently fittest Chromosome has fitness " + fitness);

			}
		}
		// Print summary.
		// --------------
		this.fittest = genotype.getFittestChromosome();
		/*
		 * ff.evaluate(fittest); System.out.println(
		 * "Fittest Chromosome has fitness " + fittest.getFitnessValue());
		 */
	}

}

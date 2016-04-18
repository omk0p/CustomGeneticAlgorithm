/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package c.m.jgap;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

public class SimpleExample {
	/** String containing the CVS revision. Read out via reflection! */
	private static final String CVS_REVISION = "$Revision: 1.9 $";

	/**
	 * Starts the example.
	 * 
	 * @param args
	 *            if optional first argument provided, it represents the number
	 *            of bits to use, but no more than 32
	 *
	 * @author Neil Rotstan
	 * @author Klaus Meffert
	 * @since 2.0
	 */
	public static void main(String[] args) {
		int numEvolutions = 2000;
		Configuration gaConf = new DefaultConfiguration();
		gaConf.setPreservFittestIndividual(true);
		gaConf.setKeepPopulationSizeConstant(false);
		Genotype genotype = null;
		int chromeSize;
		if (args.length > 0) {
			chromeSize = Integer.parseInt(args[0]);
		} else {
			chromeSize = 62;
		}

		NonLinearFF ff = new NonLinearFF();
		try {
			DoubleGene gene = new DoubleGene(gaConf, -1.5, 1.5);

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
		IChromosome fittest = genotype.getFittestChromosome();
		ff.evaluate(fittest);
		System.out.println("Fittest Chromosome has fitness " + fittest.getFitnessValue());

	}
}

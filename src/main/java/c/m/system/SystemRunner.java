package c.m.system;

import c.m.utils.Utils;

public class SystemRunner {

	static int MAX_ITERATIONS = 2000;

	void run(DataProvider dataProvider) {
		double[][] y;
		double[][] g = dataProvider.getTarget();
		NNError err2 = new NNError();
		NNError err1 = new NNError();
		NN1 nn1 = new NN1("nn1", dataProvider.getReverseInput(), dataProvider.getReverseTarget(), err1);
		NN2 nn2 = new NN2("nn2", dataProvider.getInput(), dataProvider.getTarget(), err2);
		BlockCO co = new DigStateSpaceCO();// CO();
		nn1.learnCompletely(dataProvider.getReverseInput(), dataProvider.getReverseTarget());
		nn2.learnCompletely(dataProvider.getInput(), dataProvider.getTarget());

		int percentEvolution = MAX_ITERATIONS / 100;
		int tenPercentEvolution = MAX_ITERATIONS / 10;
		long startMillis = System.currentTimeMillis();
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			// TODO filter implementation from schema(see all beginnings of the
			// schema in this method)
			double[][] u = nn1.output(g);
			y = co.output(u);
			if (percentEvolution > 0 && i % percentEvolution == 0) {
				// Utils.print("y", y);
				if (i / percentEvolution == 3) {
					double[][] uBest = nn1.outputBest(g);
					double[][] yBest = co.output(uBest);
					Utils.print("MSE for y", Utils.mse(yBest, dataProvider.getTarget()));
					co.influenceF();
					yBest = co.output(uBest);
					Utils.print("y right after influence", yBest);
					Utils.print("MSE for y CO influenced", Utils.mse(yBest, dataProvider.getTarget()));
				}
				// if (tenPercentEvolution > 0 && i % tenPercentEvolution == 0)
				// {
				// double[][] uBest = nn1.outputBest(g);
				// double[][] yBest = co.output(uBest);
				// Utils.print("MSE progress", "" + i + " " + Utils.mse(yBest,
				// dataProvider.getTarget()) + " "
				// + (System.currentTimeMillis() - startMillis) + " ms.");
				// }
			}

			double[][] ys = nn2.output(u);

			nn1.learnOnce(g, ys);
			nn2.learnOnce(u, y);
		}

		double[][] u = nn1.outputBest(g);
		y = co.output(u);
		Utils.print("y", y);
		Utils.print("MSE for y CO adapted",
				Utils.mse(y, dataProvider.getTarget()) + " " + (System.currentTimeMillis() - startMillis) + " ms.");
	}

	public static void main(String[] args) {
		new SystemRunner().run(DataProvider.getProvider());

	}

}

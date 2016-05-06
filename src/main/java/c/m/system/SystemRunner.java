package c.m.system;

import c.m.utils.Utils;

public class SystemRunner {

	static int MAX_ITERATIONS = 2000;
	double[][] y;
	double[][] g = DataProvider.getTarget();

	void run() {

		NN1 nn1 = new NN1("nn1");
		NN2 nn2 = new NN2("nn2");
		CO co = new CO();
		nn1.learnCompletely(DataProvider.getReverseInput(), DataProvider.getReverseTarget());
		nn2.learnCompletely(DataProvider.getInput(), DataProvider.getTarget());

		int percentEvolution = MAX_ITERATIONS / 100;

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
					Utils.print("MSE for y", Utils.mse(yBest, DataProvider.getTarget()));
					co.influenceF();
					yBest = co.output(uBest);
					Utils.print("MSE for y CO influenced", Utils.mse(yBest, DataProvider.getTarget()));
				}
			}
			double[][] ys = nn2.output(u);

			nn1.learnOnce(g, ys);
			nn2.learnOnce(u, y);
		}

		double[][] u = nn1.outputBest(g);
		y = co.output(u);
		// Utils.print("y", y);
		Utils.print("MSE for y CO adapted", Utils.mse(y, DataProvider.getTarget()));
	}

	public static void main(String[] args) {
		new SystemRunner().run();
	}
}

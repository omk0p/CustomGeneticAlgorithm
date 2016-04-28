package c.m.system;

import c.m.utils.Utils;

/**
 * time : 0..20 ramp1(u1): -10..10 ramp2(u2): -20..0 saturation range: -5..5
 */
public class COInitial {
	public static void main(String[] args) {

		// create source signal-time

		int[] time = time(0, 20);

		// iterate over it receiving output

		// region PrevCode
		System.out.print("Ramp signal 1/signal 2: [");
		for (int t : time) {
			System.out.print(Utils.ramp(-10, 1, t) + ",");
		}
		System.out.print(";");
		for (int t : time) {
			System.out.print(Utils.ramp(-20, 1, t) + ",");
		}
		System.out.print(";");
		for (int t : time) {
			System.out.print(Utils.ramp(0, 1, t) + ",");
		}
		System.out.println("]");

		System.out.print("Saturation  signal: ");
		for (int t : time) {
			System.out.print(Utils.saturation(-5, 5, Utils.ramp(-10, 1, t), 1) + ",");
		}
		System.out.println();

		System.out.print("Saturation  signal 2: ");
		for (int t : time) {
			System.out.print(Utils.saturation(-5, 5, Utils.ramp(-20, 1, t), 1) + ",");
		}
		System.out.println();

		System.out.print("Saturation  signal 3: ");
		for (int t : time) {
			System.out.print(Utils.saturation(-5, 5, Utils.ramp(0, 1, t), 1) + ",");
		}
		System.out.println();

		// endregion

		System.out.print("Saturation  signals sum: [");
		for (int t : time) {
			System.out.print(Utils.saturation(-5, 5, Utils.ramp(-20, 1, t), 1)
					+ Utils.saturation(-5, 5, Utils.ramp(-10, 1, t), 1) + ",");
		}
		System.out.println("]");

		System.out.print("Saturation  signals sum 2: [");
		for (int t : time) {
			System.out.print(Utils.saturation(-5, 5, Utils.ramp(-10, 1, t), 1)
					+ Utils.saturation(-5, 5, Utils.ramp(0, 1, t), 1) + ",");
		}
		System.out.println("]");

		// influenced

		System.out.print("Saturation influenced signals sum: [");
		for (int t : time) {
			System.out.print(Utils.saturation(-5, 5, Utils.ramp(-20, 1, t), 100)
					+ Utils.saturation(-5, 5, Utils.ramp(-10, 1, t), 100)

					+ ",");
		}
		System.out.println("]");

		System.out.print("Saturation influenced signals sum 2: [");
		for (int t : time) {
			System.out.print(Utils.saturation(-5, 5, Utils.ramp(-10, 1, t), 100)
					+ Utils.saturation(-5, 5, Utils.ramp(0, 1, t), 100) + ",");
		}
		System.out.println("]");

		// store output somehow somewhere

	}

	public static int[] time(int st, int et) {
		int[] rslt = new int[et - st + 1];
		int j = 0;
		for (int i = st; i <= et; i++, j++) {

			rslt[j] = i;
		}
		return rslt;
	}

}

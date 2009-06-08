package eu.webtoolkit.jwt.utils;

import java.util.Random;

public class MathUtils {

	public static final double EPSILON = 1E-5;

	public static int randomInt() {
		return random.nextInt();
	}

	static private Random random = new Random();

	public static String round(double v, int n) {
		return String.valueOf(Math.round(v * e[n]) / e[n]);
	}

	private static double e[] = new double[] { 1.0, 10.0, 100.0, 1000.0, 10000.0, 100000.0, 1000000.0 };
}

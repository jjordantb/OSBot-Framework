package io.paratek.api.util;

/**
 * @author Parametric on 3/9/2018
 * @project OSBot
 */
public class Random {

    public static int nextInt(final int max) {
        return new java.util.Random().nextInt(max);
    }

    public static int nextInt(final int min, final int max) {
        return new java.util.Random().nextInt(max - min) + min;
    }

    public static double nextDouble(final double max) {
        return nextDouble(0, max);
    }

    public static double nextDouble(final double min, final double max) {
        return min + (max - min) * new java.util.Random().nextDouble();
    }

    public static float nextFloat(final int max) {
        return nextFloat(0, max);
    }

    public static float nextFloat(final float min, final float max) {
        return min + (max - min) * new java.util.Random().nextFloat();
    }

    public static long nextLong(final long max) {
        return nextLong(0, max);
    }

    public static long nextLong(final long min, final long max) {
        return (new java.util.Random().nextLong() % (max - min)) + min;
    }

    public static boolean nextBoolean() {
        return new java.util.Random().nextBoolean();
    }

    public static double nextGaussian() {
        return new java.util.Random().nextGaussian();
    }

}

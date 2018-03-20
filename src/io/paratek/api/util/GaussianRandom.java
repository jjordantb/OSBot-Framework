package io.paratek.api.util;

public class GaussianRandom {

    public static int gRandomAboveZero(int mean, int standardDeviation) {
        int generated = gRandom(mean, standardDeviation);
        return generated > 0 ? generated : mean;
    }

    public static int gRandom(int mean, int standardDeviation) {
        new Random();
        return (int) (mean + Random.nextGaussian() * standardDeviation);
    }

    public static long gRandom(long mean, long standardDeviation) {
        return (long) (mean + Random.nextGaussian() * standardDeviation);
    }

    public static int gRandomInRange(int min, int max) {
        int mean = (min + max) / 2, std = (max - mean) / 3, n = gRandom(mean, std);
        while (n < min || n > max)
            n = gRandom(mean, std);
        return n;
    }

    public static long gRandomInRange(long min, long max) {
        long mean = (min + max) / 2, std = (max - mean) / 3, n = gRandom(mean, std);
        while (n < min || n > max)
            n = gRandom(mean, std);
        return n;
    }

    public static String gRandomInStringArray(String[] arr) {
        return arr[gRandomInRange(0, arr.length - 1)];
    }

}
package io.paratek.api.util;

import java.util.concurrent.Callable;

/**
 * @author Parametric on 3/9/2018
 * @project OSBot
 */
public class Execution {

    public static boolean delay(final int duration) {
        try {
            Thread.sleep(duration);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void delay(final int min, final int max) {
        delay(Random.nextInt(min, max));
    }

    public static boolean delayUntil(final Callable<Boolean> condition, final int min, final int max) {
        try {
            return delayUntil(condition, Random.nextInt(min, max));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delayUntil(final Callable<Boolean> condition, final int time) {
        try {
            long startTime = System.currentTimeMillis();
            while (!condition.call()) {
                if (System.currentTimeMillis() - startTime > time) {
                    return false;
                }
                delay(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delayUntil(final Callable<Boolean> condition) {
        try {
            while (!condition.call()) {
                delay(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delayGaussianStd(int mean, int std) {
        return delay(GaussianRandom.gRandom(mean, std));
    }

    public static boolean delayGaussian(int min, int max) {
        return delay(GaussianRandom.gRandomInRange(min, max));
    }

}

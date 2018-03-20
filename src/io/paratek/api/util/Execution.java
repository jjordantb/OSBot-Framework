package io.paratek.api.util;

import java.util.concurrent.Callable;

/**
 * @author Parametric on 3/9/2018
 * @project OSBot
 */
public class Execution {

    public static void delay(final int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

}

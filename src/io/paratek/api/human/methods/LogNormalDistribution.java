package io.paratek.api.human.methods;


import java.util.Random;

public class LogNormalDistribution {

    private final double scale;
    private final double shape;

    private final Random random;

    public LogNormalDistribution(double scale, double shape) {
        this.scale = scale;
        this.shape = shape;
        this.random = new Random();
    }

    public double sample() {
        double n = this.random.nextGaussian();
        return 2 * Math.exp(this.scale + this.shape * n);
    }

}

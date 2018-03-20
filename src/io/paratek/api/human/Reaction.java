package io.paratek.api.human;

import io.paratek.api.human.methods.LogNormalDistribution;
import io.paratek.api.util.Random;

public class Reaction {

    public static int getReactionTime() {
        return Math.abs((int)(new LogNormalDistribution(0, Random.nextDouble(0.58, 0.62)).sample() * 1000) - 500);
    }

//    public static int getReactionTime(final PlayerID playerID) {
//        return (int) (Math.abs((int)(new LogNormalDistribution(0, Random.nextDouble(0.58, 0.62)).sample() * 1000) - 500) * playerID.getReactionTimeModifier());
//    }

    public static int timePlayedModifier(final int minsPlayed) {
        return minsPlayed * minsPlayed;
    }

}

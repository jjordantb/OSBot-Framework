package io.paratek.fw.statistic;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public class RuntimeStatistic {

    public static RuntimeStatistic init(final int startVal) {
        return new RuntimeStatistic(startVal);
    }

    private int stat;

    private RuntimeStatistic(int stat) {
        this.stat = stat;
    }

    public int getPer(final long elapsed) {
        if (stat > 0 && elapsed > 0L) {
            return (int) (3600000 / elapsed * stat);
        }
        return -1;
    }

    public int value() {
        return stat;
    }

    public void increment(int value) {
        this.stat += value;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

}

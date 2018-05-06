package io.paratek.fw.statistic;

import java.util.LinkedHashMap;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public class StatisticContainer {

    private final LinkedHashMap<String, RuntimeStatistic> statistics = new LinkedHashMap<>();

    public RuntimeStatistic get(final String key) {
        return this.statistics.get(key);
    }

    public void replace(final String key, final RuntimeStatistic statistic) {
        this.statistics.remove(key, statistic);
    }

    public void register(final String key, final RuntimeStatistic statistic) {
        this.statistics.put(key, statistic);
    }

    public void remove(final String key) {
        this.statistics.remove(key);
    }

    public void addTo(final String key, final int value) {
        if (this.statistics.containsKey(key)) {
            this.statistics.get(key).increment(value);
        }
    }

}

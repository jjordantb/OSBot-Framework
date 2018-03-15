package io.paratek.fw.statistic;

import java.util.LinkedHashMap;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public class StatisticContainer<T> {

    private final LinkedHashMap<String, RuntimeStatistic<T>> statistics = new LinkedHashMap<>();

    public RuntimeStatistic<T> retreiveStatistic(final String key) {
        return this.statistics.get(key);
    }

    public void registerStatistic(final String key, final RuntimeStatistic<T> statistic) {
        this.statistics.put(key, statistic);
    }

    public void removeStatistic(final String key) {
        this.statistics.remove(key);
    }

}

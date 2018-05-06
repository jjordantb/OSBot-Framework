package io.paratek.fw.statistic.tools;

import io.paratek.fw.statistic.RuntimeStatistic;
import io.paratek.fw.statistic.StatisticFormatter;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public class Format {

    public static String on(final RuntimeStatistic statistic, final StatisticFormatter formatter) {
        return formatter.format(statistic);
    }

}

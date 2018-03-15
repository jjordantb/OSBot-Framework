package io.paratek.fw;

import io.paratek.fw.statistic.StatisticContainer;
import io.paratek.fw.statistic.StatisticFormatter;
import org.osbot.rs07.script.Script;

import java.util.Optional;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public abstract class ParaScript extends Script {


    private final StatisticContainer<String> statisticContainer = new StatisticContainer<>();

    public ParaScript() {
        super();
        getStatisticContainer().registerStatistic("TIME", () -> Optional.of("Time: "));
        StatisticFormatter.of(getStatisticContainer().retreiveStatistic("TIME"));

    }

    public StatisticContainer<String> getStatisticContainer() {
        return statisticContainer;
    }

}

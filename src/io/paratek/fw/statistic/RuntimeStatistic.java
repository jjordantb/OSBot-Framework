package io.paratek.fw.statistic;

import java.util.Optional;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public interface RuntimeStatistic<T> {

    Optional<T> get();

}

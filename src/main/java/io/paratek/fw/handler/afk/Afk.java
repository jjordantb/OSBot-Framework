package io.paratek.fw.handler.afk;

import io.paratek.fw.util.Timer;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class Afk {

    private final long startTime;
    private final long duration;

    public Afk(long startTime, long duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isActive(final Timer timer) {
        return timer.getTimeElapsed() > startTime;
    }

    public boolean isComplete(final Timer timer) {
        return this.startTime + this.duration > timer.getTimeElapsed();
    }

}

package io.paratek.fw.util;

/**
 * @author Parametric on 3/8/2018
 * @project osap
 */
public class Timer {

    private long startTime;
    private long pauseTime;

    public Timer() {
        this.startTime = System.currentTimeMillis();
    }

    public long getTimeElapsed() {
        return pauseTime == 0 ? System.currentTimeMillis() - startTime
                : pauseTime - startTime;
    }

    public long getSecondsElapsed() {
        return getTimeElapsed() / 1000 % 60;
    }

    public long getMinutesElapsed() {
        return getTimeElapsed() / 1000 / 60 % 60;
    }

    public long getHoursElapsed() {
        return getTimeElapsed() / 1000 / 60 / 60 % 24;
    }

    public long getDaysElapsed() {
        return getTimeElapsed() / 1000 / 60 / 60 / 24 % 7;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
        pauseTime = 0;
    }

    public String getFormattedTime() {
        return getDaysElapsed() > 0 ? "" + getDaysElapsed() + ":"
                + getHoursElapsed() + ":" + getMinutesElapsed() + ":"
                + getSecondsElapsed() + "" : getHoursElapsed() + ":"
                + getMinutesElapsed() + ":" + getSecondsElapsed();
    }

    public String longToFormattedTime(final long time) {
        long daysElapsed = time / 1000 / 60 / 60 / 24;
        long hoursElapsed = time / 1000 / 60 / 60 % 24;
        long minutesElapsed = time / 1000 / 60 % 60;
        long secondsElapsed = time / 1000 % 60;
        return daysElapsed > 0 ? "" + daysElapsed + ":"
                + hoursElapsed + ":" + minutesElapsed + ":"
                + secondsElapsed + "" : hoursElapsed + ":"
                + minutesElapsed+ ":" + secondsElapsed;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void pause() {
        pauseTime = System.currentTimeMillis();
    }

    public void resume() {
        if (isPaused()) {
            startTime += (System.currentTimeMillis() - pauseTime);
            pauseTime = 0;
        }
    }

    private boolean isPaused() {
        return pauseTime > 0;
    }

}

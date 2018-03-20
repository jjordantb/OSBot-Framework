package io.paratek.fw;

import io.paratek.api.listeners.EventDispatcher;
import io.paratek.api.util.Random;
import io.paratek.fw.handler.afk.AfkHandler;
import io.paratek.fw.handler.node.NodeModuleHandler;
import io.paratek.fw.statistic.StatisticContainer;
import io.paratek.fw.util.Timer;
import org.osbot.rs07.script.Script;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public abstract class ParaScript extends Script {

    private int minLoopDelay = 200, maxLoopDelay = 400;
    private EventDispatcher eventDispatcher;

    private final Timer timer = new Timer();
    private final StatisticContainer statisticContainer = new StatisticContainer();
    private final AfkHandler afkHandler = new AfkHandler(timer);
    private final NodeModuleHandler moduleHandler = new NodeModuleHandler(this);

    public ParaScript() {
        super();
    }

    @Override
    public void onStart() {
        this.eventDispatcher = new EventDispatcher(this);
    }

    @Override
    public int onLoop() throws InterruptedException {
        this.moduleHandler.run();
        return Random.nextInt(this.minLoopDelay, this.maxLoopDelay);
    }

    @Override
    public void onExit() {

    }

    @Override
    public void pause() {
        this.timer.pause();
    }

    @Override
    public void resume() {
        this.timer.resume();
    }

    public void setLoopDelay(final int min, final int max) {
        this.minLoopDelay = min;
        this.maxLoopDelay = max;
    }

    public NodeModuleHandler getModuleHandler() {
        return moduleHandler;
    }

    public StatisticContainer getStatistics() {
        return statisticContainer;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public AfkHandler getAfkHandler() {
        return afkHandler;
    }

    public Timer getTimer() {
        return timer;
    }

    public abstract boolean canAfk();

}

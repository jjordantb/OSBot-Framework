package io.paratek.fw.handler.afk;

import io.paratek.fw.util.Timer;

import java.util.LinkedList;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class AfkHandler {

    private Afk next;
    private final LinkedList<Afk> afks = new LinkedList<>();

    private final Timer timer;

    private boolean afk = false;

    public AfkHandler(Timer timer) {
        this.timer = timer;
    }

    public void run() {
        if (next == null) {
            if (this.afks.size() > 0) {
                this.next = this.afks.removeFirst();
            }
        } else {
            if (next.isComplete(this.timer)) {
                this.next = null;
                this.afk = false;
            } else if (next.isActive(this.timer)) {
                this.afk = true;
            }
        }
    }

    public boolean isAfk() {
        return afk;
    }
}

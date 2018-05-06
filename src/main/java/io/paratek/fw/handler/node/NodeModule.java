package io.paratek.fw.handler.node;

import io.paratek.api.listeners.Listener;
import io.paratek.fw.ParaScript;
import io.paratek.fw.util.Condition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public abstract class NodeModule {

    protected Condition shouldStop;
    protected final ParaScript ctx;
    protected final LinkedList<Node> nodes = new LinkedList<>();

    public NodeModule(ParaScript ctx) {
        this.ctx = ctx;
    }

    public void register(final Node node) {
        this.nodes.add(node);
        if (node instanceof Listener) {
            this.ctx.getEventDispatcher().submitListener((Listener) node);
        }
    }

    public void remove(final Node node) {
        this.nodes.removeIf(node1 -> node1.equals(node));
    }

    public boolean run() throws InterruptedException {
        for (Node node : nodes) {
            if (node.activate(this.ctx)) {
                ctx.log("Running: " + node.getClass().getName());
                if (node.execute(this.ctx)) {
                    break;
                }
            }
        }
        return shouldStop.validate();
    }

    public void setStopAt(final Condition stopAt) {
        this.shouldStop = stopAt;
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }
}

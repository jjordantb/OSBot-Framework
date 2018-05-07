package io.paratek.tut.modules.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import org.osbot.rs07.api.ui.RS2Widget;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class SmithDaggerNode implements Node {

    private RS2Widget widget;

    @Override
    public boolean activate(ParaScript ctx) {
        return (widget = ctx.widgets.get(312, 2, 2)) != null && widget.isVisible();
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (widget.interact()) {
            Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 1500, 2500);
        }
        return false;
    }

}

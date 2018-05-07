package io.paratek.tut.modules.n_nodes;

import io.paratek.api.util.Execution;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import org.osbot.rs07.api.ui.RS2Widget;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class RandomizeAppearanceNode implements Node {

    private RS2Widget widget;

    private static final int[] IDS = new int[]{106, 113, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119,
                                                105, 123, 122, 124, 125, 121, 127, 129, 130, 131};

    public boolean activate(ParaScript ctx) {
        int WELCOME_PARENT = 269;
        int WELCOME_CHILD = 97;
        return (widget = ctx.widgets.get(WELCOME_PARENT, WELCOME_CHILD)) != null && widget.isVisible()
                && widget.getMessage().contains("Welcome to RuneScape");
    }

    public boolean execute(ParaScript ctx) throws InterruptedException {
        // Set Sex
        if (Random.nextInt(0, 2) == 0) {
            final RS2Widget sex;
            if ((sex = ctx.widgets.get(269, 137)) != null && sex.isVisible()) {
                if (sex.interact()) {
                    Execution.delayGaussian(750, 1250);
                }
            }
        }
        // Randomize Appearance
        int iters = Random.nextInt(5, 15);
        for (int i = 0; i < iters; i++) {
            int cur = IDS[Random.nextInt(0, IDS.length)];
            final RS2Widget tmp;
            if ((tmp = ctx.widgets.get(269, cur)) != null && tmp.isVisible()) {
                if (tmp.interact()) {
                    Execution.delayGaussian(250, 750);
                }
            }
        }
        // Finalize
        final RS2Widget accept;
        if ((accept = ctx.widgets.get(269, 99)) != null && accept.isVisible()) {
            if (accept.interact()) {
                Execution.delayUntil(() -> !accept.isVisible(), 750, 1250);
            }
        }
        return !widget.isVisible();
    }

}

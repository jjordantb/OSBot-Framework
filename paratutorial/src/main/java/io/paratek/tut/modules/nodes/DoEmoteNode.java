package io.paratek.tut.modules.nodes;

import io.paratek.api.util.Execution;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.ui.RS2Widget;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class DoEmoteNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return TutorialState.DO_EMOTE.isState(ctx);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        int start = ctx.configs.get(TutorialState.PARENT_STATE);
        int rand = Random.nextInt(1, 20);
        final RS2Widget w;
        if ((w = ctx.widgets.get(216, 1, rand)) != null) {
            if (w.interact()) {
                Execution.delayUntil(() -> ctx.configs.get(TutorialState.PARENT_STATE) != start, 1500, 2500);
            }
        }
        return false;
    }

}

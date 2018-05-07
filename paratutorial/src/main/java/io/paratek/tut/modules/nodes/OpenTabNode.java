package io.paratek.tut.modules.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.Widgets;
import org.osbot.rs07.api.ui.RS2Widget;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class OpenTabNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return TutorialState.OPEN_OPTIONS.isState(ctx) ||
                TutorialState.OPEN_SKILLS.isState(ctx) ||
                TutorialState.OPEN_INVENTORY.isState(ctx) ||
                TutorialState.OPEN_MUSIC.isState(ctx) ||
                TutorialState.OPEN_EMOTE.isState(ctx) ||
                TutorialState.OPEN_OPTIONS_2.isState(ctx) ||
                TutorialState.OPEN_QUEST.isState(ctx);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        System.out.println("TABB");
        if (TutorialState.OPEN_OPTIONS.isState(ctx) || TutorialState.OPEN_OPTIONS_2.isState(ctx)) {
            run(ctx, 35);
        } else if (TutorialState.OPEN_INVENTORY.isState(ctx)) {
            run(ctx, 51);
        } else if (TutorialState.OPEN_SKILLS.isState(ctx)) {
            run(ctx, 49);
        } else if (TutorialState.OPEN_MUSIC.isState(ctx)) {
            run(ctx, 37);
        } else if (TutorialState.OPEN_EMOTE.isState(ctx)) {
            run(ctx, 36);
        } else if (TutorialState.OPEN_QUEST.isState(ctx)) {
            run(ctx, 50);
        }
        return false;
    }

    private void run(ParaScript ctx, int wid) {
        final int state = ctx.configs.get(TutorialState.PARENT_STATE);
        final RS2Widget w;
        if ((w = ctx.widgets.get(548, wid)) != null && w.interact()) {
            Execution.delayUntil(() -> ctx.configs.get(TutorialState.PARENT_STATE) != state, 750, 1500);
        }
    }

}

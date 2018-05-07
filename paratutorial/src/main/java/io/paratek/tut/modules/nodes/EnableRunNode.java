package io.paratek.tut.modules.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class EnableRunNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return TutorialState.ENABLE_RUN.isState(ctx);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.settings.setRunning(true)) {
            Execution.delayUntil(() -> ctx.settings.isRunning(), 1500, 2500);
        }
        return false;
    }

}

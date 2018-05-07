package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class TalkToNode implements Node {

    private final TutorialState state;
    private final String npcName;

    public TalkToNode(TutorialState state, String npcName) {
        this.state = state;
        this.npcName = npcName;
    }

    @Override
    public boolean activate(ParaScript ctx) {
        return this.state.isState(ctx);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        return false;
    }

}

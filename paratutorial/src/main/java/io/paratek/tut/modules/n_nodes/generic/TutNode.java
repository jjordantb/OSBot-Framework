package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public abstract class TutNode implements Node {

    protected final TutorialState[] states;

    public TutNode(TutorialState... states) {
        this.states = states;
    }

    @Override
    public boolean activate(ParaScript ctx) {
        return this.stateActivated(ctx, this.states);
    }

    private boolean stateActivated(final ParaScript ctx, final TutorialState... states) {
        boolean val = false;
        for (TutorialState state : this.states) {
            if (state.isState(ctx)) {
                val = true;
                break;
            }
        }
        return val;
    }

    protected void clearSelection(ParaScript ctx) {
        if (ctx.inventory.isItemSelected()) {
            ctx.inventory.deselectItem();
        }
    }

}

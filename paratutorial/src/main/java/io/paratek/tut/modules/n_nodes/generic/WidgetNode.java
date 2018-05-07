package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.tut.modules.n_nodes.nodeutil.CtxCondition;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.ui.RS2Widget;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class WidgetNode extends TutNode {

    private final String action;
    private final int parent, child, grandchild;
    private final CtxCondition[] conditions;

    public WidgetNode(String action, int parent, int child, int grandchild, CtxCondition[] conditions, TutorialState... states) {
        super(states);
        this.action = action;
        this.parent = parent;
        this.child = child;
        this.grandchild = grandchild;
        this.conditions = conditions;
    }

    public WidgetNode(String action, int parent, int child, CtxCondition[] conditions, TutorialState... states) {
        super(states);
        this.action = action;
        this.parent = parent;
        this.child = child;
        this.conditions = conditions;
        this.grandchild = -1;
    }

    @Override
    public boolean activate(ParaScript ctx) {
        return super.activate(ctx) && this.validateCtx(ctx, this.conditions);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (this.grandchild != -1) {
            int start = ctx.configs.get(TutorialState.PARENT_STATE);
            final RS2Widget w;
            if ((w = ctx.widgets.get(this.parent, this.child, this.grandchild)) != null) {
                if (w.interact()) {
                    Execution.delayUntil(() -> ctx.configs.get(TutorialState.PARENT_STATE) != start, 1500, 2500);
                }
            }
        } else {
            int start = ctx.configs.get(TutorialState.PARENT_STATE);
            final RS2Widget w;
            if ((w = ctx.widgets.get(this.parent, this.child)) != null) {
                if (w.interact()) {
                    Execution.delayUntil(() -> ctx.configs.get(TutorialState.PARENT_STATE) != start, 1500, 2500);
                }
            }
        }
        return false;
    }

    public boolean validateCtx(final ParaScript ctx, final CtxCondition[] ctxConditions) {
        boolean valid = true;
        for (CtxCondition condition : ctxConditions) {
            if (!condition.activate(ctx)) {
                valid = false;
            }
        }
        return valid;
    }
    
}

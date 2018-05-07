package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.api.util.Execution;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.util.Condition;
import io.paratek.tut.modules.n_nodes.nodeutil.CtxCondition;
import io.paratek.tut.modules.n_nodes.nodeutil.CtxRunnable;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.model.Item;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class ItemOnItemNode extends TutNode {

    private final int RAND = Random.nextInt(0, 2);

    private final String name1, name2;
    private final Condition allow;
    private final CtxRunnable fix;
    private final CtxCondition[] otherConds;

    public ItemOnItemNode(String name1, String name2,
                          Condition allow, CtxRunnable fix,
                          CtxCondition[] otherConds,
                          TutorialState... states) {
        super(states);
        this.otherConds = otherConds;
        this.name1 = name1;
        this.name2 = name2;
        this.allow = allow;
        this.fix = fix;
    }

    @Override
    public boolean activate(ParaScript ctx) {
        return super.activate(ctx) || this.otherActivation(ctx);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (!ctx.myPlayer().isAnimating()) {
            if (this.allow.validate()) {
                if (ctx.inventory.isItemSelected()) {
                    if (ctx.inventory.getSelectedItemName().equals(name1)) {
                        this.select(this.name2, ctx);
                    } else if (ctx.inventory.getSelectedItemName().equals(name2)) {
                        this.select(this.name1, ctx);
                    } else {
                        ctx.inventory.deselectItem();
                    }
                } else {
                    if (RAND == 0) {
                        this.select(this.name1, ctx);
                    } else {
                        this.select(this.name2, ctx);
                    }
                }
            } else {
                this.fix.run(ctx);
            }
        }
        return false;
    }

    private boolean select(final String name, final ParaScript ctx) {
        final Item i;
        if ((i = ctx.inventory.getItem(name)) != null && i.interact("Use")) {
            Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 750, 1500);
        }
        return ctx.inventory.isItemSelected() && ctx.inventory.getSelectedItemName().equals(name);
    }

    private boolean otherActivation(final ParaScript ctx) {
        for (CtxCondition cond : this.otherConds) {
            if (cond.activate(ctx)) {
                return true;
            }
        }
        return false;
    }


}

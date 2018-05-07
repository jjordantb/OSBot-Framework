package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.api.interaction.viewport.EntityInteraction;
import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.util.Condition;
import io.paratek.tut.modules.n_nodes.nodeutil.CtxCondition;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class ItemOnObjectNode extends TutNode {

    private final String itemName, objectName;
    private final Condition validate;
    private final CtxCondition[] ctxConditions;

    public ItemOnObjectNode(String itemName, String objectName, Condition validate, CtxCondition[] ctxConditions, TutorialState... states) {
        super(states);
        this.itemName = itemName;
        this.objectName = objectName;
        this.validate = validate;
        this.ctxConditions = ctxConditions;
    }

    @Override
    public boolean activate(ParaScript ctx) {
        return super.activate(ctx) && this.validateCtx(ctx, this.ctxConditions);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.inventory.isItemSelected()) {
            if (ctx.inventory.getSelectedItemName().equals(this.itemName)) {
                final RS2Object object;
                if ((object = ctx.objects.closest(this.objectName)) != null) {
                    new EntityInteraction("Use", 20, object, this.validate).execute(ctx);
                }
            } else {
                ctx.inventory.deselectItem();
            }
        } else {
            final Item i;
            if ((i = ctx.inventory.getItem(this.itemName)) != null && i.interact("Use")) {
                Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 750, 1500);
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

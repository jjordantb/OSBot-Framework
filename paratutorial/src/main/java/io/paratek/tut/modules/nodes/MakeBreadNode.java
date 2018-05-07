package io.paratek.tut.modules.nodes;

import io.paratek.api.interaction.viewport.EntityInteraction;
import io.paratek.api.util.Execution;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class MakeBreadNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.inventory.contains("Pot of flour") || ctx.inventory.contains("Bread dough");
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.inventory.contains("Pot of flour")) {
            if (ctx.inventory.isItemSelected()) {
                if (ctx.inventory.getSelectedItemName().equals("Pot of flour")) {
                    if (this.selectItem("Bucket of water", ctx)) {
                        Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 750, 1500);
                    }
                } else {
                    ctx.inventory.deselectItem();
                }
            } else {
                if (this.selectItem("Pot of flour", ctx)) {
                    Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 750, 1500);
                }
            }
        } else {
            if (ctx.inventory.isItemSelected()) {
                if (ctx.inventory.getSelectedItemName().equals("Bread dough")) {
                    final RS2Object fish;
                    if ((fish = ctx.objects.closest("Range")) != null) {
                        new EntityInteraction("Use", Random.nextInt(1500, 2500), fish, () -> ctx.myPlayer().isAnimating())
                                .execute(ctx);
                    }
                } else {
                    ctx.inventory.deselectItem();
                }
            } else {
                if (this.selectItem("Bread dough", ctx)) {
                    Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 750, 1500);
                }
            }
        }
        return false;
    }

    private boolean selectItem(final String item, ParaScript ctx) {
        final Item i;
        if ((i = ctx.inventory.getItem(item)) != null) {
            return i.interact();
        }
        return false;
    }

}

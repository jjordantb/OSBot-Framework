package io.paratek.tut.modules.nodes;

import io.paratek.api.interaction.viewport.EntityInteraction;
import io.paratek.api.util.Execution;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class CookNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return (TutorialState.COOK_SHRIMP.isState(ctx) || TutorialState.BURN_CATCH_COOK.isState(ctx))
                && ctx.objects.closest("Fire") != null;
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        System.out.println("Cook");
        if (!ctx.myPlayer().isAnimating()) {
            if (ctx.inventory.contains("Raw shrimps")) {
                if (ctx.inventory.isItemSelected()) {
                    if (ctx.inventory.getSelectedItemName().equals("Raw shrimps")) {
                        final RS2Object object;
                        if ((object = ctx.objects.closest("Fire")) != null) {
                            if (object.isVisible()) {
                                if (object.interact("Use")) {
                                    Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 1500, 2500);
                                }
                            } else {
                                if (object.getPosition().distance(ctx.myPosition()) > 8) {
                                    ctx.walking.walk(object);
                                } else {
                                    ctx.camera.toEntity(object);
                                }
                            }
                        }
                    } else {
                        ctx.inventory.deselectItem();
                    }
                } else {
                    final Item item;
                    if ((item = ctx.inventory.getItem("Raw shrimps")) != null && item.interact()) {
                        Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 750, 1500);
                    }
                }
            } else {
                final NPC fish;
                if ((fish = ctx.npcs.closest("Fishing spot")) != null) {
                    new EntityInteraction("Net", Random.nextInt(1500, 2500), fish, () -> ctx.myPlayer().isAnimating())
                            .execute(ctx);
                }
            }
        }
        return false;
    }

}

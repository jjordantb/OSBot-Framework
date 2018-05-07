package io.paratek.tut.modules.nodes;

import io.paratek.api.interaction.viewport.EntityInteraction;
import io.paratek.api.util.Execution;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.HintArrow;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;

import java.util.List;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class HandleObjectNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.hintArrow.isActive() && ctx.hintArrow.getType() == HintArrow.HintArrowType.POSITION;
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        System.out.println("OBJECT NODE");
        if (!ctx.myPlayer().isAnimating()) {
            final Position arrow = ctx.hintArrow.getPosition();
            final List<RS2Object> objects;
            if ((objects = ctx.objects.get(arrow.getX(), arrow.getY())) != null && objects.size() > 0) {
                for (RS2Object o : objects) {
                    if (o.getUID() > 0) {
                        if (o.getName().equals("Furnace")) {
                            if (ctx.inventory.isItemSelected()) {
                                if (ctx.inventory.getSelectedItemName().equals("Copper ore")) {
                                    interact(o, ctx);
                                } else {
                                    ctx.inventory.deselectItem();
                                }
                            } else {
                                final Item i;
                                if ((i = ctx.inventory.getItem("Copper ore")) != null) {
                                    if (i.interact()) {
                                        Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 1500, 2500);
                                    }
                                }
                            }
                        } else {
                            this.interact(o, ctx);
                        }
                    }
                }
            }
        }
        return false;
    }

    private void interact(final RS2Object o, final ParaScript ctx) {
        int start = ctx.configs.get(TutorialState.PARENT_STATE);
        String action = o.getActions()[0];
        if (ctx.widgets.getWidgetContainingText("Prospecting") != null) {
            action = "Prospect";
        }
        new EntityInteraction(action, Random.nextInt(1500, 2500), o,
                () -> start != ctx.configs.get(TutorialState.PARENT_STATE))
                .execute(ctx);
    }

}

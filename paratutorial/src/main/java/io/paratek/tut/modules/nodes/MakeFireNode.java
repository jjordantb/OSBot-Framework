package io.paratek.tut.modules.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class MakeFireNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return TutorialState.MAKE_FIRE.isState(ctx) ||
                ((TutorialState.COOK_SHRIMP.isState(ctx) || TutorialState.BURN_CATCH_COOK.isState(ctx))
                        && ctx.objects.closest("Fire") == null);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (!ctx.myPlayer().isAnimating()) {
            if (ctx.inventory.contains("Logs")) {
                if (this.tileGood(ctx, ctx.myPosition())) {
                    if (ctx.inventory.isItemSelected()) {
                        final String selec = ctx.inventory.getSelectedItemName();
                        final boolean val;
                        if ((val = selec.equals("Tinderbox")) || selec.equals("Logs")) {
                            if (val) {
                                final Item item;
                                if ((item = ctx.inventory.getItem("Logs")) != null && item.interact()) {
                                    Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 750, 1500);
                                }
                            } else {
                                final Item item;
                                if ((item = ctx.inventory.getItem("Tinderbox")) != null && item.interact()) {
                                    Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 750, 1500);
                                }
                            }
                        } else {
                            ctx.inventory.deselectItem();
                        }
                    } else {
                        final Item item;
                        if ((item = ctx.inventory.getItem("Logs")) != null && item.interact()) {
                            Execution.delayUntil(() -> ctx.inventory.isItemSelected(), 750, 1500);
                        }
                    }
                } else {
                    System.out.println("Find another tile pls"); // TODO
                }
            } else {
                final RS2Object actual;
                if ((actual = ctx.objects.closest("Tree")) != null) {
                    if (actual.isVisible()) {
                        if (!ctx.myPlayer().isMoving()) {
                            if (actual.interact()) {
                                Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 1500, 2500);
                            }
                        }
                    } else {
                        if (actual.getPosition().distance(ctx.myPosition()) > 8) {
                            ctx.walking.webWalk(actual.getPosition());
                        } else {
                            ctx.camera.toEntity(actual);
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean tileGood(ParaScript ctx, final Position p) {
        for (RS2Object o : ctx.objects.get(p.getX(), p.getY())) {
            if (o.getName() != null && o.getName().equals("Fire")) {
                return false;
            }
        }
        return true;
    }

}

package io.paratek.scripts.ParaSmithing.modules.smith.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.fw.util.Generic;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;

/**
 * @author Parametric on 3/18/2018
 * @project OSBot-Framework
 */
public class SmithNode implements Node {

    private long lastAnim = System.currentTimeMillis();

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.inventory.contains(2351);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.inventory.isItemSelected()) {
            ctx.inventory.deselectItem();
        } else {
            if (!ctx.myPlayer().isAnimating() && System.currentTimeMillis() - this.lastAnim > 1500) {
                final RS2Widget smith;
                if ((smith = this.getSmith(ctx)) != null && smith.isVisible()) {
                    if (smith.interact("Smith All sets")) {
                        Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 750, 1500);
                    }
                } else {
                    final RS2Object anivl;
                    if ((anivl = ctx.objects.closest("Anvil")) != null) {
                        if (anivl.isVisible() && !ctx.myPlayer().isMoving()) {
                            if (anivl.interact("Smith")) {
                                Execution.delayUntil(() -> {
                                    final RS2Widget w;
                                    return (w = this.getSmith(ctx)) != null && w.isVisible();
                                }, 1500, 2750);
                            }
                        } else {
                            if (Generic.getDestination(ctx).distance(anivl) > 2 && ctx.walking.walk(anivl)) {
                                Execution.delayUntil(anivl::isVisible, 750, 1250);
                            }
                        }
                    }
                }
            } else {
                if (ctx.myPlayer().isAnimating()) {
                    this.lastAnim = System.currentTimeMillis();
                }
                if (ctx.mouse.isOnScreen()) {
                    ctx.mouse.moveOutsideScreen();
                }
            }
        }
        return false;
    }

    private RS2Widget getSmith(ParaScript ctx) {
        return ctx.widgets.get(312, 23);
    }

}

package io.paratek.scripts.ParaOrbs.air.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.scripts.ParaOrbs.OrbOps;
import io.paratek.scripts.ParaOrbs.air.AirOps;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;

import java.util.Arrays;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class ToOby implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.inventory.contains("Law rune", "Earth rune")
                && ctx.inventory.getAmount(OrbOps.UNP_ORB) == 25
                && ctx.inventory.getAmount("Cosmic rune") == 75;
    }

    @Override
    public boolean execute(ParaScript ctx) {
        if (this.inDungeon(ctx)) {
            final RS2Widget widget;
            if ((widget = ctx.widgets.get(475, 11)) != null && widget.isVisible()) {
                if (widget.interact()) {
                    Execution.delayUntil(() -> !widget.isVisible(), 750, 1500);
                }
            } else {
                if (ctx.walking.webWalk(AirOps.ORB_AREA)) {
                    Execution.delayUntil(() -> AirOps.ORB_AREA.contains(ctx.myPosition()), 750, 1250);
                }
            }
        } else {
            final RS2Object trapDoor;
            if ((trapDoor = ctx.objects.closest("Trapdoor")) != null) {
                if (trapDoor.isVisible()) {
                    ctx.walking.webWalk(AirOps.ORB_AREA);
                } else {
                    ctx.walking.walkPath(Arrays.asList(AirOps.toTrapDoor));
                }
            }
        }
        return false;
    }

    private boolean inDungeon(ParaScript ctx) {
        return ctx.myPosition().getY() > 9000;
    }

}

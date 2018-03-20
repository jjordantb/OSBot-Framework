package io.paratek.scripts.ParaOrbs.air.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.scripts.ParaOrbs.OrbOps;
import io.paratek.scripts.ParaOrbs.air.AirOps;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Spells;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class ToBank implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return !AirOps.BANK_AREA.contains(ctx.myPosition())
                && (!ctx.inventory.contains(OrbOps.UNP_ORB) || !ctx.inventory.contains("Law rune", "Earth rune"));
    }

    @Override
    public boolean execute(ParaScript ctx) {
        ctx.log("TO BANK");
        if (!AirOps.ORB_AREA.contains(ctx.myPosition())) {
            if (this.inHouse(ctx)) {
                final RS2Object glory;
                if ((glory = ctx.objects.closest("Amulet of Glory")) != null) {
                    if (glory.isVisible() && glory.interact()) {
                        Execution.delayUntil(() -> AirOps.BANK_AREA.contains(ctx.myPosition()), 1500, 2500);
                    } else {
                        ctx.camera.toEntity(glory);
                    }
                }
            } else {
                // out of house
                // dead
                // else web walk
            }
        } else {
            if (ctx.inventory.contains("Law rune", "Earth rune")) {
                if (ctx.magic.castSpell(Spells.NormalSpells.TELEPORT_TO_HOUSE)) {
                    Execution.delayUntil(() -> this.inHouse(ctx), 1500, 2500);
                }
            } else {
                if (!this.inHouse(ctx)) {
                    ctx.log("No teleport runes");
                    ctx.logoutTab.logOut();
                    ctx.stop();
                }
            }
        }
        return false;
    }

    private boolean inHouse(ParaScript ctx) {
        return ctx.objects.closest(rs2Object ->
                rs2Object.getName().equals("Portal") && rs2Object.hasAction("Lock")) != null;
    }

}

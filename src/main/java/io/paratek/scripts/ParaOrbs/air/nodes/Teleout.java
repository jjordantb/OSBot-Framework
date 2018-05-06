package io.paratek.scripts.ParaOrbs.air.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.scripts.ParaOrbs.air.AirOps;
import org.osbot.rs07.api.ui.Spells;

/**
 * @author Parametric on 3/17/2018
 * @project OSBot-Framework
 */
public class Teleout implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.myPlayer().isUnderAttack() && AirOps.ORB_AREA.contains(ctx.myPosition());
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.inventory.contains("Law rune", "Earth rune")) {
            if (ctx.magic.castSpell(Spells.NormalSpells.TELEPORT_TO_HOUSE)) {
                Execution.delayUntil(() -> this.inHouse(ctx), 1500, 2500);
            }
        }
        return false;
    }

    private boolean inHouse(ParaScript ctx) {
        return ctx.objects.closest(rs2Object ->
                rs2Object.getName().equals("Portal") && rs2Object.hasAction("Lock")) != null;
    }

}

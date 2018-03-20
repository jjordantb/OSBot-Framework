package io.paratek.scripts.ParaOrbs.air.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.fw.util.Generic;
import io.paratek.scripts.ParaOrbs.OrbOps;
import io.paratek.scripts.ParaOrbs.air.AirOps;
import org.osbot.rs07.api.model.Item;

/**
 * @author Parametric on 3/17/2018
 * @project OSBot-Framework
 */
public class PrepareInventory implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.myPlayer().getHealthPercent() == 100
                && Generic.staminaEnabled(ctx)
                && AirOps.BANK_AREA.contains(ctx.myPosition())
                && (ctx.inventory.getAmount("Unpowered orbs") < 25
                || ctx.inventory.getAmount("Law rune") < 1
                || ctx.inventory.getAmount("Earth rune") < 1
                || ctx.inventory.getAmount("Cosmic rune") < 75);
    }

    // If ever released this can be randomize in a fluid action
    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (this.invContainsRandomItems(ctx) && ctx.bank.isOpen()) {
            if (ctx.bank.depositAll()) {
                Execution.delayUntil(() -> !this.invContainsRandomItems(ctx), 750, 1500);
            }
        } else {
            if (ctx.inventory.getAmount("Law rune") < 1) {
                this.withdraw("Law rune", 1, ctx);
            } else if (ctx.inventory.getAmount("Earth rune") < 1) {
                this.withdraw("Earth rune", 1, ctx);
            } else if (ctx.inventory.getAmount("Cosmic rune") < 75) {
                this.withdraw("Cosmic rune", 75, ctx);
            } else if (ctx.inventory.getAmount("Unpowered orb") < 25) {
                ctx.log("daw");
                this.withdraw("Unpowered orb", 0, ctx);
            }
        }
        return false;
    }

    private boolean invContainsRandomItems(ParaScript ctx) {
        for (Item i : ctx.inventory.getItems()) {
            if (i != null) {
                if (!i.getName().equals("Unpowered orb") && !i.getName().equals("Cosmic rune")
                        && !i.getName().equals("Law rune") && !i.getName().equals("Earth rune")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void withdraw(final String name, int amount, ParaScript ctx) throws InterruptedException {
        if (ctx.bank.isOpen()) {
            long cur;
            if ((cur = ctx.inventory.getAmount(name)) < amount || amount == 0) {
                if (ctx.bank.withdraw(name, (int) (amount - cur))) {
                    Execution.delayUntil(() -> ctx.inventory.getAmount(name) == amount, 750, 1500);
                }
            }
        } else {
            ctx.bank.open();
        }
    }

}

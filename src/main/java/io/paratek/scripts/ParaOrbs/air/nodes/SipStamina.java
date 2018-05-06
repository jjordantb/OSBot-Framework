package io.paratek.scripts.ParaOrbs.air.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.fw.util.Generic;
import io.paratek.scripts.ParaOrbs.air.AirOps;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;

/**
 * @author Parametric on 3/17/2018
 * @project OSBot-Framework
 */
public class SipStamina implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return AirOps.BANK_AREA.contains(ctx.myPosition())
                && !Generic.staminaEnabled(ctx)
                && !ctx.inventory.contains("Unpowered orb");
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.inventory.contains(this.stamFilter())) {
            if (!ctx.bank.isOpen()) {
                final Item stam;
                if ((stam = ctx.inventory.getItem(this.stamFilter())) != null) {
                    if (stam.interact()) {
                        Execution.delayUntil(() -> Generic.staminaEnabled(ctx), 750, 1500);
                    }
                }
            } else {
                ctx.bank.close();
            }
        } else {
            if (ctx.bank.isOpen()) {
                if (ctx.bank.contains(this.stamFilter())) {
                    this.withdrawStam(ctx);
                } else {
                    ctx.log("Out of Stamina Pots");
                    ctx.logoutTab.logOut();
                    ctx.stop();
                }
            } else {
                ctx.bank.open();
            }
        }
        return true;
    }

    private void withdrawStam(ParaScript ctx) {
        if (ctx.bank.contains("Stamina potion(1)")) {
            this.withdrawOne("Stamina potion(1)", ctx);
        } else if (ctx.bank.contains("Stamina potion(2)")) {
            this.withdrawOne("Stamina potion(2)", ctx);
        } else if (ctx.bank.contains("Stamina potion(3)")) {
            this.withdrawOne("Stamina potion(3)", ctx);
        } else if (ctx.bank.contains("Stamina potion(4)")) {
            this.withdrawOne("Stamina potion(4)", ctx);
        } else {
            ctx.log("Default no Stam - not reachable code");
        }
    }

    private void withdrawOne(final String name, ParaScript ctx) {
        if (ctx.bank.withdraw(name, 1)) {
            Execution.delayUntil(() -> ctx.inventory.contains(name), 750, 1500);
        }
    }

    private Filter<Item> stamFilter() {
        return item -> item.getName().contains("Stamina");
    }

}

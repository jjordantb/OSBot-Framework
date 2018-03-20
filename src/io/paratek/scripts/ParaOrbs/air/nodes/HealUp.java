package io.paratek.scripts.ParaOrbs.air.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.scripts.ParaOrbs.air.AirOps;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Skill;

/**
 * @author Parametric on 3/17/2018
 * @project OSBot-Framework
 */
public class HealUp implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return this.healthMissing(ctx) > 0
                && AirOps.BANK_AREA.contains(ctx.myPosition());
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.inventory.contains("Tuna")) {
            if (!ctx.bank.isOpen()) {
                final Item food;
                if ((food = ctx.inventory.getItem("Tuna")) != null) {
                    int startCnt = (int) ctx.inventory.getAmount("Tuna");
                    if (food.interact("Eat")) {
                        Execution.delayUntil(() -> startCnt != ctx.inventory.getAmount("Tuna"), 750, 1500);
                    }
                }
            } else {
                ctx.bank.close();
            }
        } else {
            if (ctx.bank.isOpen()) {
                if (ctx.bank.withdraw("Tuna", 10)) {
                    Execution.delayUntil(() -> ctx.inventory.contains("Tuna"), 750, 1250);
                }
            } else {
                ctx.bank.open();
            }
        }
        return true;
    }

    private int healthMissing(ParaScript ctx) {
        int base = ctx.skills.getStatic(Skill.HITPOINTS);
        int current = ctx.skills.getDynamic(Skill.HITPOINTS);
        return base - current;
    }

}

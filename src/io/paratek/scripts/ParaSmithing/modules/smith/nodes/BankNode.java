package io.paratek.scripts.ParaSmithing.modules.smith.nodes;

import io.paratek.api.human.Reaction;
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
public class BankNode implements Node {

    private boolean hasReacted = false;

    @Override
    public boolean activate(ParaScript ctx) {
        return !ctx.inventory.contains(2351);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        if (ctx.inventory.contains(2352)) {
            final RS2Widget unnote;
            if ((unnote = this.getUnote(ctx)) != null && unnote.isVisible()) {
                if (ctx.keyboard.typeKey('1')) {
                    this.hasReacted = false;
                    Execution.delayUntil(() -> ctx.inventory.contains(2351), 750, 1500);
                }
            } else {
                if (ctx.bank.isOpen()) {
                    ctx.bank.close();
                } else {
                    final RS2Object bank;
                    if ((bank = ctx.objects.closest("Bank booth")) != null) {
                        if (!hasReacted) {
                            Execution.delay(Reaction.getReactionTime());
                            this.hasReacted = true;
                        }
                        if (bank.isVisible()) {
                            if (ctx.inventory.isItemSelected()) {
                                if (!Generic.getDestination(ctx).equals(new Position(bank.getX() - 1, bank.getY(), 0)) && ctx.inventory.getSelectedItemName().equals("Iron bar")) {
                                    if (bank.interact("Use")) {
                                        Execution.delayUntil(() -> this.getUnote(ctx) != null, 750, 1500);
                                    }
                                } else {
                                    ctx.inventory.deselectItem();
                                }
                            } else {
                                final Item noted;
                                if ((noted = ctx.inventory.getItem(2352)) != null && noted.interact()) {
                                    Execution.delayUntil(ctx.inventory::isItemSelected, 750, 1500);
                                }
                            }
                        } else {
                            if (Generic.getDestination(ctx).distance(bank) > 3 && ctx.walking.walk(bank)) {
                                Execution.delayUntil(bank::isVisible, 750, 1250);
                            }
                        }
                    }
                }
            }
        } else {
            ctx.logoutTab.logOut();
            ctx.stop();
        }
        return false;
    }

    private RS2Widget getUnote(ParaScript ctx) {
        return ctx.widgets.get(219, 0, 1);
    }

}

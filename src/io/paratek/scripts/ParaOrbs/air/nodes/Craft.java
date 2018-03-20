package io.paratek.scripts.ParaOrbs.air.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import io.paratek.scripts.ParaOrbs.OrbOps;
import io.paratek.scripts.ParaOrbs.air.AirOps;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Spells;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class Craft implements Node {

    private long lastAnim = System.currentTimeMillis();

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.inventory.contains(OrbOps.UNP_ORB) && ctx.inventory.getAmount(OrbOps.COSM) >= 3
                && AirOps.ORB_AREA.contains(ctx.myPosition());
    }

    @Override
    public boolean execute(ParaScript ctx) {
        if (!ctx.myPlayer().isAnimating() && System.currentTimeMillis() - this.lastAnim > 1500) {
            final RS2Widget widget;
            if ((widget = ctx.widgets.get(270, 14)) != null && widget.isVisible()) {
                if (widget.interact()) {
                    Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 750, 1500);
                }
            } else {
                if (ctx.magic.isSpellSelected()) {
                    final RS2Object oby;
                    if ((oby = ctx.objects.closest(AirOps.OBY)) != null) {
                        if (oby.isVisible()) {
                            if (oby.interact()) {
                                Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 750, 1500);
                            }
                        } else {
                            ctx.camera.toEntity(oby);
                        }
                    }
                } else {
                    final RS2Object oby;
                    if ((oby = ctx.objects.closest(AirOps.OBY)) != null) {
                        if (ctx.magic.castSpellOnEntity(Spells.NormalSpells.CHARGE_AIR_ORB, oby)) {
                            Execution.delayUntil(() -> ctx.myPlayer().isAnimating(), 750, 1500);
                        }
                    }
                }
            }
        } else {
            if (ctx.myPlayer().isAnimating()) {
                this.lastAnim = System.currentTimeMillis();
            }
        }
        return false;
    }

}

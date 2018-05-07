package io.paratek.tut.modules.nodes;

import io.paratek.api.util.Chatbox;
import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import org.osbot.rs07.api.HintArrow;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class HandleNpcNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.hintArrow.isActive() && ctx.hintArrow.getType() == HintArrow.HintArrowType.NPC
                && !new Chatbox(ctx).isOpen();
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        System.out.println("NPC NODE");
        if (!ctx.myPlayer().isAnimating()) {
            final Position start = ctx.hintArrow.getPosition();
            final NPC actual;
            if ((actual = (NPC) ctx.hintArrow.getEntity()) != null) {
                if (actual.isVisible()) {
                    if (!ctx.myPlayer().isMoving()) {
                        String action = actual.getActions().length > 0 ? actual.getActions()[0] : "";
                        if (actual.interact(action)) {
                            Execution.delayUntil(() -> !ctx.hintArrow.isActive() || !ctx.hintArrow.getPosition().equals(start), 1500, 2500);
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
        return false;
    }

}

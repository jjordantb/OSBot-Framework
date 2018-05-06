package io.paratek.tut.modules.nodes;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import org.osbot.rs07.api.HintArrow;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;

import java.util.List;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class HandleObjectNode implements Node {

    @Override
    public boolean activate(ParaScript ctx) {
        return ctx.hintArrow.isActive() && ctx.hintArrow.getType() == HintArrow.HintArrowType.POSITION;
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        System.out.println("OBJECT NODE");
        if (!ctx.myPlayer().isAnimating()) {
            final Position arrow = ctx.hintArrow.getPosition();
            final List<RS2Object> objects;
            if ((objects = ctx.objects.get(arrow.getX(), arrow.getY())) != null && objects.size() > 0) {
                for (RS2Object o : objects) {
                    if (o.getUID() > 0) {
                        final String name = o.getName();

                        final RS2Object actual;
                        if ((actual = ctx.objects.closest(name)) != null) {
                            if (actual.isVisible()) {
                                if (!ctx.myPlayer().isMoving()) {
                                    if (actual.interact()) {
                                        Execution.delayUntil(() -> !ctx.hintArrow.isActive() || !ctx.hintArrow.getPosition().equals(arrow), 1500, 2500);
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
                }
            }
        }
        return false;
    }

}

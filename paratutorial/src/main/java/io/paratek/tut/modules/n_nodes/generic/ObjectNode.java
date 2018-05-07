package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.api.interaction.viewport.EntityInteraction;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.util.Condition;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;

import java.util.List;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class ObjectNode extends TutNode {

    private final String name, action;
    private final Position nearestTo;
    private final Condition validate;
    private final int delay;

    public ObjectNode(String name, String action, Position nearestTo, Condition validate, int delay, TutorialState... states) {
        super(states);
        this.name = name;
        this.action = action;
        this.nearestTo = nearestTo;
        this.validate = validate;
        this.delay = delay;
    }

    public ObjectNode(String name, String action, Position nearestTo, Condition validate, TutorialState... states) {
        super(states);
        this.name = name;
        this.action = action;
        this.nearestTo = nearestTo;
        this.validate = validate;
        this.delay = Random.nextInt(1500, 2500);
    }

    public ObjectNode(String name, String action, Condition validate, TutorialState... states) {
        this(name, action, null, validate, Random.nextInt(1500, 2500), states);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        super.clearSelection(ctx);

        if (this.nearestTo != null) {
            final RS2Object object;
            if ((object = this.closestTo(this.name, this.nearestTo, ctx)) != null) {
                new EntityInteraction(this.action, this.delay, object, this.validate).execute(ctx);
            }
        } else {
            final RS2Object object;
            if ((object = ctx.objects.closest(this.name)) != null) {
                new EntityInteraction(this.action, this.delay, object, this.validate).execute(ctx);
            }
        }
        return false;
    }

    private RS2Object closestTo(final String name, final Position position, final ParaScript ctx) {
        final List<RS2Object> objects = ctx.objects.getAll();
        RS2Object cur = null;
        int dist = Integer.MAX_VALUE;
        for (RS2Object object : objects) {
            if (object.getName().equals(name)) {
                if (cur == null) {
                    cur = object;
                    dist = cur.getPosition().distance(position);
                } else {
                    int tmpDist = object.getPosition().distance(position);
                    if (tmpDist < dist) {
                        cur = object;
                        dist = tmpDist;
                    }
                }
            }
        }
        return cur;
    }

}

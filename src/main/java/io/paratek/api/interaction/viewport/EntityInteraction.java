package io.paratek.api.interaction.viewport;

import io.paratek.api.util.Execution;
import io.paratek.fw.ParaScript;
import io.paratek.fw.util.Condition;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Player;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class EntityInteraction {

    private int attempts = 0;

    private final String action;
    private final int sleepTime;

    private final Entity entity;
    private final Condition validate;

    public EntityInteraction(String action, int sleepTime, Entity entity, Condition validate) {
        this.action = action;
        this.sleepTime = sleepTime;
        this.entity = entity;
        this.validate = validate;
    }

    public boolean execute(final ParaScript ctx) {
        if (entity != null) {
            if (this.validate.validate()) {
                return true;
            }
            if (attempts >= 5) {
                ctx.camera.toEntity(entity);
                return false;
            }
            if (entity.isVisible() && entity.getPosition().distance(ctx.myPlayer()) <= 20) {
                if (this.interaction(this.entity, this.action)) {
                    ctx.log("[EntityInteraction] Interaction: " + this.action + " -> " + this.entity.toString());
                    Execution.delayUntil(this.validate::validate, this.sleepTime);
                    if (this.validate.validate()) {
                        return true;
                    } else {
                        if (ctx.myPlayer().isMoving()) {
                            while (ctx.myPlayer().isMoving()) {
                                Execution.delayUntil(this.validate::validate, 250);
                            }
                        }
                    }
                } else {
                    ctx.log("[EntityInteraction] Failure Detected (Attempts: " + this.attempts + ")");
                    attempts++;
                    return execute(ctx);
                }
            } else {
                ctx.walking.walk(entity);
            }
        }
        return false;
    }

    private boolean interaction(final Entity entity, final String action) {
        return entity.interact(action);
    }

}

package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.api.interaction.viewport.EntityInteraction;
import io.paratek.api.util.Chatbox;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.tut.modules.n_nodes.nodeutil.CtxCondition;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.model.NPC;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class NpcNode extends TutNode {

    private final String npcName, action;
    private final CtxCondition[] ctxConditions;

    public NpcNode(String npcName, String action, CtxCondition[] ctxConditions, TutorialState... states) {
        super(states);
        this.npcName = npcName;
        this.action = action;
        this.ctxConditions = ctxConditions;
    }

    @Override
    public boolean activate(ParaScript ctx) {
        return (super.activate(ctx) && !new Chatbox(ctx).isOpen()) && this.validateCtx(ctx, this.ctxConditions);
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        super.clearSelection(ctx);

        final NPC npc;
        if ((npc = ctx.npcs.closest(this.npcName)) != null) {
            new EntityInteraction(action, Random.nextInt(1500, 2500), npc, () -> new Chatbox(ctx).isOpen())
                    .execute(ctx);
        }
        return false;
    }

    public boolean validateCtx(final ParaScript ctx, final CtxCondition[] ctxConditions) {
        boolean valid = true;
        for (CtxCondition condition : ctxConditions) {
            if (!condition.activate(ctx)) {
                valid = false;
            }
        }
        return valid;
    }

}

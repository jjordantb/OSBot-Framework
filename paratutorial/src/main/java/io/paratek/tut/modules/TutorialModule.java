package io.paratek.tut.modules;

import io.paratek.api.util.Execution;
import io.paratek.api.util.Random;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.NodeModule;
import io.paratek.tut.modules.n_nodes.HandleDialogNode;
import io.paratek.tut.modules.n_nodes.RandomizeAppearanceNode;
import io.paratek.tut.modules.n_nodes.generic.*;
import io.paratek.tut.modules.n_nodes.nodeutil.CtxCondition;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Tab;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class TutorialModule extends NodeModule {

    private final CtxCondition hasShrimp = (ctx) -> ctx.inventory.contains("Raw shrimps") && ctx.objects.closest("Fire") == null;
    private final CtxCondition doesNotHaveShrimp = (ctx) -> !ctx.inventory.contains("Raw shrimps");
    private final CtxCondition notAnimating = (ctx) -> !ctx.myPlayer().isAnimating();
    private final CtxCondition fireExists = (ctx) -> ctx.objects.closest("Fire") != null;


    public TutorialModule(ParaScript ctx) {
        super(ctx);
        super.shouldStop = () -> false;

        super.register(new RandomizeAppearanceNode());
        super.register(new HandleDialogNode());
        super.register(new NpcNode("RuneScape Guide", "Talk-to", new CtxCondition[]{}, TutorialState.TALK_TO_GUIDE, TutorialState.TALK_TO_GUIDE_2));
        super.register(new OpenTabNode(Tab.SETTINGS, TutorialState.OPEN_OPTIONS, TutorialState.OPEN_OPTIONS_2));
        super.register(new ObjectNode("Door", "Open", new Position(3097, 3107, 0), () -> !TutorialState.STARTING_DOOR.isState(ctx), TutorialState.STARTING_DOOR));
        super.register(new NpcNode("Survival Expert", "Talk-to", new CtxCondition[]{}, TutorialState.TALK_SURVIVAL_EXPERT, TutorialState.TALK_SURVIVAL_EXPERT_2));
        super.register(new OpenTabNode(Tab.INVENTORY, TutorialState.OPEN_INVENTORY));
        super.register(new ObjectNode("Tree", "Chop down", () -> ctx.myPlayer().isAnimating(), TutorialState.CUT_DOWN_TREE));
        super.register(new ItemOnItemNode("Tinderbox", "Logs",
                () -> this.fireOk(ctx),
                ctx1 -> this.findTileAndWalk(ctx),
                new CtxCondition[]{this.hasShrimp}, TutorialState.MAKE_FIRE));
        super.register(new OpenTabNode(Tab.SKILLS, TutorialState.OPEN_SKILLS));
        super.register(new NpcNode("Fishing spot", "Net", new CtxCondition[]{this.notAnimating, this.doesNotHaveShrimp}, TutorialState.FISH_SHRIMP, TutorialState.BURN_CATCH_COOK));
        super.register(new ItemOnObjectNode("Raw shrimps", "Fire",
                () -> ctx.myPlayer().isAnimating(),
                new CtxCondition[]{this.fireExists, this.notAnimating},
                TutorialState.COOK_SHRIMP, TutorialState.BURN_CATCH_COOK));
        super.register(new ObjectNode("Gate", "Open", new Position(3090, 3092, 0), () -> !TutorialState.WALK_OPEN_GATE.isState(ctx), TutorialState.WALK_OPEN_GATE));
        super.register(new ObjectNode("Door", "Open", new Position(3079, 3084, 0), () -> !TutorialState.OPEN_COOK_DOOR.isState(ctx), TutorialState.OPEN_COOK_DOOR));
        super.register(new NpcNode("Master Chef", "Talk-to", new CtxCondition[]{}, TutorialState.TALK_TO_COOK));
        super.register(new ItemOnItemNode("Pot of flour", "Bucket of water",
                () -> true,
                ctx1 -> {},
                new CtxCondition[]{}, TutorialState.FLOUR_WATER));
        super.register(new ItemOnObjectNode("Bread dough", "Range",
                () -> ctx.myPlayer().isAnimating(),
                new CtxCondition[]{this.notAnimating},
                TutorialState.COOK_BREAD));
        super.register(new OpenTabNode(Tab.MUSIC, TutorialState.OPEN_MUSIC));
        super.register(new ObjectNode("Door", "Open", new Position(3073, 3090, 0), () -> !TutorialState.LEAVE_COOK.isState(ctx), TutorialState.LEAVE_COOK));
        super.register(new OpenTabNode(Tab.EMOTES, TutorialState.OPEN_EMOTE));
        super.register(new WidgetNode("", 216, 1, Random.nextInt(1, 20), new CtxCondition[]{}, TutorialState.DO_EMOTE));
        super.register(new GenericRunNode(c -> {
            if (c.settings.setRunning(true)) {
                Execution.delayUntil(() -> ctx.settings.isRunning(), 750, 1500);
            }
        }, TutorialState.ENABLE_RUN));
        super.register(new ObjectNode("Door", "Open", new Position(3086, 3126, 0), () -> !TutorialState.OPEN_QUEST_DOOR.isState(ctx), TutorialState.OPEN_QUEST_DOOR));
        super.register(new NpcNode("Quest Guide", "Talk-to", new CtxCondition[]{}, TutorialState.TALK_QUEST_GUIDE, TutorialState.TALK_QUEST_GUIDE_2));
        super.register(new OpenTabNode(Tab.QUEST, TutorialState.OPEN_QUEST));
        super.register(new ObjectNode("Ladder", "Climb-down", new Position(3088, 3120, 0), () -> !TutorialState.GO_DOWN_LADDER.isState(ctx), TutorialState.GO_DOWN_LADDER));
        super.register(new NpcNode("Mining Instructor", "Talk-to", new CtxCondition[]{}, TutorialState.TALK_MINER, TutorialState.TALK_MINER_2, TutorialState.TALK_MINER_3));
        super.register(new ObjectNode("Rocks", "Prospect", new Position(3078, 9504, -1), () -> !TutorialState.PROSPECT_TIN.isState(ctx), Random.nextInt(3000, 5000), TutorialState.PROSPECT_TIN));
        super.register(new ObjectNode("Rocks", "Prospect", new Position(3082, 9501, -1), () -> !TutorialState.PROSPECT_COPPER.isState(ctx), Random.nextInt(3000, 5000), TutorialState.PROSPECT_COPPER));
        super.register(new ObjectNode("Rocks", "Mine", new Position(3078, 9504, -1), () -> !TutorialState.MINE_TIN.isState(ctx), Random.nextInt(3000, 5000), TutorialState.MINE_TIN));
        super.register(new ObjectNode("Rocks", "Mine", new Position(3082, 9501, -1), () -> !TutorialState.MINE_COPPER.isState(ctx), Random.nextInt(3000, 5000), TutorialState.MINE_COPPER));
        super.register(new ItemOnObjectNode("Tin ore", "Furnace", () -> ctx.myPlayer().isAnimating(), new CtxCondition[]{this.notAnimating}, TutorialState.MAKE_BRONZE_BAR));
        super.register(new ObjectNode("Anvil", "Smith", new Position(3082, 9499, -1), () -> !TutorialState.USE_BAR_ANVIL.isState(ctx), Random.nextInt(3000, 5000), TutorialState.USE_BAR_ANVIL));
        super.register(new WidgetNode("Smith 1", 312, 2, 2, new CtxCondition[]{}, TutorialState.SMITH_DAGGER));
        super.register(new ObjectNode("Gate", "Open", new Position(3094, 9503, -1), () -> !TutorialState.OPEN_MINE_GATE.isState(ctx), TutorialState.OPEN_MINE_GATE));
        super.register(new NpcNode("Combat Instructor", "Talk-to", new CtxCondition[]{}, TutorialState.TALK_CB_INST));
        super.register(new OpenTabNode(Tab.EQUIPMENT, TutorialState.OPEN_EQUIPMENT));
        super.register(new WidgetNode("", 387, 17, 0, new CtxCondition[]{}, TutorialState.OPEN_WORN));
        super.register(new WidgetNode("", 84, 4, new CtxCondition[]{}, TutorialState.CLOSE_EQUIPMENT_STATS));
        // close equipment stats -- equip items;
//        super.register();
//        super.register();
//        super.register();



    }

    private boolean fireOk(final ParaScript ctx) {
        final Position p = ctx.myPosition();
        for (RS2Object o : ctx.objects.get(p.getX(), p.getY())) {
            if (o.getName() != null && o.getName().equals("Fire")) {
                return false;
            }
        }
        return true;
    }

    private void findTileAndWalk(final ParaScript ctx) {

    }

}

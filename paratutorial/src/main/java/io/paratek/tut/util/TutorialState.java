package io.paratek.tut.util;

import io.paratek.fw.ParaScript;
import org.osbot.PA;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public enum TutorialState {

    TALK_TO_GUIDE(0),
    OPEN_OPTIONS(3),
    TALK_TO_GUIDE_2(7),
    STARTING_DOOR(10),
    TALK_SURVIVAL_EXPERT(20),
    OPEN_INVENTORY(30),
    CUT_DOWN_TREE(40),
    MAKE_FIRE(50),
    OPEN_SKILLS(60),
    TALK_SURVIVAL_EXPERT_2(70),
    FISH_SHRIMP(80),
    COOK_SHRIMP(90),
    BURN_CATCH_COOK(110),
    WALK_OPEN_GATE(120),
    OPEN_COOK_DOOR(130),
    TALK_TO_COOK(140),
    FLOUR_WATER(150),
    COOK_BREAD(160),
    OPEN_MUSIC(170),
    LEAVE_COOK(180),
    OPEN_EMOTE(183),
    DO_EMOTE(187),
    OPEN_OPTIONS_2(190),
    ENABLE_RUN(200),
    OPEN_QUEST_DOOR(210),
    TALK_QUEST_GUIDE(220),
    OPEN_QUEST(230),
    TALK_QUEST_GUIDE_2(240),
    GO_DOWN_LADDER(250),
    TALK_MINER(260),
    PROSPECT_TIN(270),
    PROSPECT_COPPER(280),
    TALK_MINER_2(290),
    MINE_TIN(300),
    MINE_COPPER(310),
    MAKE_BRONZE_BAR(320),
    TALK_MINER_3(330),
    USE_BAR_ANVIL(340),
    SMITH_DAGGER(350),
    OPEN_MINE_GATE(360),
    TALK_CB_INST(370),
    OPEN_EQUIPMENT(390),
    OPEN_WORN(400),
    CLOSE_EQUIPMENT_STATS(405);

    private final int state;
    public static final int PARENT_STATE = 281;

    TutorialState(final int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public boolean isState(ParaScript ctx) {
        return ctx.configs.get(PARENT_STATE) == this.getState();
    }

}
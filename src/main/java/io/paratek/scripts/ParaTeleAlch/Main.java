package io.paratek.scripts.ParaTeleAlch;

import io.paratek.api.listeners.skill.SkillEvent;
import io.paratek.api.listeners.skill.SkillListener;
import io.paratek.api.util.Execution;
import io.paratek.api.util.GaussianRandom;
import io.paratek.fw.ParaScript;
import io.paratek.fw.statistic.RuntimeStatistic;
import io.paratek.fw.statistic.tools.Format;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Spells;
import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.input.mouse.InventorySlotDestination;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
@ScriptManifest(name = "Para Teleport Alching", author = "Parametric", version = 1.0, info = "Teleport Alchs in Camelot", logo = "")
public class Main extends ParaScript implements SkillListener, Painter {

    @Override
    public void onStart() {
        super.onStart();
        getEventDispatcher().submitListener(this);
        getStatistics().register("XP", RuntimeStatistic.init(0));
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (inventory.contains("Nature rune", "Law rune", "Yew longbow")) {
            final int anim = myPlayer().getAnimation();
            if (anim == -1) {
                if (magic.isSpellSelected()) {
                    if (this.invOpen()) {
                        if (magic.getSelectedSpellName().equals("High Level Alchemy")) {
                            if (this.mouseOverSlot(15)) {
                                Execution.delay(GaussianRandom.gRandom(500, 75));
                                if (mouse.click(false)) {
                                    Execution.delayUntil(() -> myPlayer().getAnimation() == 713, 750, 1250);
                                }
                            } else {
                                if (this.hoverSlot(15)) {
                                    Execution.delayUntil(() -> menu.getTooltip().contains("Yew"), 750, 1250);
                                }
                            }
                        } else {
                            magic.deselectSpell();
                        }
                    } else {
                        magic.deselectSpell();
                    }
                } else {
                    if (magic.castSpell(Spells.NormalSpells.HIGH_LEVEL_ALCHEMY)) {
                        Execution.delayUntil(() -> magic.isSpellSelected(), 750, 1250);
                    }
                }
            } else if (anim == 713) {
                if (magic.castSpell(Spells.NormalSpells.CAMELOT_TELEPORT) && this.hoverAlch()) {
                    Execution.delayUntil(() -> myPlayer().getAnimation() == 714, 750, 1250);
                }
            }
        } else {
            stop();
        }
        return GaussianRandom.gRandom(200, 50);
    }

    private boolean mouseOverSlot(final int slot) {
        return new InventorySlotDestination(bot, slot).getBoundingBox().contains(mouse.getPosition());
    }

    private boolean invOpen() {
        final RS2Widget widget;
        return (widget = widgets.get(149, 0)) != null && widget.isVisible();
    }

    private boolean hoverSlot(final int slot) {
        return mouse.move(new InventorySlotDestination(bot, slot));
    }

    private boolean hoverAlch() {
        final RS2Widget widget;
        return (widget = widgets.get(218, 35)) != null && widget.interact();
    }

    @Override
    public void onPaint(Graphics2D g) {
        g = (Graphics2D) g.create();
        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.PLAIN, 12));
        g.drawString("Time: " + getTimer().getFormattedTime(), 15, 60);

        g.drawString(Format.on(getStatistics().get("XP"), statistic ->
                "Xp: " + statistic.value() + " [" + statistic.getPer(getTimer().getTimeElapsed())) + "/H]", 15, 72);
    }

    @Override
    public void onExperienceGain(SkillEvent skillEvent) {
        if (skillEvent.getSkill().equals(Skill.MAGIC)) {
            getStatistics().get("XP").increment(skillEvent.getChange());
        }
    }

    @Override
    public void onLevelGain(SkillEvent skillEvent) {

    }
}

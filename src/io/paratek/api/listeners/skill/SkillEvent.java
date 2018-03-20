package io.paratek.api.listeners.skill;

import org.osbot.rs07.api.ui.Skill;

/**
 * @author Parametric on 2/6/2018
 * @project TriBot
 */
public class SkillEvent {

    private final Skill skill;
    private final Type type;
    private final int change, current, previous;

    public SkillEvent(Skill skill, Type type, int change, int current, int previous) {
        this.skill = skill;
        this.change = change;
        this.current = current;
        this.previous = previous;
        this.type = type;
    }

    public Skill getSkill() {
        return skill;
    }

    public Type getType() {
        return type;
    }

    public int getChange() {
        return change;
    }

    public int getCurrent() {
        return current;
    }

    public int getPrevious() {
        return previous;
    }

    public enum Type {
        LEVEL_GAINED,
        EXPERIENCE_GAINED;
    }

}

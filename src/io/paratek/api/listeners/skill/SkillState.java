package io.paratek.api.listeners.skill;

import org.osbot.rs07.api.Client;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import java.util.HashMap;

/**
 * @author Parametric on 2/6/2018
 * @project TriBot
 */
public class SkillState {

    private final HashMap<Skill, Integer> experienceMap;
    private final HashMap<Skill, Integer> levelMap;

    private boolean inited = false;
    private final Script context;

    public SkillState(final Script context) {
        this.context = context;
        this.experienceMap = new HashMap<>();
        this.levelMap = new HashMap<>();
        this.init();
    }

    public SkillEvent experienceGained() {
        if (!this.inited) {
            this.init();
        }
        if (this.inited) {
            for (Skill skill : Skill.values()) {
                final int change, current, previous;
                if ((change = (current = this.context.skills.getExperience(skill)) - (previous = this.experienceMap.get(skill))) > 0) {
                    this.experienceMap.put(skill, current);
                    return new SkillEvent(skill, SkillEvent.Type.EXPERIENCE_GAINED, change, current, previous);
                }
            }
        }
        return null;
    }

    public SkillEvent levelGained() {
        if (!this.inited) {
            this.init();
        }
        if (this.inited) {
            for (Skill skill : Skill.values()) {
                final int change, current, previous;
                if ((change = (current = this.context.skills.getStatic(skill)) - (previous = this.levelMap.get(skill))) > 0) {
                    this.levelMap.put(skill, current);
                    return new SkillEvent(skill, SkillEvent.Type.LEVEL_GAINED, change, current, previous);
                }
            }
        }
        return null;
    }

    public void init() {
        try {
            if (this.context.client.getLoginState().equals(Client.LoginState.LOGGED_IN)) {
                for (Skill skill : Skill.values()) {
                    this.experienceMap.put(skill, this.context.skills.getExperience(skill));
                    this.levelMap.put(skill, this.context.skills.getStatic(skill));
                }
                this.inited = true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            this.inited = false;
        }
    }

    public boolean isInited() {
        return inited;
    }
}

package io.paratek.api.listeners.skill;

import io.paratek.api.listeners.Listener;

/**
 * @author Parametric on 2/6/2018
 * @project TriBot
 */
public interface SkillListener extends Listener {

    void onExperienceGain(final SkillEvent skillEvent);

    void onLevelGain(final SkillEvent skillEvent);

}

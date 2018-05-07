package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.fw.ParaScript;
import io.paratek.tut.util.TutorialState;
import org.osbot.rs07.api.ui.Tab;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class OpenTabNode extends TutNode {

    private final Tab tab;

    public OpenTabNode(Tab tab, TutorialState... tutorialStates) {
        super(tutorialStates);
        this.tab = tab;
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        ctx.tabs.open(this.tab);
        return false;
    }

}

package io.paratek.tut.modules;

import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.NodeModule;
import io.paratek.tut.modules.n_nodes.HandleDialogNode;
import io.paratek.tut.modules.n_nodes.RandomizeAppearanceNode;
import io.paratek.tut.modules.nodes.*;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class TutorialModule extends NodeModule {

    public TutorialModule(ParaScript ctx) {
        super(ctx);
        super.shouldStop = () -> false;

        super.register(new MakeFireNode());
        super.register(new CookNode());
        super.register(new MakeBreadNode());
        super.register(new DoEmoteNode());
        super.register(new EnableRunNode());
        super.register(new OpenTabNode());

        super.register(new HandleDialogNode());
        super.register(new HandleObjectNode());
        super.register(new HandleNpcNode());

        super.register(new RandomizeAppearanceNode());

    }

}

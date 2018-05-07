package io.paratek.tut.modules.n_nodes.generic;

import io.paratek.fw.ParaScript;
import io.paratek.tut.modules.n_nodes.nodeutil.CtxRunnable;
import io.paratek.tut.util.TutorialState;

/**
 * @author Parametric on 5/6/2018
 * @project OSBot-Framework
 */
public class GenericRunNode extends TutNode {

    private final CtxRunnable runnable;

    public GenericRunNode(CtxRunnable runnable, TutorialState... states) {
        super(states);
        this.runnable = runnable;
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        this.runnable.run(ctx);
        return false;
    }

}

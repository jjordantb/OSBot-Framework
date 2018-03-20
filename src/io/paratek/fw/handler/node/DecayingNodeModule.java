package io.paratek.fw.handler.node;

import io.paratek.fw.ParaScript;

import java.util.Iterator;

/**
 * @author Parametric on 3/19/2018
 * @project OSBot-Framework
 */
public class DecayingNodeModule extends NodeModule {

    public DecayingNodeModule(ParaScript ctx) {
        super(ctx);
    }

    @Override
    public boolean run() throws InterruptedException {
        final Iterator<Node> nodeIterator = super.nodes.iterator();
        while (nodeIterator.hasNext()) {
            final Node current = nodeIterator.next();
            if (current.activate(super.ctx) && current.execute(super.ctx)) {
                nodeIterator.remove();
            }
        }
        return shouldStop.validate() || nodes.size() <= 0;
    }

}

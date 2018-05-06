package io.paratek.scripts.ParaFletching.modules.stringing.nodes;

import io.paratek.fw.ParaScript;
import io.paratek.scripts.ParaFletching.modules.stringing.nodes.generic.AbstractStringingNode;

/**
 * @author Parametric on 3/22/2018
 * @project OSBot-Framework
 */
public class StringingNode extends AbstractStringingNode {

    public StringingNode(String item1, String item2, String result) {
        super(item1, item2, result);
    }

    @Override
    public boolean activate(ParaScript ctx) {
        return !ctx.inventory.contains(item1) || !ctx.inventory.contains(item2) || ctx.bank.isOpen();
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {

        return false;
    }
}

package io.paratek.scripts.ParaFletching.modules.stringing.nodes.generic;

import io.paratek.fw.handler.node.Node;

/**
 * @author Parametric on 3/22/2018
 * @project OSBot-Framework
 */
public abstract class AbstractStringingNode implements Node {

    protected final String item1, item2, result;

    protected AbstractStringingNode(String item1, String item2, String result) {
        this.item1 = item1;
        this.item2 = item2;
        this.result = result;
    }
}

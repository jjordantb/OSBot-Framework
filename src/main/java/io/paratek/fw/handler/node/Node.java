package io.paratek.fw.handler.node;

import io.paratek.fw.ParaScript;

/**
 * @author Parametric on 3/15/2018
 * @project OSBot-Framework
 */
public interface Node {

    boolean activate(ParaScript ctx);

    boolean execute(ParaScript ctx) throws InterruptedException;

}

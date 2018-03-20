package io.paratek.fw.handler.node;

import io.paratek.fw.ParaScript;

import java.util.LinkedList;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class NodeModuleHandler {

    private NodeModule current;
    private final LinkedList<NodeModule> nodeModules = new LinkedList<>();

    private final ParaScript ctx;

    public NodeModuleHandler(ParaScript ctx){
        this.ctx = ctx;
    }

    public void register(final NodeModule module) {
        this.nodeModules.add(module);
    }

    public void remove(final NodeModule module) {
        this.nodeModules.removeIf(mod -> mod.equals(module));
    }

    public void run() throws InterruptedException {
        if (current == null) {
            if (nodeModules.size() > 0) {
                this.current = this.nodeModules.removeFirst();
                this.ctx.log("Setting New Module");
            } else {
                this.ctx.log("All Modules Complete, Stopping.");
            }
        } else {
            if (this.current.run()) {
                this.current = null;
            }
        }
    }

}

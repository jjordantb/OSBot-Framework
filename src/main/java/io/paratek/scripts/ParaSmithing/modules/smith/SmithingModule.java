package io.paratek.scripts.ParaSmithing.modules.smith;

import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.NodeModule;
import io.paratek.scripts.ParaSmithing.modules.smith.nodes.BankNode;
import io.paratek.scripts.ParaSmithing.modules.smith.nodes.SmithNode;

/**
 * @author Parametric on 3/18/2018
 * @project OSBot-Framework
 */
public class SmithingModule extends NodeModule {

    public SmithingModule(ParaScript ctx) {
        super(ctx);
        super.setStopAt(() -> ctx.inventory.getAmount(2352) <= 0 && ctx.inventory.getAmount(2351) <= 0);
        super.register(new BankNode());
        super.register(new SmithNode());
    }

}

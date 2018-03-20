package io.paratek.scripts.ParaOrbs.air;

import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.NodeModule;
import io.paratek.scripts.ParaOrbs.air.nodes.*;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
public class AirModule extends NodeModule {

    public AirModule(ParaScript ctx) {
        super(ctx);

        super.setStopAt(() -> false);
        super.register(new Teleout());
        super.register(new HealUp());
        super.register(new SipStamina());
        super.register(new PrepareInventory());
        super.register(new Craft());
        super.register(new ToBank());
        super.register(new ToOby());
    }

}

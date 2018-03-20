package io.paratek.scripts.ParaOrbs;

import io.paratek.fw.ParaScript;
import io.paratek.fw.statistic.RuntimeStatistic;
import io.paratek.scripts.ParaOrbs.air.AirModule;
import org.osbot.rs07.script.ScriptManifest;

/**
 * @author Parametric on 3/16/2018
 * @project OSBot-Framework
 */
@ScriptManifest(name = "Para Orbs", author = "Parametric", version = 1.0, info = "Creates Air Orbs", logo = "")
public class Main extends ParaScript {

    @Override
    public void onStart() {
        super.onStart();
        getModuleHandler().register(new AirModule(this));
        getStatistics().register("XP", RuntimeStatistic.init(0));
    }

    @Override
    public boolean canAfk() {
        return false;
    }

}

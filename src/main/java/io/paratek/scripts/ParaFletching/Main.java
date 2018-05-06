package io.paratek.scripts.ParaFletching;

import io.paratek.fw.ParaScript;
import io.paratek.scripts.ParaFletching.modules.stringing.StringingModule;
import org.osbot.rs07.script.ScriptManifest;

/**
 * @author Parametric on 3/22/2018
 * @project OSBot-Framework
 */
@ScriptManifest(name = "Para Fletching", author = "Parametric", version = 1.0, info = "AIO Fletcher built with human biometrics", logo = "")
public class Main extends ParaScript {

    @Override
    public void onStart() {
        super.onStart();
        super.getModuleHandler().register(new StringingModule(this));
    }

}

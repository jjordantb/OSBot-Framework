package io.paratek.scripts.ParaSmithing;

import io.paratek.fw.ParaScript;
import io.paratek.fw.util.Generic;
import io.paratek.scripts.ParaSmithing.modules.smith.SmithingModule;
import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

/**
 * @author Parametric on 3/18/2018
 * @project OSBot-Framework
 */
@ScriptManifest(name = "Para Smithing", author = "Parametric", version = 1.0, info = "Hammer's Bars", logo = "")
public class Main extends ParaScript implements Painter {

    @Override
    public void onStart() {
        super.onStart();
        super.getModuleHandler().register(new SmithingModule(this));
    }

    @Override
    public void onPaint(Graphics2D g) {
        g = (Graphics2D) g.create();
        g.setStroke(new BasicStroke(0.5f));
        Point p = mouse.getPosition();
        g.drawLine(p.x - 5, p.y - 5, p.x + 5, p.y + 5);
        g.drawLine(p.x - 5, p.y + 5, p.x + 5, p.y - 5);
    }
}

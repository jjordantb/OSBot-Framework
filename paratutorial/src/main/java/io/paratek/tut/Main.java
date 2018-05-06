package io.paratek.tut;

import io.paratek.api.util.Chatbox;
import io.paratek.fw.ParaScript;
import io.paratek.tut.modules.TutorialModule;
import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
@ScriptManifest(name = "Para_Tutorial", author = "Parametric", info = "Completes Tutorial Island", version = 1.0, logo = "")
public class Main extends ParaScript implements Painter {

    public Main() {
        super.getModuleHandler().register(new TutorialModule(this));
    }

    @Override
    public void onPaint(Graphics2D g) {
        g = (Graphics2D) g.create();
        Point p = mouse.getPosition();
        g.setColor(Color.ORANGE);
        g.fillOval(p.x - 4, p.y - 4, 8, 8);

        g.drawString("Debug: " + new Chatbox(this).isOpen(), 100, 100);
    }
}

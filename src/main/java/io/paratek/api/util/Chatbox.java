package io.paratek.api.util;

import io.paratek.fw.ParaScript;
import org.osbot.rs07.api.ui.RS2Widget;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class Chatbox {

//    private static final int O_PARENT_ID = 231;
//    private static final int O_CONT_ID = 2;
//    private static final int O_TITLE_ID = 1;
//    private static final int O_TXT_ID = 3;
//
//    private static final int I_PARENT_ID = 217;
//    private static final int I_CONT_ID = 2;
//    private static final int I_TITLE_ID = 1;
//    private static final int I_TXT_ID = 3;
//
//    private static final int U_PARENT_ID = 229;
//    private static final int U_CONT_ID = 1;
//
//    private static final int P_PARENT_ID = 193;
//    private static final int P_CONT_ID = 3;

    private static final int STICKY_PARENT_ID = 162;
    private static final int STICKY_CONT_ID = 37; // must be clicked

    private static final int OPTION_PARENT_ID = 219;
    private static final int OPTION_CHILD_ID = 0;

    private final ParaScript ctx;

    // "Yes", "No, I'm not planning to do that."

    public Chatbox(ParaScript ctx) {
        this.ctx = ctx;
    }

    public boolean canContinue() {
        final RS2Widget w, w2;
        return ((w = this.ctx.widgets.getWidgetContainingText("Click here to continue")) != null && w.isVisible()
                && w.getRootId() != STICKY_PARENT_ID)
                || ((w2 = this.ctx.widgets.getWidgetContainingText("Please wait...")) != null && w2.isVisible());
    }

    public boolean doContinue() {
        this.ctx.keyboard.pressKey(KeyEvent.VK_SPACE);
        return true;
    }

    public boolean stickyCanContinue() {
        final RS2Widget w;
        return (w = this.ctx.widgets.get(STICKY_PARENT_ID, STICKY_CONT_ID)) != null && w.isVisible();
    }

    public boolean stickyDoContinue() {
        final RS2Widget w;
        if ((w = this.ctx.widgets.get(STICKY_PARENT_ID, STICKY_CONT_ID)) != null && w.isVisible()) {
            return w.interact() && Execution.delayUntil(() -> !w.isVisible(), 750, 1500);
        }
        return false;
    }

    public boolean optionsOpen() {
        final RS2Widget w;
        return  (w = this.ctx.widgets.get(OPTION_PARENT_ID, OPTION_CHILD_ID)) != null
                && w.isVisible() && w.getChildWidgets().length > 0;
    }

    public boolean processOptions(final String... options) {
        final RS2Widget widget;
        if ((widget = this.ctx.widgets.get(OPTION_PARENT_ID, OPTION_CHILD_ID)) != null && widget.isVisible()) {
            final RS2Widget[] children = widget.getChildWidgets();
            int index = 0;
            for (RS2Widget w : children) {
                for (String opt : options) {
                    if (w != null && w.getMessage().equals(opt)) {
                        this.ctx.keyboard.typeKey(Character.forDigit(index, 10));
                        return true;
                    }
                }
                index++;
            }
            // couldn't find choose random
            int rand = Random.nextInt(0, children.length);
            final RS2Widget child;
            if ((child = children[rand]) != null && child.isVisible()) {
                this.ctx.keyboard.typeKey(Character.forDigit(rand + 1, 10));
                return true;
            }
        }
        return false;
    }

    public boolean isOpen() {
        return canContinue() || stickyCanContinue() || optionsOpen();
    }

    public boolean complete(final String... options) {
        if (this.canContinue()) {
            this.doContinue();
        } else if (this.stickyCanContinue()) {
            return this.stickyDoContinue();
        } else if (this.optionsOpen()) {
            return this.processOptions(options);
        }
        return false;
    }

}

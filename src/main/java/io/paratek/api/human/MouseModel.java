package io.paratek.api.human;

import org.osbot.rs07.api.ui.RS2Widget;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Parametric on 3/12/2018
 * @project OSBot
 */
public class MouseModel {

    private final HashMap<String, Rectangle> bounds = new HashMap<>();

    public Rectangle get(String key) {
        if (bounds.containsKey(key)) {
            return bounds.get(key);
        }
        return null;
    }

    public void add(String key, RS2Widget widget) {
        if (widget != null) {
            this.add(key, widget.getBounds());
        }
    }

    public void add(String key, Rectangle rect) {
        if (bounds.containsKey(key)) {
            Rectangle in;
            if (!rect.equals(in =bounds.get(key))) {
                bounds.replace(key, biggest(in, rect));
            }
        } else {
            bounds.put(key, rect);
        }
    }

    private Rectangle biggest(Rectangle rect1, Rectangle rect2) {
        int area1 = rect1.width * rect1.height;
        int area2 = rect2.width * rect2.height;
        return area1 > area2 ? rect1 : rect2;
    }

}

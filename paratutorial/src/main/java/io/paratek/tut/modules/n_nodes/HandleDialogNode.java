package io.paratek.tut.modules.n_nodes;

import io.paratek.api.util.Chatbox;
import io.paratek.fw.ParaScript;
import io.paratek.fw.handler.node.Node;
import org.osbot.rs07.api.ui.RS2Widget;

import java.util.ArrayList;

/**
 * @author Parametric on 5/5/2018
 * @project OSBot-Framework
 */
public class HandleDialogNode implements Node {

    private Chatbox chatbox;

    private final ArrayList<String> options = new ArrayList<>();
    private final String[] strings;

    public HandleDialogNode() {
        options.add("I've played in the past, but not recently.");
        this.strings = this.options.toArray(new String[this.options.size()]);
    }

    @Override
    public boolean activate(ParaScript ctx) {
        this.chatbox = new Chatbox(ctx);
        return this.chatbox.isOpen();
    }

    @Override
    public boolean execute(ParaScript ctx) throws InterruptedException {
        this.chatbox.complete(this.strings);
        return true;
    }

}

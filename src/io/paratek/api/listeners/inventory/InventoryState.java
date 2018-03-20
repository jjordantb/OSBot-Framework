package io.paratek.api.listeners.inventory;

import org.osbot.rs07.api.Client;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.Script;

import java.util.HashMap;

/**
 * @author Parametric on 2/5/2018
 * @project OSBot
 */
public class InventoryState {

    private final Script context;
    private HashMap<Integer, Integer> inventoryMap;

    private boolean initd = false;

    public InventoryState(Script script) {
        context = script;
    }

    public ItemEvent inventoryChanges() {
        if (!this.initd && this.context.client.getLoginState().equals(Client.LoginState.LOGGED_IN)) {
            this.inventoryMap = this.getInventoryData();
            this.initd = true;
        }
        if (this.initd) {
            final HashMap<Integer, Integer> currMap = this.getInventoryData();
            for (Integer i : currMap.keySet()) {
                if (this.inventoryMap.containsKey(i)) {
                    final int val, val2;
                    if ((val = currMap.get(i)) > this.inventoryMap.get(i)) {
                        final ItemEvent event = new ItemEvent(i, val - this.inventoryMap.get(i), ItemEvent.Type.ADD);
                        this.inventoryMap.put(i, val);
                        return event;
                    } else if ((val2 = currMap.get(i)) < this.inventoryMap.get(i)) {
                        final ItemEvent event = new ItemEvent(i, Math.abs(val2 - this.inventoryMap.get(i)), ItemEvent.Type.REMOVE);
                        if (val2 == 0) {
                            this.inventoryMap.remove(i);
                        } else {
                            this.inventoryMap.put(i, val2);
                        }
                        return event;
                    }
                } else {
                    this.inventoryMap.put(i, currMap.get(i));
                    return new ItemEvent(i, this.inventoryMap.get(i), ItemEvent.Type.ADD);
                }
            }
            for (Integer i : inventoryMap.keySet()) {
                if (!currMap.containsKey(i)) {
                    final ItemEvent event = new ItemEvent(i, inventoryMap.get(i), ItemEvent.Type.REMOVE);
                    inventoryMap.remove(i);
                    return event;
                }
            }
        }
        return null;
    }


    private HashMap<Integer, Integer> getInventoryData() {
        final HashMap<Integer, Integer> invMap = new HashMap<>();
        Item[] items = this.context.inventory.getItems();
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item != null) {
                invMap.put(item.getId(), invMap.containsKey(item.getId())
                        ? invMap.get(item.getId()) + item.getAmount() : item.getAmount());
            }
        }
        return invMap;
    }
}

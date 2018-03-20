package io.paratek.api.listeners.inventory;

import io.paratek.api.listeners.Listener;

/**
 * @author Parametric on 2/5/2018
 * @project TriBot
 */
public interface InventoryListener extends Listener {

    void onItemRemoved(final ItemEvent itemEvent);


    void onItemEntered(final ItemEvent itemEvent);

}

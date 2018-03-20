package io.paratek.api.listeners.inventory;

/**
 * @author Parametric on 2/5/2018
 * @project TriBot
 */
public class ItemEvent {

    private final int itemID;
    private final int quantityChange;
    private final Type type;

    public ItemEvent(int itemID, int quantityChange, Type type) {
        this.itemID = itemID;
        this.quantityChange = quantityChange;
        this.type = type;
    }

    public int getId() {
        return itemID;
    }

    public int getQuantityChange() {
        return quantityChange;
    }

    public Type getType() {
        return type;
    }

    public enum Type {

        ADD,
        REMOVE,
        UNKNOWN;

    }

}

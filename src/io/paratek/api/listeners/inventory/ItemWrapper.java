package io.paratek.api.listeners.inventory;

/**
 * @author Parametric on 3/12/2018
 * @project OSBot
 */
public class ItemWrapper {

    private final int id;
    private final String name;

    public ItemWrapper(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ItemWrapper && ((ItemWrapper) obj).getId() == this.getId();
    }
}

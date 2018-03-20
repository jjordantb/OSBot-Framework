package io.paratek.api.listeners;

import io.paratek.api.listeners.inventory.InventoryListener;
import io.paratek.api.listeners.inventory.InventoryState;
import io.paratek.api.listeners.inventory.ItemEvent;
import io.paratek.api.listeners.skill.SkillEvent;
import io.paratek.api.listeners.skill.SkillListener;
import io.paratek.api.listeners.skill.SkillState;
import org.osbot.rs07.script.Script;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Parametric on 2/5/2018
 * @project TriBot
 */
public class EventDispatcher {

    private final ScheduledExecutorService executorService;

    private final ArrayList<InventoryListener> inventoryListeners;
    private final InventoryState inventoryState;

    private final ArrayList<SkillListener> skillListeners;
    private final SkillState skillState;

    private final Script context;

    public EventDispatcher(Script context) {
        this.context = context;
        this.inventoryState = new InventoryState(this.context);
        this.inventoryListeners = new ArrayList<>();
        this.skillState = new SkillState(this.context);
        this.skillListeners = new ArrayList<>();

        this.executorService = Executors.newScheduledThreadPool(2);
        this.executorService.scheduleAtFixedRate(() -> {
            final ItemEvent item;
            if ((item = this.inventoryState.inventoryChanges()) != null) {
                for (InventoryListener inventoryListener : this.inventoryListeners) {
                    if (item.getType() == ItemEvent.Type.ADD) {
                        inventoryListener.onItemEntered(item);
                    } else if (item.getType() == ItemEvent.Type.REMOVE) {
                        inventoryListener.onItemRemoved(item);
                    }
                }
            }
        },0, 100, TimeUnit.MILLISECONDS);
        this.executorService.scheduleAtFixedRate(() -> {
            final SkillEvent s1;
            if ((s1 = this.skillState.experienceGained()) != null && this.skillState.isInited()) {
                for (SkillListener skillListener : this.skillListeners) {
                    skillListener.onExperienceGain(s1);
                }
            }
            final SkillEvent s2;
            if ((s2 = this.skillState.levelGained()) != null && this.skillState.isInited()) {
                for (SkillListener skillListener : this.skillListeners) {
                    skillListener.onLevelGain(s2);
                }
            }
        },0, 100, TimeUnit.MILLISECONDS);
    }

    public void submitListener(final Listener listener) {
        if (listener instanceof InventoryListener) {
            this.inventoryListeners.add((InventoryListener) listener);
        }
        if (listener instanceof SkillListener) {
            this.skillListeners.add((SkillListener) listener);
        }
    }


    public void stop() {
        this.executorService.shutdown();
    }
}

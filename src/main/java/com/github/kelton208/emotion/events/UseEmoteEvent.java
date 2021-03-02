package com.github.kelton208.emotion.events;

import com.github.kelton208.emotion.EmoteType;
import com.github.kelton208.emotion.Emotion;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class UseEmoteEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final EmoteType type;
    private final ItemStack stack;
    private final String chat;
    private final Emotion<Entity> emotion;
    private boolean cancelled;

    public UseEmoteEvent(EmoteType type, ItemStack stack, String chat, Emotion<Entity> emote) {
        this.type = type;
        this.stack = stack;
        this.chat = chat;
        this.emotion = emote;
    }

    public EmoteType getType() {
        return this.type;
    }

    public ItemStack getItem() {
        return this.stack;
    }

    public String getChat() {
        return this.chat;
    }

    public Emotion<Entity> getEmotion() {
        return this.emotion;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

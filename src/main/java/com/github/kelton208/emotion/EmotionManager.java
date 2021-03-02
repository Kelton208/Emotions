package com.github.kelton208.emotion;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public interface EmotionManager {

    void registerEmotion(Emotion<Entity> emotion, ItemStack stack);
    void registerEmotion(Emotion<Entity> emote, String str);
    Listener getListener();

}

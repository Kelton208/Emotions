package com.github.kelton208.emotion;

import com.github.kelton208.emotion.events.UseEmoteEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Emotions extends JavaPlugin {

    private final EmotionManager manager = new EmotionManager() {

        private final HashMap<ItemStack, Emotion<Entity>> emotions_cl =  new HashMap<>();
        private final HashMap<String, Emotion<Entity>> emotes_chat = new HashMap<>();
        private final Listener listener = new Listener() {

            @EventHandler
            public void onClick(PlayerInteractEvent e) {
                if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    emotions_cl.forEach((k, v) -> {
                        if(e.getItem() == null) return;
                        UseEmoteEvent event = new UseEmoteEvent(EmoteType.RIGHT_CLICK, e.getItem(), "", v, e.getPlayer());
                        Bukkit.getServer().getPluginManager().callEvent(event);
                        if (e.getItem().isSimilar(k) && !event.isCancelled()) v.show(e.getPlayer());
                    });
            }

            @EventHandler
            public void onChat(AsyncPlayerChatEvent e) {
                emotes_chat.forEach((k, v) -> {
                    UseEmoteEvent event = new UseEmoteEvent(EmoteType.CHAT, new ItemStack(Material.AIR), e.getMessage(), v, e.getPlayer());
                    Bukkit.getServer().getPluginManager().callEvent(event);
                    if(e.getMessage().equals(k) && !event.isCancelled()) v.show(e.getPlayer());
                });
            }

        };

        @Override
        public void registerEmotion(Emotion<Entity> emote, ItemStack stack) {
            emotions_cl.put(stack, emote);
        }

        @Override
        public void registerEmotion(Emotion<Entity> emote, String str) {
            emotes_chat.put(str, emote);
        }

        @Override
        public Listener getListener() {
            return this.listener;
        }

    };

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Emotions plugin is now enable");
        Bukkit.getPluginManager().registerEvents(manager.getListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Emotions plugin is now disable");
    }

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("Emotions");
    }

    public EmotionManager getEmoteManager() {
        return this.manager;
    }
}

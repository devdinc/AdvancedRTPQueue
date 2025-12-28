package com.kingrbxd.rtpqueue.listeners;

import com.kingrbxd.rtpqueue.AdvancedRTPQueue;

import io.papermc.paper.event.player.AsyncChatEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Complete player chat listener
 */
public class PlayerChatListener implements Listener {
    private final AdvancedRTPQueue plugin;

    public PlayerChatListener(AdvancedRTPQueue plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        if (!plugin.getConfigManager().getBoolean("teleport.cancel-on-chat", false)) {
            return;
        }

        Player player = event.getPlayer();

        // Cancel active teleport session if player chatted
        if (plugin.getTeleportManager().hasActiveSession(player)) {
            plugin.getTeleportManager().cancelPlayerSession(player, "chat");
        }
    }
}
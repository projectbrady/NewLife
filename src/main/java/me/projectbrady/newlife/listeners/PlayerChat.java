package me.projectbrady.newlife.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.projectbrady.newlife.utils.ChatUtils.chat;

public class PlayerChat implements Listener {

    private final String chat;

    public PlayerChat(String chat) {
        this.chat = chat;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.setFormat(chat(p, e.getMessage()));

    }

}

package me.projectbrady.newlife.listeners;

import me.projectbrady.newlife.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static me.projectbrady.newlife.utils.ChatUtils.sChat;


public class PlayerJoin implements Listener {
    private final String chat;


    public PlayerJoin(String chat) {
        this.chat = chat;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        e.setJoinMessage(null);

        Bukkit.getServer().broadcastMessage(sChat(Main.plugin.getConfig().getString("Messages.Welcome")
                .replace("&", "§").replace("{player}", p.getDisplayName())));
    }

}

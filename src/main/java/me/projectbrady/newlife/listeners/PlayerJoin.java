package me.projectbrady.newlife.listeners;

import me.projectbrady.newlife.Main;
import me.projectbrady.newlife.db.Database;
import me.projectbrady.newlife.models.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

import static me.projectbrady.newlife.utils.ChatUtils.sChat;


public class PlayerJoin implements Listener {
    private final String chat;

    private final Database db;

    public PlayerJoin(String chat, Database db) {
        this.chat = chat;
        this.db = db;
    }

    public PlayerStats getPlayerStatsFromDatabase(Player player) throws SQLException {
        PlayerStats playerStats = db.findPlayerStatsByUUID(player.getUniqueId().toString());

        if(playerStats == null) {
            playerStats = new PlayerStats(player.getUniqueId().toString(), 0, 0, 0.0);
            db.createPlayerStats(playerStats);
        }
        return playerStats;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        try{
            PlayerStats playerStats = getPlayerStatsFromDatabase(p);
            db.updatePlayerStats(playerStats);
        }catch (SQLException exception){
            exception.printStackTrace();
            System.out.println("Could not update player stats after join.");
        }
        e.setJoinMessage(null);

        Bukkit.getServer().broadcastMessage(sChat(Main.plugin.getConfig().getString("Messages.Welcome")
                .replace("&", "§").replace("{player}", p.getDisplayName())));
    }

}

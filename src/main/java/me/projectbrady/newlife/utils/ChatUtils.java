package me.projectbrady.newlife.utils;

import me.projectbrady.newlife.Main;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static String sChat(String s) {
        String prefix = Main.plugin.getConfig().getString("Prefix.Server")
                .replace("&", "§");
        String msg = "" + prefix + s;

        return msg;
    }

    public static String chat(Player p, String s) {
        String prefix = Main.plugin.getConfig().getString("Prefix.Member");

        String playerMessage = prefix + Main.plugin.getConfig().getString("Messages.PlayerChatFormat")
                .replace("&", "§")
                .replace("{player}", p.getDisplayName()) + s;

        return playerMessage;
    }


}

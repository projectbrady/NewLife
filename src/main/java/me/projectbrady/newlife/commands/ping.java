package me.projectbrady.newlife.commands;

import me.projectbrady.newlife.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.projectbrady.newlife.utils.ChatUtils.sChat;

public class ping implements CommandExecutor {

    private final String chat;

    public ping(String chat) {
        this.chat = chat;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(sender instanceof Player) {
            sender.sendMessage(sChat("pong"));
        }

        return true;
    }
}

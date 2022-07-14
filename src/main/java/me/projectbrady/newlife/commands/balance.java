package me.projectbrady.newlife.commands;

        import me.projectbrady.newlife.Main;
        import net.milkbowl.vault.economy.Economy;
        import org.bukkit.ChatColor;
        import org.bukkit.OfflinePlayer;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandExecutor;
        import org.bukkit.command.CommandSender;
        import org.bukkit.entity.Player;

public class balance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        Economy econ = Main.getEconomy();

        if(sender instanceof Player) {
            player.sendMessage(ChatColor.YELLOW + "You currently have a balance of " +
                    ChatColor.GREEN + econ.format(econ.getBalance(player)));
        }

        return true;
    }
}

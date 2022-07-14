package me.projectbrady.newlife;

import me.projectbrady.newlife.commands.balance;
import me.projectbrady.newlife.commands.ping;
import me.projectbrady.newlife.db.Database;
import me.projectbrady.newlife.listeners.PlayerChat;
import me.projectbrady.newlife.listeners.PlayerJoin;
import me.projectbrady.newlife.utils.ChatUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public final class Main extends JavaPlugin implements Listener {

    private static Economy econ;
    public static Plugin plugin;
    public static FileConfiguration config;
    private Database database;
    private final String chat = String.valueOf(new ChatUtils());


    @Override
    public void onEnable() {
        plugin = this;
        config = getConfig();

        //Create database
        try {
            this.database = new Database();
            this.database.initializeDatabase();
        } catch (SQLException e) {
            Bukkit.getLogger().info("[New Life] Unable to connect to database and create tables");
        }


        //setup economy
        if (!setupEconomy() ) {
            Bukkit.getLogger().info("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //register events & commands
        Bukkit.getLogger().info("[NewLife] has been enabled | version 0.0.1");
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoin(chat, database), this);
        pluginManager.registerEvents(new PlayerChat(chat), this);

        getCommand("ping").setExecutor(new ping(chat));
        getCommand("balance").setExecutor(new balance());

        //save config
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[NewLife] has been disabled");
    }

    public Database getDatabase() {
        return database;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}

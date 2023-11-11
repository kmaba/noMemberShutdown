package com.abdullaharafat.noMemberShutdown;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("noMemberShutdown has started");

        getServer().getPluginManager().registerEvents(this, this);

        saveDefaultConfig();
        FileConfiguration config = getConfig();

        int delayInSeconds = config.getInt("shutdownDelay");
        getServer().getScheduler().runTaskTimer(this, () -> {
            if (Bukkit.getOnlinePlayers().isEmpty()) {
                getLogger().info("No members online. Server will shut down in " + delayInSeconds + " seconds.");
                Bukkit.getServer().getScheduler().runTaskLater(this, Bukkit::shutdown, delayInSeconds * 20L);
            }
        }, 0L, 20L * 60L);
    }

    @Override
    public void onDisable() {
        getLogger().info("noMemberShutdown has stopped");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        getServer().getScheduler().cancelTasks(this);
        getLogger().info("Scheduled shutdown canceled. A player has joined.");
    }
}

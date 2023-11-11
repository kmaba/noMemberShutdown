package com.abdullaharafat.noMemberShutdown;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("noMemberShutdown has started");
    }
    @Override
    public void onDisable() {
        getLogger().info("noMemberShutdown has stopped");
    }
}
package me.mythicalflame.spigotmodding;

import me.mythicalflame.spigotmodding.commands.CommandTest;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotModding extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        System.out.println("Starting up SpigotModding plugin...");
        getServer().getPluginManager().registerEvents(new EventWatcher(), this);
        this.getCommand("commandtest").setExecutor(new CommandTest());
        System.out.println("Finished starting up SpigotModding plugin");
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        System.out.println("Shutting down SpigotModding plugin...");
    }
}

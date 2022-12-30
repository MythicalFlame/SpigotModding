package me.mythicalflame.spigotmodding;

import me.mythicalflame.spigotmodding.commands.CommandSpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class SpigotModding extends JavaPlugin
{
    public static ArrayList<ModdedItem> registeredItems = new ArrayList<ModdedItem>();
    @Override
    public void onEnable()
    {
        // Plugin startup logic
        System.out.println("Starting up SpigotModding plugin...");
        getServer().getPluginManager().registerEvents(new EventWatcher(), this);
        this.getCommand("spigotmodding").setExecutor(new CommandSpigotModding());

        System.out.println("Finished starting up SpigotModding plugin");
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        System.out.println("Shutting down SpigotModding plugin...");
    }

    public static boolean registerItems(ModdedItem[] items)
    {
        String expectedNameSpace = items[0].getNamespace();
        //check if all namespaces in the registerItems method are the same - different namespaces should be registered by a separate plugin!
        for (ModdedItem item : items)
        {
            if (!expectedNameSpace.equals(item.getNamespace()))
            {
                System.err.println("Failed to register items! Expected namespace: " + expectedNameSpace + ", received namespace: " + item.getNamespace() + ".");
                return false;
            }
        }
        //If all namespaces are the same, add the items
        for (ModdedItem item : items)
        {
            registeredItems.add(item);
        }

        System.out.println("Successfully registered " + items.length + " items with namespace " + expectedNameSpace + ".");
        return true;
    }
}

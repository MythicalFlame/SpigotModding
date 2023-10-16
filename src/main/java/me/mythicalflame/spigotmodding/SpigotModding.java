package me.mythicalflame.spigotmodding;

import me.mythicalflame.spigotmodding.commands.CommandSpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.Mod;
import me.mythicalflame.spigotmodding.utilities.ModRegister;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class SpigotModding extends JavaPlugin
{
    private static Plugin plugin;
    private static Logger logger;
    private static ArrayList<Mod> registeredMods = new ArrayList<>();

    @Override
    public void onEnable()
    {
        plugin = this;
        logger = getLogger();

        //Set up configuration
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Enable commands
        this.getCommand("spigotmodding").setExecutor(new CommandSpigotModding());

        logger.info("Finished starting up!");
    }

    public static ArrayList<Mod> getRegisteredMods()
    {
        return registeredMods;
    }

    //registration of mod objects
    public static boolean registerMod(Mod mod)
    {
        for (Mod i : registeredMods)
        {
            if (i.getNamespace().equalsIgnoreCase(mod.getNamespace()))
            {
                logger.warning("Could not register mod \"" + mod.getDisplayName() + "\" due to namespace \"" + mod.getNamespace() + "\" being already used!");
                return false;
            }
        }

        for (ModdedItem item : mod.getRegisteredItems())
        {
            if (!item.getNamespace().equals(mod.getNamespace()))
            {
                logger.warning("Could not register mod \"" + mod.getDisplayName() + "\" due to multiple namespaces being used!");
                return false;
            }
        }

        ModRegister.register(mod, plugin);

        registeredMods.add(mod);

        return true;
    }
}

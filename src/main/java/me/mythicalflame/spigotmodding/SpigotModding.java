package me.mythicalflame.spigotmodding;

import me.mythicalflame.spigotmodding.commands.CommandSpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.Mod;
import me.mythicalflame.spigotmodding.utilities.ModRegister;
import me.mythicalflame.spigotmodding.utilities.Version;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class SpigotModding extends JavaPlugin
{
    private static final Version[] compatibleVersions = {new Version(0, 7, 0, "release"),
                                                         new Version(1, 0, 0, "universal")};
    private static Plugin plugin;
    private static Logger logger;
    private static final ArrayList<Mod> registeredMods = new ArrayList<>();
    private static NamespacedKey contentKey;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable()
    {
        plugin = this;
        logger = getLogger();
        contentKey = new NamespacedKey(this, "content");

        //Set up configuration
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Enable commands
        this.getCommand("spigotmodding").setExecutor(new CommandSpigotModding());

        logger.info("Finished starting up!");
        logger.warning("CRITICAL WARNING! You are using the universal (legacy) version of SpigotModding!");
        logger.warning("This version is intended for use with ancient Minecraft versions (1.14-1.17)");
        logger.warning("If you use 1.18 or above, please download the latest release version that supports your MC version.");
        logger.warning("This version will not receive updates, bar a few CRITICAL bug fix updates. There will be no new features.");
        logger.warning("This version is compatible with all mods made for it and 0.7.0-release");
        logger.warning("USE AT YOUR OWN RISK!");
    }

    public static ArrayList<Mod> getRegisteredMods()
    {
        return registeredMods;
    }

    public static NamespacedKey getContentKey()
    {
        return contentKey;
    }

    //registration of mod objects
    @SuppressWarnings("unused")
    public static boolean registerMod(Mod mod)
    {
        boolean isCompatible = false;
        for (Version version : compatibleVersions)
        {
            if (mod.getAPIVersion().equals(version))
            {
                isCompatible = true;
                break;
            }
        }
        if (!isCompatible)
        {
            if (plugin.getConfig().getBoolean("shutDownServerOnError.modVersionError"))
            {
                logger.severe("Critical error found while registering mod " + mod + "!");
                logger.severe("Could not register mod " + mod + " due to requiring an incompatible version! (" + mod.getAPIVersion() + ")");
                logger.severe("You are on the universal (legacy) version of SpigotModding intended for 1.14-1.17");
                logger.severe("If you are on 1.18 or higher, please use the release version");
                logger.severe("Otherwise, you may only use mods built for this version or 0.7.0-release");
                logger.severe("The server is now shutting down as a precautionary measure in case this mod was integral to your server experience.");
                logger.severe("To disable the server shutting down, set shutDownServerOnError.modVersionError to false in the SpigotModding configuration.");
                plugin.getServer().shutdown();
            }
            else
            {
                logger.warning("Could not register mod " + mod + " due to requiring an incompatible version! (" + mod.getAPIVersion() + ")");
                logger.warning("You are on the universal (legacy) version of SpigotModding intended for 1.14-1.17");
                logger.warning("If you are on 1.18 or higher, please use the release version");
                logger.warning("Otherwise, you may only use mods built for this version or 0.7.0-release");
            }
            return false;
        }

        for (Mod i : registeredMods)
        {
            if (i.getNamespace().equals(mod.getNamespace()))
            {
                if (plugin.getConfig().getBoolean("shutDownServerOnError.modNamespaceCollision"))
                {
                    logger.severe("Critical error found while registering mod " + mod + "!");
                    logger.severe("Could not register mod " + mod + " due to namespace " + mod.getNamespace() + " being already used!");
                    logger.severe("The server is now shutting down as a precautionary measure in case this mod was integral to your server experience.");
                    logger.severe("To disable the server shutting down, set shutDownServerOnError.modNamespaceCollision to false in the SpigotModding configuration.");
                    plugin.getServer().shutdown();
                }
                else
                {
                    logger.warning("Could not register mod " + mod + " due to namespace " + mod.getNamespace() + " being already used!");
                }

                return false;
            }
        }

        for (ModdedItem item : mod.getRegisteredItems())
        {
            if (!item.getNamespace().equals(mod.getNamespace()))
            {
                if (plugin.getConfig().getBoolean("shutDownServerOnError.modDoubleNamespaces"))
                {
                    logger.severe("Critical error found while registering mod " + mod + "!");
                    logger.severe("Could not register mod " + mod + " due to multiple namespaces being used!");
                    logger.severe("The server is now shutting down as a precautionary measure in case this mod was integral to your server experience.");
                    logger.severe("To disable the server shutting down, set shutDownServerOnError.modDoubleNamespaces to false in the SpigotModding configuration.");
                    plugin.getServer().shutdown();
                }
                else
                {
                    logger.warning("Could not register mod " + mod + " due to multiple namespaces being used!");
                }

                return false;
            }
        }

        ModRegister.register(mod, plugin);
        registeredMods.add(mod);
        logger.info("Successfully registered mod " + mod);

        return true;
    }

    @SuppressWarnings("unused")
    public static Version[] getCompatibleVersions()
    {
        return compatibleVersions;
    }
}

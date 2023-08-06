package me.mythicalflame.spigotmodding;

import me.mythicalflame.spigotmodding.commands.CommandSpigotModding;
import me.mythicalflame.spigotmodding.functionalities.ArmorTask;
import me.mythicalflame.spigotmodding.functionalities.GeneralEventWatcher;
import me.mythicalflame.spigotmodding.functionalities.RecipeEventWatcher;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedConsumable;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public final class SpigotModding extends JavaPlugin
{
    private static final Logger logger = Logger.getLogger(SpigotModding.class.getName());

    //registeredItems - array to access all registered items
    private static ArrayList<ModdedItem> registeredItems = new ArrayList<>();
    //BELOW: ArrayLists holding specific item types. To be used for EventWatcher
    private static ArrayList<ModdedConsumable> consumables = new ArrayList<>();
    //armor sets - do not hold items
    private static ArrayList<ModdedArmorSet> registeredArmorSets = new ArrayList<>();

    @Override
    public void onEnable()
    {
        //Set up configuration
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Check configuration
        //If enabled, check for basic item functionality (clicking, attacking, eating...)
        if (getConfig().getBoolean("itemFunctionality"))
        {
            getServer().getPluginManager().registerEvents(new GeneralEventWatcher(), this);
        }
        //If enabled, cancel recipes that use custom items
        if (getConfig().getBoolean("cancelRecipes"))
        {
            getServer().getPluginManager().registerEvents(new RecipeEventWatcher(), this);
        }
        //If enabled, watch for custom armor wearing
        if (getConfig().getBoolean("armorFunctionality"))
        {
            BukkitTask armorTask = new ArmorTask(this).runTaskTimer(this, 100, 200);
        }

        //Enable commands
        this.getCommand("spigotmodding").setExecutor(new CommandSpigotModding());

        logger.info("Finished starting up!");
    }

    public static ArrayList<ModdedItem> getRegisteredItems()
    {
        return registeredItems;
    }

    public static ArrayList<ModdedConsumable> getConsumables()
    {
        return consumables;
    }

    public static ArrayList<ModdedArmorSet> getRegisteredArmorSets()
    {
        return registeredArmorSets;
    }

    public static ModdedItem getRegisteredItem(String namespace, String ID)
    {
        for (ModdedItem item : registeredItems)
        {
            if (item.getNamespace().equalsIgnoreCase(namespace) && item.getID().equalsIgnoreCase(ID))
            {
                return item;
            }
        }

        return null;
    }

    //registration of all modded content
    public static boolean registerMods(ModdedItem[] items, ModdedArmorSet[] armorSets)
    {
        if (items != null)
        {
            if(!registerItems(items))
            {
                return false;
            }

            if (armorSets != null)
            {
                return registerArmorSets(armorSets);
            }
        }

        return true;
    }

    private static boolean registerArmorSets(ModdedArmorSet[] armorSets)
    {
        //check validity
        for (ModdedArmorSet set : armorSets)
        {
            if (set.getPieces().length > 4)
            {
                logger.severe("Failed to register armor sets! One of your armor set has more than 4 pieces.");
                return false;
            }
        }
        //valid
        registeredArmorSets.addAll(Arrays.asList(armorSets));

        return true;
    }

    private static boolean registerItems(ModdedItem[] items)
    {
        String expectedNameSpace = items[0].getNamespace();
        //check if all namespaces in the registerItems method are the same - different namespaces should be registered by a separate plugin!
        for (ModdedItem item : items)
        {
            if (!expectedNameSpace.equals(item.getNamespace()))
            {
                logger.severe("Failed to register items! Expected namespace: " + expectedNameSpace + ", received namespace: " + item.getNamespace() + ".");
                return false;
            }
        }
        //If all namespaces are the same, add the items
        for (ModdedItem item : items)
        {
            registeredItems.add(item);
            if (item instanceof ModdedConsumable)
            {
                consumables.add((ModdedConsumable) item);
            }
        }

        logger.info("Successfully registered " + items.length + " items with namespace " + expectedNameSpace + ".");
        return true;
    }
}

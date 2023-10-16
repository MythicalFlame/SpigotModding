package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.functionalities.EntityDamageByEntityEventWatcher;
import me.mythicalflame.spigotmodding.functionalities.EntityDeathEventWatcher;
import me.mythicalflame.spigotmodding.functionalities.ModTickTask;
import me.mythicalflame.spigotmodding.functionalities.PlayerInteractEventWatcher;
import me.mythicalflame.spigotmodding.functionalities.PlayerItemConsumeEventWatcher;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedConsumable;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class ModRegister
{
    /*
        Index - Listener
        0 - ModTickTask
        1 - PlayerInteractEventWatcher
        2 - PlayerItemConsumeEventWatcher
        3 - EntityDeathEventWatcher
        4 - EntityDamageByEntityEventWatcher
     */
    private static boolean[] registered = new boolean[5];

    public static void register(Mod mod, Plugin plugin)
    {
        registerArmor(mod, plugin);
        registerItems(mod, plugin);
    }

    private static void registerArmor(Mod mod, Plugin plugin)
    {
        for (ModdedArmorSet set : mod.getRegisteredArmor())
        {
            Class<? extends ModdedArmorSet> clazz = set.getClass();

            //ModTickTask
            try
            {
                if (clazz.getMethod("onTick", Player.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[0])
                    {
                        BukkitTask task = new ModTickTask().runTaskTimer(plugin, 100, 1);
                    }

                    ModTickTask.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //PlayerInteractEventWatcher
            try
            {
                if (clazz.getMethod("onInteract", PlayerInteractEvent.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[1])
                    {
                        plugin.getServer().getPluginManager().registerEvents(new PlayerInteractEventWatcher(), plugin);
                    }

                    PlayerInteractEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //PlayerItemConsumeEventWatcher
            try
            {
                if (clazz.getMethod("onConsume", PlayerItemConsumeEvent.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[2])
                    {
                        plugin.getServer().getPluginManager().registerEvents(new PlayerItemConsumeEventWatcher(), plugin);
                    }

                    PlayerItemConsumeEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //EntityDeathEventWatcher
            try
            {
                if (clazz.getMethod("onKill", EntityDeathEvent.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[3])
                    {
                        plugin.getServer().getPluginManager().registerEvents(new EntityDeathEventWatcher(), plugin);
                    }

                    EntityDeathEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //EntityDamageByEntityEventWatcher
            try
            {
                if (clazz.getMethod("onAttack", EntityDamageByEntityEvent.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[4])
                    {
                        plugin.getServer().getPluginManager().registerEvents(new EntityDamageByEntityEventWatcher(), plugin);
                    }

                    EntityDamageByEntityEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}
        }
    }

    private static void registerItems(Mod mod, Plugin plugin)
    {
        for (ModdedItem item : mod.getRegisteredItems())
        {
            Class<? extends ModdedItem> clazz = item.getClass();

            //ModTickTask
            try
            {
                if (clazz.getMethod("onTick", Player.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[0])
                    {
                        BukkitTask task = new ModTickTask().runTaskTimer(plugin, 100, 1);
                    }

                    ModTickTask.addItem(item);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //PlayerInteractEventWatcher
            try
            {
                if (clazz.getMethod("onInteract", PlayerInteractEvent.class, EventType.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[1])
                    {
                        plugin.getServer().getPluginManager().registerEvents(new PlayerInteractEventWatcher(), plugin);
                    }

                    PlayerInteractEventWatcher.addItem(item);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //PlayerItemConsumeEventWatcher
            if (item instanceof ModdedConsumable)
            {
                Class<? extends ModdedConsumable> consumeClazz = ((ModdedConsumable) item).getClass();
                try
                {
                    if (consumeClazz.getMethod("onConsume", PlayerItemConsumeEvent.class).getDeclaringClass().equals(consumeClazz))
                    {
                        if (!registered[2])
                        {
                            plugin.getServer().getPluginManager().registerEvents(new PlayerItemConsumeEventWatcher(), plugin);
                        }

                        PlayerItemConsumeEventWatcher.addItem((ModdedConsumable) item);
                    }
                }
                catch (NoSuchMethodException ignored) {}
            }

            //EntityDeathEventWatcher
            try
            {
                if (clazz.getMethod("onKill", EntityDeathEvent.class, EventType.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[3])
                    {
                        plugin.getServer().getPluginManager().registerEvents(new EntityDeathEventWatcher(), plugin);
                    }

                    EntityDeathEventWatcher.addItem(item);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //EntityDamageByEntityEventWatcher
            try
            {
                if (clazz.getMethod("onAttack", EntityDamageByEntityEvent.class, EventType.class).getDeclaringClass().equals(clazz))
                {
                    if (!registered[4])
                    {
                        plugin.getServer().getPluginManager().registerEvents(new EntityDamageByEntityEventWatcher(), plugin);
                    }

                    EntityDamageByEntityEventWatcher.addItem(item);
                }
            }
            catch (NoSuchMethodException ignored) {}
        }
    }
}

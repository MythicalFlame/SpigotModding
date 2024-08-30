package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.functionalities.EntityDamageByEntityEventWatcher;
import me.mythicalflame.spigotmodding.functionalities.EntityDeathEventWatcher;
import me.mythicalflame.spigotmodding.functionalities.ModTickTask;
import me.mythicalflame.spigotmodding.functionalities.PlayerInteractEventWatcher;
import me.mythicalflame.spigotmodding.functionalities.PlayerItemConsumeEventWatcher;
import me.mythicalflame.spigotmodding.items.ModdedArmorPiece;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedConsumable;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Arrays;
import java.util.HashSet;

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
    private static final boolean[] registered = new boolean[5];

    public static void register(Mod mod, SpigotModding plugin)
    {
        registerArmor(mod, plugin);
        registerItems(mod, plugin);
    }

    private static void registerArmor(Mod mod, SpigotModding plugin)
    {
        for (ModdedArmorSet set : mod.getRegisteredArmor())
        {
            Class<? extends ModdedArmorSet> clazz = set.getClass();
            Class<ModdedArmorSet> defaultClazz = ModdedArmorSet.class;

            //ModTickTask
            try
            {
                if (!clazz.getMethod("onTick", Player.class).getDeclaringClass().equals(defaultClazz))
                {
                    if (!registered[0])
                    {
                        registered[0] = true;
                        new ModTickTask().runTaskTimer(plugin, 100, 1);
                    }

                    ModTickTask.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //PlayerInteractEventWatcher
            try
            {
                if (!clazz.getMethod("onInteract", PlayerInteractEvent.class).getDeclaringClass().equals(defaultClazz))
                {
                    if (!registered[1])
                    {
                        registered[1] = true;
                        plugin.getServer().getPluginManager().registerEvents(new PlayerInteractEventWatcher(), plugin);
                    }

                    PlayerInteractEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //PlayerItemConsumeEventWatcher
            try
            {
                if (!clazz.getMethod("onConsume", PlayerItemConsumeEvent.class).getDeclaringClass().equals(defaultClazz))
                {
                    if (!registered[2])
                    {
                        registered[2] = true;
                        plugin.getServer().getPluginManager().registerEvents(new PlayerItemConsumeEventWatcher(), plugin);
                    }

                    PlayerItemConsumeEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //EntityDeathEventWatcher
            try
            {
                if (!clazz.getMethod("onKill", EntityDeathEvent.class).getDeclaringClass().equals(defaultClazz))
                {
                    if (!registered[3])
                    {
                        registered[3] = true;
                        plugin.getServer().getPluginManager().registerEvents(new EntityDeathEventWatcher(), plugin);
                    }

                    EntityDeathEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //EntityDamageByEntityEventWatcher
            try
            {
                if (!clazz.getMethod("onAttack", EntityDamageByEntityEvent.class).getDeclaringClass().equals(defaultClazz))
                {
                    if (!registered[4])
                    {
                        registered[4] = true;
                        plugin.getServer().getPluginManager().registerEvents(new EntityDamageByEntityEventWatcher(), plugin);
                    }

                    EntityDamageByEntityEventWatcher.addArmor(set);
                }
            }
            catch (NoSuchMethodException ignored) {}
        }
    }

    private static void registerItems(Mod mod, SpigotModding plugin)
    {
        for (ModdedItem item : mod.getRegisteredItems())
        {
            Class<? extends ModdedItem> clazz = item.getClass();
            Class<?>[] defaultValues = {ModdedItem.class, ModdedConsumable.class, ModdedArmorPiece.class, ModdedArmorSet.class};
            HashSet<Class<?>> defaultSet = new HashSet<>(Arrays.asList(defaultValues));

            //ModTickTask
            try
            {
                if (!defaultSet.contains(clazz.getMethod("onTick", Player.class).getDeclaringClass()))
                {
                    if (!registered[0])
                    {
                        registered[0] = true;
                        new ModTickTask().runTaskTimer(plugin, 100, 1);
                    }

                    ModTickTask.addItem(item);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //PlayerInteractEventWatcher
            try
            {
                if (!defaultSet.contains(clazz.getMethod("onInteract", PlayerInteractEvent.class, EventType.class).getDeclaringClass()))
                {
                    if (!registered[1])
                    {
                        registered[1] = true;
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
                    if (!consumeClazz.getMethod("onConsume", PlayerItemConsumeEvent.class).getDeclaringClass().equals(ModdedConsumable.class))
                    {
                        if (!registered[2])
                        {
                            registered[2] = true;
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
                if (!defaultSet.contains(clazz.getMethod("onKill", EntityDeathEvent.class, EventType.class).getDeclaringClass()))
                {
                    if (!registered[3])
                    {
                        registered[3] = true;
                        plugin.getServer().getPluginManager().registerEvents(new EntityDeathEventWatcher(), plugin);
                    }

                    EntityDeathEventWatcher.addItem(item);
                }
            }
            catch (NoSuchMethodException ignored) {}

            //EntityDamageByEntityEventWatcher
            try
            {
                if (!defaultSet.contains(clazz.getMethod("onAttack", EntityDamageByEntityEvent.class, EventType.class).getDeclaringClass()))
                {
                    if (!registered[4])
                    {
                        registered[4] = true;
                        plugin.getServer().getPluginManager().registerEvents(new EntityDamageByEntityEventWatcher(), plugin);
                    }

                    EntityDamageByEntityEventWatcher.addItem(item);
                }
            }
            catch (NoSuchMethodException ignored) {}
        }
    }
}

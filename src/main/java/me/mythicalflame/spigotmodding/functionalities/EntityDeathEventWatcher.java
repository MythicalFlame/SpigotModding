package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.EventType;
import me.mythicalflame.spigotmodding.utilities.SpigotModdingAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

public class EntityDeathEventWatcher implements Listener
{
    private static final ArrayList<ModdedItem> items = new ArrayList<>();
    private static final ArrayList<ModdedArmorSet> armorSets = new ArrayList<>();

    //This event listens for when the player kills another entity, not when the player themselves are killed.
    @EventHandler
    public void killWatcher(EntityDeathEvent event)
    {
        Player killer = event.getEntity().getKiller();

        if (killer == null)
        {
            return;
        }

        checkArmor(event);
        checkInventory(event);
    }

    private void checkArmor(EntityDeathEvent event)
    {
        for (ModdedArmorSet set : armorSets)
        {
            if (SpigotModdingAPI.isWearingSet(event.getEntity().getKiller(), set))
            {
                set.onKill(event);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void checkInventory(EntityDeathEvent event)
    {
        Player player = event.getEntity().getKiller();

        for (ItemStack stack : player.getInventory().getContents())
        {
            ModdedItem item = SpigotModdingAPI.getModdedItem(stack, items);

            EventType type = EventType.IN_INVENTORY;

            if (Objects.equals(stack, player.getInventory().getItemInMainHand()))
            {
                type = EventType.IN_MAIN_HAND;
            }

            if (item != null)
            {
                item.onKill(event, type);
            }
        }
    }

    public static void addArmor(ModdedArmorSet set)
    {
        armorSets.add(set);
    }

    public static void addItem(ModdedItem item)
    {
        items.add(item);
    }
}


package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.EventType;
import me.mythicalflame.spigotmodding.utilities.SpigotModdingAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerInteractEventWatcher implements Listener
{
    private static final ArrayList<ModdedItem> items = new ArrayList<>();
    private static final ArrayList<ModdedArmorSet> armorSets = new ArrayList<>();

    @EventHandler
    public void interactWatcher(PlayerInteractEvent event)
    {
        checkArmor(event);
        checkInventory(event);
    }

    private void checkArmor(PlayerInteractEvent event)
    {
        for (ModdedArmorSet set : armorSets)
        {
            if (SpigotModdingAPI.isWearingSet(event.getPlayer(), set))
            {
                set.onInteract(event);
            }
        }
    }

    private void checkInventory(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        for (ItemStack stack : player.getInventory().getContents())
        {
            ModdedItem item = SpigotModdingAPI.getModdedItem(stack, items);

            EventType type;

            if (Objects.equals(stack, player.getInventory().getItemInMainHand()))
            {
                type = EventType.IN_MAIN_HAND;
            }
            else if (Objects.equals(stack, player.getInventory().getItemInOffHand()))
            {
                type = EventType.IN_OFF_HAND;
            }
            else
            {
                type = EventType.IN_INVENTORY;
            }

            if (item != null)
            {
                item.onInteract(event, type);
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
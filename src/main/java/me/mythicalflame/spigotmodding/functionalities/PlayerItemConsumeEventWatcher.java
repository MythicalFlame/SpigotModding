package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedConsumable;
import me.mythicalflame.spigotmodding.utilities.SpigotModdingAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PlayerItemConsumeEventWatcher implements Listener
{
    private static ArrayList<ModdedConsumable> items = new ArrayList<>();
    private static ArrayList<ModdedArmorSet> armorSets = new ArrayList<>();

    @EventHandler
    public void playerItemConsumeEventWatcher(PlayerItemConsumeEvent event)
    {
        checkArmor(event);
        checkInventory(event);
    }

    private void checkArmor(PlayerItemConsumeEvent event)
    {
        for (ModdedArmorSet set : armorSets)
        {
            if (SpigotModdingAPI.isWearingSet(event.getPlayer(), set))
            {
                set.onConsume(event);
            }
        }
    }

    private void checkInventory(PlayerItemConsumeEvent event)
    {
        ItemStack consumed = event.getItem();

        ModdedConsumable consumableUsed = null;

        if (!consumed.hasItemMeta() || !consumed.getItemMeta().hasCustomModelData())
        {
            return;
        }

        for (ModdedConsumable item : items)
        {
            if (consumed.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())
            {
                consumableUsed = item;
                break;
            }
        }

        if (consumableUsed != null)
        {
            consumableUsed.onConsume(event);
        }
    }

    public static void addArmor(ModdedArmorSet set)
    {
        armorSets.add(set);
    }

    public static void addItem(ModdedConsumable item)
    {
        items.add(item);
    }
}

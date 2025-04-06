package me.mythicalflame.netherreactor.functionalities;

import me.mythicalflame.netherreactor.items.ModdedArmorSet;
import me.mythicalflame.netherreactor.items.ModdedConsumable;
import me.mythicalflame.netherreactor.items.ModdedItem;
import me.mythicalflame.netherreactor.utilities.NetherReactorAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PlayerItemConsumeEventWatcher implements Listener
{
    private static final ArrayList<ModdedItem> items = new ArrayList<>();
    private static final ArrayList<ModdedArmorSet> armorSets = new ArrayList<>();

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
            if (NetherReactorAPI.isWearingSet(event.getPlayer(), set))
            {
                set.onConsume(event);
            }
        }
    }

    private void checkInventory(PlayerItemConsumeEvent event)
    {
        ItemStack consumed = event.getItem();

        ModdedConsumable consumableUsed = (ModdedConsumable) NetherReactorAPI.getModdedItem(consumed, items);

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

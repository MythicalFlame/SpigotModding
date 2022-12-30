package me.mythicalflame.spigotmodding;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventWatcher implements Listener
{
    //NOTE: does not do anything if player clicks on a block instead of air. Could be a separate method?
    @EventHandler
    public void ClickWatcher(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack hand = player.getItemInHand();
        ModdedItem itemUsed = null;
        boolean holdingModdedItem = false;

        for (ModdedItem item : SpigotModding.registeredItems)
        {
            if (hand.equals(item.getItem()))
            {
                itemUsed = item;
                holdingModdedItem = true;
            }
        }

        if (holdingModdedItem)
        {
            if (event.getAction() == Action.LEFT_CLICK_AIR)
            {
                itemUsed.onLeftClick();
            }
            else if (event.getAction() == Action.RIGHT_CLICK_AIR)
            {
                itemUsed.onRightClick();
            }
        }
    }
}

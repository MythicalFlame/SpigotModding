package me.mythicalflame.spigotmodding;

import me.mythicalflame.spigotmodding.items.ModdedItemTinSword;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventWatcher implements Listener
{
    @EventHandler
    public void RightClickWatcher(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack hand = player.getItemInHand();
        ItemStack tinSword = new ModdedItemTinSword().getItem();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (hand.equals(tinSword)) {
                new ModdedItemTinSword().onRightClick();
            }
        }
    }
}
package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedConsumable;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class GeneralEventWatcher implements Listener
{
    //NOTE: does not do anything if player clicks on a block instead of air. Could be a separate method?
    @EventHandler
    public void clickWatcher(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();

        ModdedItem itemUsed = null;
        boolean holdingModdedItem = false;

        //Check for item meta
        if (!hand.hasItemMeta())
        {
            return;
        }
        //Check for lore
        if (!hand.getItemMeta().hasLore())
        {
            return;
        }

        for (ModdedItem item : SpigotModding.getRegisteredItems())
        {
            if (hand.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
            {
                itemUsed = item;
                holdingModdedItem = true;
                break;
            }
        }

        if (holdingModdedItem)
        {
            if (event.getAction() == Action.LEFT_CLICK_AIR)
            {
                itemUsed.onLeftClick(event);
            }
            else if (event.getAction() == Action.RIGHT_CLICK_AIR)
            {
                itemUsed.onRightClick(event);
            }
        }
    }

    @EventHandler
    public void consumeWatcher(PlayerItemConsumeEvent event)
    {
        ItemStack consumed = event.getItem();

        ModdedConsumable consumableUsed = null;
        boolean consumedModdedConsumable = false;

        //Check for item meta
        if (!consumed.hasItemMeta())
        {
            return;
        }
        //Check for lore
        if (!consumed.getItemMeta().hasLore())
        {
            return;
        }

        for (ModdedConsumable item : SpigotModding.getConsumables())
        {
            if (consumed.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
            {
                consumableUsed = item;
                consumedModdedConsumable = true;
                break;
            }
        }

        if (consumedModdedConsumable)
        {
            consumableUsed.onConsume(event);
        }
    }

    @EventHandler
    public void damageWatcher(EntityDamageByEntityEvent event)
    {
        if (event.getDamager() instanceof Player)
        {
            Player attacker = (Player) event.getDamager();
            ItemStack hand = attacker.getInventory().getItemInMainHand();

            ModdedItem itemUsed = null;
            boolean customItemUsed = false;

            //Check for item meta
            if (!hand.hasItemMeta())
            {
                return;
            }
            //Check for lore
            if (!hand.getItemMeta().hasLore())
            {
                return;
            }

            for (ModdedItem item : SpigotModding.getRegisteredItems())
            {
                if (hand.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
                {
                    itemUsed = item;
                    customItemUsed = true;
                    break;
                }
            }

            if (customItemUsed)
            {
                itemUsed.onAttack(event);
            }
        }
    }

    @EventHandler
    public void killWatcher(EntityDeathEvent event)
    {
        LivingEntity killed = event.getEntity();
        Player killer = killed.getKiller();
        ItemStack hand = killer.getInventory().getItemInMainHand();

        ModdedItem itemUsed = null;
        boolean customItemUsed = false;

        //Check for item meta
        if (!hand.hasItemMeta())
        {
            return;
        }
        //Check for lore
        if (!hand.getItemMeta().hasLore())
        {
            return;
        }

        for (ModdedItem item : SpigotModding.getRegisteredItems())
        {
            if (hand.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
            {
                itemUsed = item;
                customItemUsed = true;
                break;
            }
        }

        if (customItemUsed)
        {
            itemUsed.onKill(event);
        }
    }
}

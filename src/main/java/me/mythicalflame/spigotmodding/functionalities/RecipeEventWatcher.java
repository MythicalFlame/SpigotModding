package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

public class RecipeEventWatcher implements Listener
{
    //crafting
    @EventHandler
    public void onCraft(PrepareItemCraftEvent event)
    {
        CraftingInventory inventory = event.getInventory();
        for (ItemStack stack : inventory.getStorageContents()) {
            //check if stack has itemmeta
            if (stack.hasItemMeta())
            {
                //check if stack is a modded item
                for (ModdedItem item : SpigotModding.getRegisteredItems())
                {
                    if (stack.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
                    {
                        inventory.setResult(null);
                        return;
                    }
                }
            }
        }
    }

    //smelting
    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event)
    {
        ItemStack ingredient = event.getSource();
        //check if stack has itemmeta
        if (ingredient.hasItemMeta())
        {
            //check if stack is a modded item
            for (ModdedItem item : SpigotModding.getRegisteredItems())
            {
                if (ingredient.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
                {
                    event.setResult(null);
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    //burning
    @EventHandler
    public void onBurn(FurnaceBurnEvent event)
    {
        ItemStack fuel = event.getFuel();
        //check if stack has itemmeta
        if (fuel.hasItemMeta())
        {
            //check if stack is a modded item
            for (ModdedItem item : SpigotModding.getRegisteredItems())
            {
                if (fuel.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
                {
                    event.setBurning(false);
                    event.setBurnTime(0);
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    //brewing
    @EventHandler
    public void onBrew(BrewEvent event)
    {
        ItemStack ingredient = event.getContents().getIngredient();
        //check if stack has itemmeta
        if (ingredient.hasItemMeta())
        {
            //check if stack is a modded item
            for (ModdedItem item : SpigotModding.getRegisteredItems())
            {
                if (ingredient.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
                {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    //smithing
    @EventHandler
    public void onSmelt(PrepareSmithingEvent event)
    {
        SmithingInventory inventory = event.getInventory();
        ItemStack first = inventory.getItem(0);
        ItemStack second = inventory.getItem(1);
        //check if first stack has itemmeta
        if (first.hasItemMeta())
        {
            //check if stack is a modded item
            for (ModdedItem item : SpigotModding.getRegisteredItems())
            {
                if (first.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
                {
                    event.setResult(null);
                    return;
                }
            }
        }
        //check if second stack has itemmeta
        if (second.hasItemMeta())
        {
            //check if stack is a modded item
            for (ModdedItem item : SpigotModding.getRegisteredItems())
            {
                if (second.getItemMeta().getLore().get(0).equals(item.getItem().getItemMeta().getLore().get(0)))
                {
                    event.setResult(null);
                    return;
                }
            }
        }
    }
}

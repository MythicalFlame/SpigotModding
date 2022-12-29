package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ModdedItem
{
    private ItemStack item;
    //constructor
    public ModdedItem(Material material, String name)
    {
        item = new ItemStack(material);

        ItemMeta moddedItemMeta = item.getItemMeta();
        moddedItemMeta.setDisplayName(name);

        item.setItemMeta(moddedItemMeta);
    }
    //get/set
    public ItemStack getItem()
    {
        return item;
    }

    public void setItem(Material material, String name)
    {
        item = new ItemStack(material);

        ItemMeta moddedItemMeta = item.getItemMeta();
        moddedItemMeta.setDisplayName(name);

        item.setItemMeta(moddedItemMeta);
    }

    public void setItem(ItemStack item)
    {
        this.item = item;
    }
    //Events to override
    public void onLeftClick(){}
    public void onRightClick(){}
}

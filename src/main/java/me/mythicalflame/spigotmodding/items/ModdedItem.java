package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ModdedItem
{
    private final String NAMESPACE;
    private final ItemStack ITEM;
    //constructor
    public ModdedItem(String namespace, Material material, String name)
    {
        this.NAMESPACE = namespace;

        ItemStack constructorItemStack = new ItemStack(material);

        ItemMeta moddedItemMeta = constructorItemStack.getItemMeta();
        moddedItemMeta.setDisplayName(name);

        constructorItemStack.setItemMeta(moddedItemMeta);

        ITEM = constructorItemStack;
    }
    //get/set
    public String getNamespace()
    {
        return NAMESPACE;
    }

    public ItemStack getItem()
    {
        return ITEM;
    }

    //Events to override
    public void onLeftClick(){}
    public void onRightClick(){}
}

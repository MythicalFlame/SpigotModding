package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ModdedItem
{
    private final String NAMESPACE;
    private final String ID;
    private final Material MATERIAL;
    private final ItemStack ITEM;
    //constructor
    public ModdedItem(String namespace, String ID, Material material, String name)
    {
        this.NAMESPACE = namespace;
        this.ID = ID;
        this.MATERIAL = material;

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

    public String getID()
    {
        return ID;
    }

    public Material getMaterial()
    {
        return MATERIAL;
    }

    public ItemStack getItem()
    {
        return ITEM;
    }

    //Events to override
    public void onLeftClick(PlayerInteractEvent event){}
    public void onRightClick(PlayerInteractEvent event){}
}

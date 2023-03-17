package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ModdedConsumable extends ModdedItem
{
    //constructor without customModelData
    public ModdedConsumable(String namespace, String ID, Material material, String name/*, CustomRecipe[] recipes*/)
    {
        super(namespace, ID, material, name/*, recipes*/);
    }

    //constructor with customModelData
    public ModdedConsumable(String namespace, String ID, Material material, String name, /*CustomRecipe[] recipes,*/ int customModelData)
    {
        super(namespace, ID, material, name, /*recipes,*/ customModelData);
    }

    public void onConsume(PlayerItemConsumeEvent event){}
}

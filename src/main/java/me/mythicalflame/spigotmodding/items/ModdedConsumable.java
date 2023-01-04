package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ModdedConsumable extends ModdedItem
{
    //constructor without customModelData
    public ModdedConsumable(String namespace, String ID, Material material, String name)
    {
        super(namespace, ID, material, name);
    }

    //constructor with customModelData
    public ModdedConsumable(String namespace, String ID, Material material, String name, int customModelData)
    {
        super(namespace, ID, material, name, customModelData);
    }

    public void onConsume(PlayerItemConsumeEvent event){}
}

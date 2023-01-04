package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ModdedConsumable extends ModdedItem
{
    public ModdedConsumable(String namespace, String ID, Material material, String name)
    {
        super(namespace, ID, material, name);
    }

    public void onConsume(PlayerItemConsumeEvent event){}
}

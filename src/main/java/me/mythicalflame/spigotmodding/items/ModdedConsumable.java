package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.exceptions.NotConsumableException;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ModdedConsumable extends ModdedItem
{
    public ModdedConsumable(String namespace, String ID, Material material, String name, /*CustomRecipe[] recipes,*/ int customModelData)
    {
        super(namespace, ID, material, name, /*recipes,*/ customModelData);

        if (!material.isEdible())
        {
            throw new NotConsumableException("Attempted to initialize ModdedConsumable object with material that is not consumable! (" + material + ")");
        }
    }

    public void onConsume(PlayerItemConsumeEvent event){}
}

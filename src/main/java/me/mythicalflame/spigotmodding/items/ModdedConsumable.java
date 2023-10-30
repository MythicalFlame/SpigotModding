package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.exceptions.NotConsumableException;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.List;

public abstract class ModdedConsumable extends ModdedItem
{
    public ModdedConsumable(String namespace, String ID, Material material, String name, /*CustomRecipe[] recipes,*/ int customModelData, List<String> lore)
    {
        super(namespace, ID, material, name, customModelData, lore);

        if (!material.isEdible())
        {
            throw new NotConsumableException("Attempted to initialize ModdedConsumable object with material that is not consumable! (" + material + ")");
        }
    }

    public abstract void onConsume(PlayerItemConsumeEvent event);
}

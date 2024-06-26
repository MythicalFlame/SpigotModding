package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.exceptions.NotConsumableException;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.List;

public abstract class ModdedConsumable extends ModdedItem
{
    public ModdedConsumable(String namespace, String ID, Material material, String name, Integer customModelData, List<String> lore)
    {
        super(namespace, ID, material, name, customModelData, lore);

        if (!material.isEdible())
        {
            throw new NotConsumableException("Attempted to initialize a ModdedConsumable object with material that is not consumable! (" + material + ")");
        }
    }

    @SuppressWarnings({"EmptyMethod", "unused"})
    public void onConsume(PlayerItemConsumeEvent event) {}
}

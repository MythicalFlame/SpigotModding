package me.mythicalflame.netherreactor.items;

import me.mythicalflame.netherreactor.exceptions.NotConsumableException;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.List;

/**
 * This class represents a custom consumable.
 */
public abstract class ModdedConsumable extends ModdedItem
{
    /**
     * Constructs a ModdedConsumable object.
     *
     * @param namespace The non-null namespace that this item belongs to.
     * @param id The non-null ID of this item.
     * @param material The non-null consumable Material that this item is based off of.
     * @param name The nullable display name of this item.
     * @param customModelData The nullable custom model data value of this item.
     * @param lore The nullable lore of the item.
     *
     * @throws NotConsumableException If this constructor is called with a non-edible Material.
     */
    public ModdedConsumable(String namespace, String id, Material material, String name, Integer customModelData, List<String> lore)
    {
        super(namespace, id, material, name, customModelData, lore);

        if (!material.isEdible())
        {
            throw new NotConsumableException("Attempted to initialize a ModdedConsumable object with material that is not consumable! (" + material + ")");
        }
    }

    /**
     * This method is intended to be overridden and is called when a player is involved in a PlayerItemConsumeEvent.
     *
     * @param event The PlayerItemConsumeEvent.
     */
    public void onConsume(PlayerItemConsumeEvent event) {}
}

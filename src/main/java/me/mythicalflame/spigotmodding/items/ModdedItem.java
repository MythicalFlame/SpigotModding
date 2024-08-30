package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.utilities.EventType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a custom item.
 */
public abstract class ModdedItem
{
    /**
     * The namespace of an item. The full item's technical name is "namespace:ID".
     */
    private final String NAMESPACE;
    /**
     * The ID of the item. The full item's technical name is "namespace:ID".
     */
    private final String ID;
    /**
     * The material that the item is based off of. This may or may not have restrictions depending on if the item is a specialized type (e.g. food).
     */
    private final Material MATERIAL;
    /**
     * The display name of this item.
     */
    private final String DISPLAY_NAME;
    /**
     * The custom model data value of this item. If there is no custom model data value, this variable holds null.
     */
    private final Integer CUSTOM_MODEL_DATA;
    /**
     * The ItemStack representation of this item.
     */
    private final ItemStack ITEM;

    /**
     * Constructs a ModdedItem object.
     *
     * @param namespace The non-null namespace that this item belongs to.
     * @param id The non-null ID of this item.
     * @param material The non-null Material that this item is based off of.
     * @param name The nullable display name of this item.
     * @param customModelData The nullable custom model data value of this item.
     * @param lore The nullable lore of the item.
     */
    public ModdedItem(String namespace, String id, Material material, String name, Integer customModelData, List<String> lore)
    {
        if (namespace == null || id == null || material == null || material == Material.AIR)
        {
            throw new NullPointerException("Attempted to initialize a ModdedItem object with a null field!");
        }

        NAMESPACE = namespace.toLowerCase();
        ID = id.toLowerCase();
        MATERIAL = material;
        DISPLAY_NAME = name;
        CUSTOM_MODEL_DATA = customModelData;

        ItemStack constructorItemStack = new ItemStack(material);

        ItemMeta moddedItemMeta = constructorItemStack.getItemMeta();

        //Name without italics
        assert moddedItemMeta != null;
        moddedItemMeta.setDisplayName(ChatColor.RESET + name);

        //Lore
        ArrayList<String> moddedItemLore = new ArrayList<>();
        moddedItemLore.add(NAMESPACE + ":" + ID);
        if (lore != null)
        {
            moddedItemLore.addAll(lore);
        }
        moddedItemMeta.setLore(moddedItemLore);

        //CMD
        moddedItemMeta.setCustomModelData(customModelData);

        //PDC
        moddedItemMeta.getPersistentDataContainer().set(SpigotModding.getContentKey(), PersistentDataType.STRING, NAMESPACE + ":" + ID);

        constructorItemStack.setItemMeta(moddedItemMeta);

        ItemStack finalizedItem = finalizeItem(constructorItemStack);

        if (constructorItemStack.getType() != MATERIAL)
        {
            throw new UnsupportedOperationException("Overrides of ModdedItem#finalizeItem must not change the material of the ItemStack!");
        }

        ITEM = finalizedItem;
    }

    /**
     * This method is intended to be overridden by mod developers and is used to provide more control over the item.
     * <p><b>Overrides of this method must NOT change the material of the ItemStack.</b></p>
     *
     * @param stack The initial ItemStack created by the constructor with its specified parameters.
     * @return The finalized ItemStack representation of the item.
     */
    protected ItemStack finalizeItem(ItemStack stack) { return stack; }

    /**
     * @return The non-null namespace that this item belongs to.
     */
    public final String getNamespace() { return NAMESPACE; }

    /**
     * @return The non-null ID of this item.
     */
    public final String getID()
    {
        return ID;
    }

    /**
     * @return The non-null Material that this item is based off of.
     */
    public final Material getMaterial()
    {
        return MATERIAL;
    }

    /**
     * @return The nullable display name of this item.
     */
    public final String getDisplayName()
    {
        return DISPLAY_NAME;
    }

    /**
     * @return The nullable custom model data value of this item.
     */
    public final Integer getCustomModelData()
    {
        return CUSTOM_MODEL_DATA;
    }

    /**
     * @return Whether the item has custom model data or not.
     */
    public final boolean hasCustomModelData()
    {
        return CUSTOM_MODEL_DATA != null;
    }

    /**
     * @return A clone of the ItemStack representation of this item with an amount of 1.
     */
    public ItemStack getItem()
    {
        return ITEM.clone();
    }

    /**
     * @param amount The amount of items that should be in the ItemStack.
     * @return A clone of the ItemStack representation of this item with a variable amount.
     */
    public ItemStack getItem(int amount)
    {
        ItemStack get = ITEM.clone();
        get.setAmount(amount);
        return get;
    }

    /**
     * This method is intended to be overridden and is called every tick.
     *
     * @param player The player that has the item.
     * @param type The type of this event.
     */
    public void onTick(Player player, EventType type) {}

    /**
     * This method is intended to be overridden and is called when a player is associated with a PlayerInteractEvent.
     *
     * @param event The PlayerInteractEvent.
     * @param type The type of this event.
     */
    public void onInteract(PlayerInteractEvent event, EventType type) {}

    /**
     * This method is intended to be overridden and is called when a player is the killer in an EntityDeathEvent.
     *
     * @param event The EntityDeathEvent.
     * @param type The type of this event.
     */
    public void onKill(EntityDeathEvent event, EventType type) {}

    /**
     * This method is intended to be overridden and is called when a player is the damager in an EntityDamageByEntityEvent.
     *
     * @param event The EntityDamageByEntityEvent.
     * @param type The type of this event.
     */
    public void onAttack(EntityDamageByEntityEvent event, EventType type) {}

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof ModdedItem otherItem))
        {
            return false;
        }

        return NAMESPACE.equals(otherItem.NAMESPACE) && ID.equals(otherItem.ID);
    }

    //Source: https://stackoverflow.com/a/16377941
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[]{NAMESPACE, ID});
    }
}

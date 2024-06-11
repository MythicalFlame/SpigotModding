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
import java.util.List;

public abstract class ModdedItem
{
    private final String NAMESPACE;
    private final String ID;
    private final Material MATERIAL;
    private final ItemStack ITEM;

    @SuppressWarnings("ConstantConditions")
    public ModdedItem(String namespace, String id, Material material, String name, Integer customModelData, List<String> lore)
    {
        if (namespace == null || id == null || material == null || material == Material.AIR)
        {
            throw new NullPointerException("Attempted to initialize a ModdedItem object with a null field!");
        }

        NAMESPACE = namespace.toLowerCase();
        ID = id.toLowerCase();
        MATERIAL = material;

        ItemStack constructorItemStack = new ItemStack(material);

        ItemMeta moddedItemMeta = constructorItemStack.getItemMeta();

        //Name without italics
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

        ITEM = finalizeItem(constructorItemStack);
    }

    //mods can override this for custom behavior
    protected ItemStack finalizeItem(ItemStack stack) { return stack; }

    //getters
    public final String getNamespace() { return NAMESPACE; }

    public final String getID()
    {
        return ID;
    }

    public final Material getMaterial()
    {
        return MATERIAL;
    }

    public ItemStack getItem()
    {
        return ITEM.clone();
    }

    @SuppressWarnings("unused")
    public ItemStack getItem(int amount)
    {
        ItemStack get = ITEM.clone();
        get.setAmount(amount);
        return get;
    }

    //Events to override
    @SuppressWarnings({"EmptyMethod", "unused"})
    public void onTick(Player player) {}
    @SuppressWarnings({"EmptyMethod", "unused"})
    public void onInteract(PlayerInteractEvent event, EventType type) {}
    @SuppressWarnings({"EmptyMethod", "unused"})
    public void onKill(EntityDeathEvent event, EventType type) {}
    @SuppressWarnings({"EmptyMethod", "unused"})
    public void onAttack(EntityDamageByEntityEvent event, EventType type) {}
}

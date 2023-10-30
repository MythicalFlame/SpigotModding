package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.utilities.EventType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class ModdedItem
{
    private final String NAMESPACE;
    private final String ID;
    private final Material MATERIAL;
    private final ItemStack ITEM;
    private final int CUSTOM_MODEL_DATA;

    public ModdedItem(String namespace, String id, Material material, String name, int customModelData, List<String> lore)
    {
        NAMESPACE = namespace.toLowerCase();
        ID = id.toLowerCase();
        MATERIAL = material;
        CUSTOM_MODEL_DATA = customModelData;

        ItemStack constructorItemStack = new ItemStack(material);

        ItemMeta moddedItemMeta = constructorItemStack.getItemMeta();

        //Name without italics
        moddedItemMeta.setDisplayName(ChatColor.RESET + name);

        ArrayList<String> moddedItemLore = new ArrayList<>();
        moddedItemLore.add(NAMESPACE + ":" + ID);
        moddedItemLore.addAll(lore);
        moddedItemMeta.setLore(moddedItemLore);

        moddedItemMeta.setCustomModelData(CUSTOM_MODEL_DATA);

        constructorItemStack.setItemMeta(moddedItemMeta);

        ITEM = finalizeItem(constructorItemStack);
    }

    //mods can override this for custom behavior
    public ItemStack finalizeItem(ItemStack stack) { return stack; }

    //getters
    public String getNamespace() { return NAMESPACE; }

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

    /*public CustomRecipe[] getRecipes() { return RECIPES; }*/

    public int getCustomModelData()
    {
        return CUSTOM_MODEL_DATA;
    }

    //Events to override
    public abstract void onTick(Player player);
    public abstract void onInteract(PlayerInteractEvent event, EventType type);
    public abstract void onKill(EntityDeathEvent event, EventType type);
    public abstract void onAttack(EntityDamageByEntityEvent event, EventType type);
}

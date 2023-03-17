package me.mythicalflame.spigotmodding.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ModdedItem
{
    private final String NAMESPACE;
    private final String ID;
    private final Material MATERIAL;
    private final ItemStack ITEM;
    //private final VanillaInteractionOption VANILLA_INTERACTIONS;
    private final Integer CUSTOM_MODEL_DATA;
    /*private final CustomRecipe[] RECIPES;*/
    //constructors
    //without customModelData
    public ModdedItem(String namespace, String ID, Material material, String name/*, CustomRecipe[] recipes*/)
    {
        this.NAMESPACE = namespace;
        this.ID = ID;
        this.MATERIAL = material;
        /*RECIPES = recipes;*/
        this.CUSTOM_MODEL_DATA = null;

        ItemStack constructorItemStack = new ItemStack(material);

        ItemMeta moddedItemMeta = constructorItemStack.getItemMeta();
        //Name without italics
        moddedItemMeta.setDisplayName(ChatColor.RESET + name);
        /* Lore for ModdedItem
            First line - "namespace:id" (used to check if items are equivalent after given custom lore or renamed
            Second line - extra lore
         */
        ArrayList<String> moddedItemLore = new ArrayList<>();
        moddedItemLore.add(namespace.toLowerCase() + ":" + ID.toLowerCase());
        moddedItemMeta.setLore(moddedItemLore);

        moddedItemMeta.setCustomModelData(CUSTOM_MODEL_DATA);

        constructorItemStack.setItemMeta(moddedItemMeta);

        ITEM = constructorItemStack;
    }
    //with customModelData
    public ModdedItem(String namespace, String ID, Material material, String name, /*CustomRecipe[] recipes,*/ int customModelData)
    {
        this.NAMESPACE = namespace;
        this.ID = ID;
        this.MATERIAL = material;
        /*RECIPES = recipes;*/
        this.CUSTOM_MODEL_DATA = customModelData;

        ItemStack constructorItemStack = new ItemStack(material);

        ItemMeta moddedItemMeta = constructorItemStack.getItemMeta();
        //Name without italics
        moddedItemMeta.setDisplayName(ChatColor.RESET + name);
        /* Lore for ModdedItem
            First line - "namespace:id" (used to check if items are equivalent after given custom lore or renamed
            Second line - extra lore
        */
        ArrayList<String> moddedItemLore = new ArrayList<>();
        moddedItemLore.add(namespace.toLowerCase() + ":" + ID.toLowerCase());
        moddedItemMeta.setLore(moddedItemLore);

        moddedItemMeta.setCustomModelData(CUSTOM_MODEL_DATA);

        constructorItemStack.setItemMeta(moddedItemMeta);

        ITEM = constructorItemStack;
    }
    //get/set
    public String getNamespace()
    {
        return NAMESPACE;
    }

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

    public Integer getCustomModelData()
    {
        return CUSTOM_MODEL_DATA;
    }

    //Events to override
    public void onLeftClick(PlayerInteractEvent event){}
    public void onRightClick(PlayerInteractEvent event){}
    public void onAttack(EntityDamageByEntityEvent event){}
}

package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.exceptions.NotWearableException;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class ModdedArmorPiece extends ModdedItem
{
    private static final Material[] WEARABLE_VALUES = {Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, getNetheriteBoots(), getNetheriteLeggings(), getNetheriteChestplate(), getNetheriteHelmet(), Material.ELYTRA, Material.TURTLE_HELMET, Material.CARVED_PUMPKIN, Material.CREEPER_HEAD, Material.DRAGON_HEAD, Material.ZOMBIE_HEAD, Material.SKELETON_SKULL, Material.WITHER_SKELETON_SKULL, Material.PLAYER_HEAD, getPiglinHead()};

    private static final HashSet<Material> WEARABLES = new HashSet<>(Arrays.asList(WEARABLE_VALUES));

    public ModdedArmorPiece(String namespace, String ID, Material material, String name, Integer customModelData, List<String> lore)
    {
        super(namespace, ID, material, name, customModelData, lore);

        if (!WEARABLES.contains(material))
        {
            throw new NotWearableException("Attempted to initialize a ModdedArmorPiece object with material that is not wearable! (" + material + ")");
        }
    }

    private static Material getNetheriteHelmet()
    {
        try
        {
            //game version 1.16 or higher
            Class.forName("org.bukkit.Material.NETHERITE_HELMET");
            return Material.valueOf("NETHERITE_HELMET");
        }
        catch (ClassNotFoundException e)
        {
            //if the game version is not 1.16 or higher, this block of code will be run.
            //since Material.PLAYER_HEAD is already defined in the array, when the array is converted to a set, the duplicate Material.PLAYER_HEAD will be deleted.
            return Material.PLAYER_HEAD;
        }
    }

    private static Material getNetheriteChestplate()
    {
        try
        {
            //game version 1.16 or higher
            Class.forName("org.bukkit.Material.NETHERITE_CHESTPLATE");
            return Material.valueOf("NETHERITE_CHESTPLATE");
        }
        catch (ClassNotFoundException e)
        {
            //if the game version is not 1.16 or higher, this block of code will be run.
            //since Material.PLAYER_HEAD is already defined in the array, when the array is converted to a set, the duplicate Material.PLAYER_HEAD will be deleted.
            return Material.PLAYER_HEAD;
        }
    }

    private static Material getNetheriteLeggings()
    {
        try
        {
            //game version 1.16 or higher
            Class.forName("org.bukkit.Material.NETHERITE_LEGGINGS");
            return Material.valueOf("NETHERITE_LEGGINGS");
        }
        catch (ClassNotFoundException e)
        {
            //if the game version is not 1.16 or higher, this block of code will be run.
            //since Material.PLAYER_HEAD is already defined in the array, when the array is converted to a set, the duplicate Material.PLAYER_HEAD will be deleted.
            return Material.PLAYER_HEAD;
        }
    }

    private static Material getNetheriteBoots()
    {
        try
        {
            //game version 1.16 or higher
            Class.forName("org.bukkit.Material.NETHERITE_BOOTS");
            return Material.valueOf("NETHERITE_BOOTS");
        }
        catch (ClassNotFoundException e)
        {
            //if the game version is not 1.16 or higher, this block of code will be run.
            //since Material.PLAYER_HEAD is already defined in the array, when the array is converted to a set, the duplicate Material.PLAYER_HEAD will be deleted.
            return Material.PLAYER_HEAD;
        }
    }

    private static Material getPiglinHead()
    {
        try
        {
            //game version 1.20 or higher
            Class.forName("org.bukkit.Material.PIGLIN_HEAD");
            return Material.valueOf("PIGLIN_HEAD");
        }
        catch (ClassNotFoundException e)
        {
            //if the game version is not 1.20 or higher, this block of code will be run.
            //since Material.PLAYER_HEAD is already defined in the array, when the array is converted to a set, the duplicate Material.PLAYER_HEAD will be deleted.
            return Material.PLAYER_HEAD;
        }
    }
}

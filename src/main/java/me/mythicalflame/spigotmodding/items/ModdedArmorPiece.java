package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;

public class ModdedArmorPiece extends ModdedItem
{
    public ModdedArmorPiece(String namespace, String ID, Material material, String name)
    {
        super(namespace, ID, material, name);
    }

    //constructor with customModelData
    public ModdedArmorPiece(String namespace, String ID, Material material, String name, int customModelData)
    {
        super(namespace, ID, material, name, customModelData);
    }
}

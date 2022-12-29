package me.mythicalflame.spigotmodding.items;

import org.bukkit.Material;

public class ModdedItemTinSword extends ModdedItem
{
    public ModdedItemTinSword()
    {
        super(Material.STONE_SWORD, "Tin Sword");
    }
    //Events
    @Override
    public void onRightClick()
    {
        System.out.println("right clicked with tin sword");
    }
}

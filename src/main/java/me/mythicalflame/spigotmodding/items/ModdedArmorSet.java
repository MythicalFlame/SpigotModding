package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.utilities.ArmorSetEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;

public class ModdedArmorSet
{
    //TODO: allow armor pieces to have modified protection amounts
    //pieces should hold 4 moddedarmorpiece objects (null = no piece requirement for that slot). Index 0 indicates the helmet, index 1 the chestplate, index 2 the leggings, and index 3 the boots.
    private final ModdedArmorPiece[] pieces;
    private final ArmorSetEffect setEffects;

    public ModdedArmorSet(ModdedArmorPiece[] pieces, ArmorSetEffect setEffects)
    {
        this.setEffects = setEffects;
        this.pieces = pieces;
    }

    public ModdedArmorPiece[] getPieces()
    {
        return pieces;
    }

    public ArmorSetEffect getSetEffects()
    {
        return setEffects;
    }

    //classes cannot override this method, but in the future ArmorSetEffects will give you more behavior.
    public final void applyEffects(Player player)
    {
        for (PotionEffect effect : setEffects.getEffects())
        {
            player.addPotionEffect(effect);
        }
    }
}

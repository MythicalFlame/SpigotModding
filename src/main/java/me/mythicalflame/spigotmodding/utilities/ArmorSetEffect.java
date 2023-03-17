package me.mythicalflame.spigotmodding.utilities;

import org.bukkit.potion.PotionEffect;

public class ArmorSetEffect
{
    //TODO: allow custom effects when added
    private final PotionEffect[] effects;

    public ArmorSetEffect(PotionEffect[] effects)
    {
        this.effects = effects;
    }

    public PotionEffect[] getEffects()
    {
        return effects;
    }
}

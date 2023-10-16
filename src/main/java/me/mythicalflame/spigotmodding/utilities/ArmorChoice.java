package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.items.ModdedArmorPiece;

public class ArmorChoice
{
    private final ModdedArmorPiece[] armors;

    //Empty array - NO wearable accepted
    //null - ANY wearable accepted
    public ArmorChoice(ModdedArmorPiece[] armors)
    {
        this.armors = armors;
    }

    public ModdedArmorPiece[] getArmors() { return armors; }
}

package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.items.ModdedArmorPiece;

public record ArmorChoice(ModdedArmorPiece[] armors)
{
    //Empty array - NO wearable accepted
    //null - ANY wearable accepted
}

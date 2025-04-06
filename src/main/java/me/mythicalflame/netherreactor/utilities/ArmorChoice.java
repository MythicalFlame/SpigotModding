package me.mythicalflame.netherreactor.utilities;

import me.mythicalflame.netherreactor.items.ModdedArmorPiece;

/**
 * Represents possible armor piece choices to satisfy an armor set.
 *
 * @param armors An array of ModdedArmorPiece objects. The first element is the helmet, the second element is the chestplate, the third element is the leggings, the fourth element is the helmet. An empty array means no wearable is acceptable. A null element means any element is acceptable.
 */
public record ArmorChoice(ModdedArmorPiece[] armors)
{
    //Empty array - NO wearable accepted
    //null - ANY wearable accepted
}

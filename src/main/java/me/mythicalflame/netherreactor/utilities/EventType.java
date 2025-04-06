package me.mythicalflame.netherreactor.utilities;

/**
 * Represents where a custom item in a player's inventory is.
 */
public enum EventType
{
    /**
     * Used when an item is in a player's inventory and does not match any other enum option.
     */
    IN_INVENTORY,
    /**
     * Used when an item is in a player's off hand.
     */
    IN_OFF_HAND,
    /**
     * Used when an item is in a player's main hand.
     */
    IN_MAIN_HAND
}

package me.mythicalflame.spigotmodding.exceptions;

/**
 * This exception is thrown when a ModdedArmorPiece is attempted to be initialized with a non-wearable Material.
 */
public class NotWearableException extends RuntimeException
{
    public NotWearableException(String msg)
    {
        super(msg);
    }
}

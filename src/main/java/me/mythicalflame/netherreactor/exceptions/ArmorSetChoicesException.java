package me.mythicalflame.netherreactor.exceptions;

/**
 * This exception is thrown when a ModdedArmorSet is attempted to be initialized with an ArmorChoice array that does not have a length of 4.
 */
public class ArmorSetChoicesException extends RuntimeException
{
    public ArmorSetChoicesException(String msg)
    {
        super(msg);
    }
}

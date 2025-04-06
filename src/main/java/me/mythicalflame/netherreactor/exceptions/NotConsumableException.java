package me.mythicalflame.netherreactor.exceptions;

/**
 * This exception is thrown when a ModdedConsumable is attempted to be initialized with a Material that is not edible.
 */
public class NotConsumableException extends RuntimeException
{
    public NotConsumableException(String msg)
    {
        super(msg);
    }
}

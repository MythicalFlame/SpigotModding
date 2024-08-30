package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.exceptions.ArmorSetChoicesException;
import me.mythicalflame.spigotmodding.utilities.ArmorChoice;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * This class represents a custom armor set.
 */
public abstract class ModdedArmorSet
{
    //TODO allow armor pieces to have modified protection amounts (armor rewrite)
    /**
     * The ArmorChoices that apply to this set.
     */
    private final ArmorChoice[] choices;

    /**
     * Creates a ModdedArmorSet.
     *
     * @param choices The ArmorChoices that apply to this set.
     */
    public ModdedArmorSet(ArmorChoice[] choices)
    {
        if (choices.length != 4)
        {
            throw new ArmorSetChoicesException("Attempted to initialize a ModdedArmorSet object with an armor choice array that is length" + choices.length + "instead of length 4!");
        }

        this.choices = choices;
    }

    /**
     * @return This ModdedArmorSet's ArmorChoices.
     */
    public ArmorChoice[] getChoices()
    {
        return choices;
    }

    /**
     * This method is intended to be overridden and is called every tick.
     *
     * @param player The player wearing the armor set.
     */
    public void onTick(Player player) {}

    /**
     * This method is intended to be overriden and is called when a set wearer is associated with a PlayerInteractEvent.
     *
     * @param event The PlayerInteractEvent.
     */
    public void onInteract(PlayerInteractEvent event) {}

    /**
     * This method is intended to be overriden and is called when a set wearer is the killer in an EntityDeathEvent.
     *
     * @param event The EntityDeathEvent.
     */
    public void onKill(EntityDeathEvent event) {}

    /**
     * This method is intended to be overriden and is called when a set wearer is the attacker in an EntityDamageByEntityEvent.
     *
     * @param event The EntityDamageByEntityEvent
     */
    public void onAttack(EntityDamageByEntityEvent event) {}

    /**
     * This method is intended to be overriden and is called when a set wearer consumes an item.
     *
     * @param event The PlayerItemConsumeEvent.
     */
    public void onConsume(PlayerItemConsumeEvent event) {}
}
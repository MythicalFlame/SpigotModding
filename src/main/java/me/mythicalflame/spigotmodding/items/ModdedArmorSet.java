package me.mythicalflame.spigotmodding.items;

import me.mythicalflame.spigotmodding.exceptions.ArmorSetChoicesException;
import me.mythicalflame.spigotmodding.utilities.ArmorChoice;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public abstract class ModdedArmorSet
{
    //TODO: allow armor pieces to have modified protection amounts
    //pieces should hold 4 ArmorChoice objects. Index 0 indicates the helmet, index 1 the chestplate, index 2 the leggings, and index 3 the boots.
    private final ArmorChoice[] choices;

    public ModdedArmorSet(ArmorChoice[] choices)
    {
        if (choices.length != 4)
        {
            throw new ArmorSetChoicesException("Attempted to initialize ModdedArmorSet object with an armor choice array that is length" + choices.length + "instead of length 4!");
        }

        this.choices = choices;
    }

    public ArmorChoice[] getChoices()
    {
        return choices;
    }

    public abstract void onTick(Player player);
    public abstract void onInteract(PlayerInteractEvent event);
    public abstract void onKill(EntityDeathEvent event);
    public abstract void onAttack(EntityDamageByEntityEvent event);
    public abstract void onConsume(PlayerItemConsumeEvent event);
}
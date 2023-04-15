package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedArmorPiece;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmorTask extends BukkitRunnable
{
    private SpigotModding plugin;

    public ArmorTask(SpigotModding plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public void run()
    {
        for (ModdedArmorSet set : SpigotModding.getRegisteredArmorSets())
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                ItemStack playerHelmet = player.getInventory().getHelmet();
                boolean playerHelmetHasLore = (playerHelmet != null && playerHelmet.hasItemMeta() && playerHelmet.getItemMeta().hasLore());
                ItemStack playerChestplate = player.getInventory().getChestplate();
                boolean playerChestplateHasLore = (playerChestplate != null && playerChestplate.hasItemMeta() && playerChestplate.getItemMeta().hasLore());
                ItemStack playerLeggings = player.getInventory().getLeggings();
                boolean playerLeggingsHasLore = (playerLeggings != null && playerLeggings.hasItemMeta() && playerLeggings.getItemMeta().hasLore());
                ItemStack playerBoots = player.getInventory().getBoots();
                boolean playerBootsHasLore = (playerBoots != null && playerBoots.hasItemMeta() && playerBoots.getItemMeta().hasLore());

                //retrieve items from the set
                ModdedArmorPiece setHelmetPiece = set.getPieces()[0];
                ItemStack setHelmet = null;
                if (setHelmetPiece != null)
                {
                    setHelmet = set.getPieces()[0].getItem();
                }

                ModdedArmorPiece setChestplatePiece = set.getPieces()[1];
                ItemStack setChestplate = null;
                if (setChestplatePiece != null)
                {
                    setChestplate = set.getPieces()[1].getItem();
                }

                ModdedArmorPiece setLeggingsPiece = set.getPieces()[2];
                ItemStack setLeggings = null;
                if (setLeggingsPiece != null)
                {
                    setLeggings = set.getPieces()[2].getItem();
                }

                ModdedArmorPiece setBootsPiece = set.getPieces()[3];
                ItemStack setBoots = null;
                if (setBootsPiece != null)
                {
                    setBoots = set.getPieces()[3].getItem();
                }

                //assume player is wearing set and attempt to disprove the assumption
                if (!playerHelmetHasLore || !(playerHelmet.getItemMeta().getLore().get(0).equals(setHelmet.getItemMeta().getLore().get(0))))
                {
                    continue;
                }

                if (!playerChestplateHasLore || !(playerChestplate.getItemMeta().getLore().get(0).equals(setChestplate.getItemMeta().getLore().get(0))))
                {
                    continue;
                }

                if (!playerLeggingsHasLore || !(playerLeggings.getItemMeta().getLore().get(0).equals(setLeggings.getItemMeta().getLore().get(0))))
                {
                    continue;
                }

                if (!playerBootsHasLore || !(playerBoots.getItemMeta().getLore().get(0).equals(setBoots.getItemMeta().getLore().get(0))))
                {
                    continue;
                }

                set.applyEffects(player);
            }
        }
    }
}

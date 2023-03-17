package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.SpigotModding;
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
                //TODO: add lore checking while ensuring efficiency
                ItemStack playerHelmet = player.getInventory().getHelmet();
                ItemStack playerChestplate = player.getInventory().getChestplate();
                ItemStack playerLeggings = player.getInventory().getLeggings();
                ItemStack playerBoots = player.getInventory().getBoots();
                ItemStack setHelmet = set.getPieces()[0].getItem();
                ItemStack setChestplate = set.getPieces()[1].getItem();
                ItemStack setLeggings = set.getPieces()[2].getItem();
                ItemStack setBoots = set.getPieces()[3].getItem();

                //assume player is wearing set and attempt to disprove the assumption
                if (setHelmet != null)
                {
                    if (playerHelmet == null || !playerHelmet.equals(setHelmet))
                    {
                        continue;
                    }
                }

                if (setChestplate != null)
                {
                    if (playerChestplate == null || !playerChestplate.equals(setChestplate))
                    {
                        continue;
                    }
                }

                if (setLeggings != null)
                {
                    if (playerLeggings == null || !playerLeggings.equals(setLeggings))
                    {
                        continue;
                    }
                }

                if (setBoots != null)
                {
                    if (playerBoots == null || !playerBoots.equals(setBoots))
                    {
                        continue;
                    }
                }

                set.applyEffects(player);
            }
        }
    }
}

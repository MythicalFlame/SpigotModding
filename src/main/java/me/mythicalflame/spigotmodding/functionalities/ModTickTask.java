package me.mythicalflame.spigotmodding.functionalities;

import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.SpigotModdingAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ModTickTask extends BukkitRunnable
{
    private static final ArrayList<ModdedItem> items = new ArrayList<>();
    private static final ArrayList<ModdedArmorSet> armorSets = new ArrayList<>();

    @Override
    public void run()
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            for (ItemStack stack : player.getInventory().getContents())
            {
                ModdedItem item = SpigotModdingAPI.getModdedItem(stack, items);

                if (item != null)
                {
                    item.onTick(player);
                }
            }

            for (ModdedArmorSet set : armorSets)
            {
                if (SpigotModdingAPI.isWearingSet(player, set))
                {
                    set.onTick(player);
                }
            }
        }
    }

    public static void addArmor(ModdedArmorSet set)
    {
        armorSets.add(set);
    }

    public static void addItem(ModdedItem item)
    {
        items.add(item);
    }
}

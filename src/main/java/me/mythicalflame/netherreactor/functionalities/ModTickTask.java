package me.mythicalflame.netherreactor.functionalities;

import me.mythicalflame.netherreactor.items.ModdedArmorSet;
import me.mythicalflame.netherreactor.items.ModdedItem;
import me.mythicalflame.netherreactor.utilities.EventType;
import me.mythicalflame.netherreactor.utilities.NetherReactorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Objects;

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
                ModdedItem item = NetherReactorAPI.getModdedItem(stack, items);

                if (item != null)
                {
                    EventType type;

                    if (Objects.equals(stack, player.getInventory().getItemInMainHand()))
                    {
                        type = EventType.IN_MAIN_HAND;
                    }
                    else if (Objects.equals(stack, player.getInventory().getItemInOffHand()))
                    {
                        type = EventType.IN_OFF_HAND;
                    }
                    else
                    {
                        type = EventType.IN_INVENTORY;
                    }

                    item.onTick(player, type);
                }
            }

            for (ModdedArmorSet set : armorSets)
            {
                if (NetherReactorAPI.isWearingSet(player, set))
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

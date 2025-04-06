package me.mythicalflame.netherreactor.functionalities;

import me.mythicalflame.netherreactor.items.ModdedArmorSet;
import me.mythicalflame.netherreactor.items.ModdedItem;
import me.mythicalflame.netherreactor.utilities.EventType;
import me.mythicalflame.netherreactor.utilities.NetherReactorAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

public class EntityDamageByEntityEventWatcher implements Listener
{
    private static final ArrayList<ModdedItem> items = new ArrayList<>();
    private static final ArrayList<ModdedArmorSet> armorSets = new ArrayList<>();

    @EventHandler
    public void damageWatcher(EntityDamageByEntityEvent event)
    {
        if (event.getDamager() instanceof Player)
        {
            checkArmor(event);
            checkInventory(event);
        }
    }

    private void checkArmor(EntityDamageByEntityEvent event)
    {
        for (ModdedArmorSet set : armorSets)
        {
            if (NetherReactorAPI.isWearingSet((Player) event.getDamager(), set))
            {
                set.onAttack(event);
            }
        }
    }

    private void checkInventory(EntityDamageByEntityEvent event)
    {
        Player player = (Player) event.getDamager();

        for (ItemStack stack : player.getInventory().getContents())
        {
            ModdedItem item = NetherReactorAPI.getModdedItem(stack, items);

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

            if (item != null)
            {
                item.onAttack(event, type);
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

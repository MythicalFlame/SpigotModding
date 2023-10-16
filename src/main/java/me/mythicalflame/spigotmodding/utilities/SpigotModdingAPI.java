package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedArmorPiece;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SpigotModdingAPI
{
    private static final ArmorChoice emptyArmorChoice = new ArmorChoice(new ModdedArmorPiece[]{});

    public static boolean isWearingSet(Player player, ModdedArmorSet set)
    {
        //checks (choice == null -> any accepted, choice == [] -> empty slot accepted only)
        for (byte i = 0; i < 4; i++)
        {
            ArmorChoice choice = set.getChoices()[i];

            if (choice != null)
            {
                ItemStack playerArmor = null;

                switch (i)
                {
                    case 0:
                        playerArmor = player.getInventory().getHelmet();
                        break;
                    case 1:
                        playerArmor = player.getInventory().getChestplate();
                        break;
                    case 2:
                        playerArmor = player.getInventory().getLeggings();
                        break;
                    case 3:
                        playerArmor = player.getInventory().getBoots();
                        break;
                }

                if (choice.equals(emptyArmorChoice))
                {
                    if (playerArmor != null)
                    {
                        return false;
                    }
                }
                else
                {
                    if (playerArmor == null || !playerArmor.hasItemMeta() || !playerArmor.getItemMeta().hasCustomModelData())
                    {
                        return false;
                    }
                    else
                    {
                        boolean pieceFound = false;

                        for (ModdedArmorPiece piece : choice.getArmors())
                        {
                            if (piece.getItem().getItemMeta().getCustomModelData() == playerArmor.getItemMeta().getCustomModelData())
                            {
                                pieceFound = true;
                                break;
                            }
                        }

                        if (!pieceFound)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    //search with namespace and ID within the set of all items
    public static ModdedItem getModdedItem(String namespace, String ID)
    {
        for (Mod mod : SpigotModding.getRegisteredMods())
        {
            for (ModdedItem item : mod.getRegisteredItems())
            {
                if (item.getNamespace().equalsIgnoreCase(namespace) && item.getID().equalsIgnoreCase(ID))
                {
                    return item;
                }
            }
        }

        return null;
    }

    //search with itemstack within the set of all items
    public static ModdedItem getModdedItem(ItemStack stack)
    {
        if (stack == null || !stack.hasItemMeta() || !stack.getItemMeta().hasCustomModelData())
        {
            return null;
        }

        for (Mod mod : SpigotModding.getRegisteredMods())
        {
            for (ModdedItem item : mod.getRegisteredItems())
            {
                if (stack.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())
                {
                    return item;
                }
            }
        }

        return null;
    }

    //search with itemstack within a set of items
    public static ModdedItem getModdedItem(ItemStack stack, List<ModdedItem> list)
    {
        if (stack == null || !stack.hasItemMeta() || !stack.getItemMeta().hasCustomModelData())
        {
            return null;
        }

        for (ModdedItem item : list)
        {
            if (stack.getItemMeta().getCustomModelData() == item.getItem().getItemMeta().getCustomModelData())
            {
                return item;
            }
        }

        return null;
    }

    public static Mod getMod(String namespace)
    {
        for (Mod m : SpigotModding.getRegisteredMods())
        {
            if (m.getNamespace().equalsIgnoreCase(namespace))
            {
                return m;
            }
        }

        return null;
    }
}

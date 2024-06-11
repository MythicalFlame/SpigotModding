package me.mythicalflame.spigotmodding.utilities;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedArmorPiece;
import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class SpigotModdingAPI
{
    private static final ArmorChoice emptyArmorChoice = new ArmorChoice(new ModdedArmorPiece[]{});

    public static boolean isWearingSet(Player player, ModdedArmorSet set)
    {
        //checks (choice == null -> any accepted, choice == [] -> empty slot accepted only)
        for (int i = 0; i < 4; i++)
        {
            ArmorChoice choice = set.getChoices()[i];

            if (choice != null)
            {
                ItemStack playerArmor = switch (i)
                {
                    case 0 -> player.getInventory().getHelmet();
                    case 1 -> player.getInventory().getChestplate();
                    case 2 -> player.getInventory().getLeggings();
                    case 3 -> player.getInventory().getBoots();
                    default -> null;
                };

                if (choice.equals(emptyArmorChoice))
                {
                    if (playerArmor != null)
                    {
                        return false;
                    }
                }
                else
                {
                    if (playerArmor == null)
                    {
                        return false;
                    }
                    else
                    {
                        ModdedItem pieceFound = SpigotModdingAPI.getModdedItem(playerArmor, Arrays.asList(choice.armors()));

                        if (pieceFound == null)
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
            if (namespace.equalsIgnoreCase(mod.getNamespace()))
            {
                for (ModdedItem item : mod.getRegisteredItems())
                {
                    if (item.getNamespace().equalsIgnoreCase(namespace) && item.getID().equalsIgnoreCase(ID))
                    {
                        return item;
                    }
                }

                return null;
            }
        }

        return null;
    }

    //search with itemstack within the set of all items
    @SuppressWarnings({"ConstantConditions", "unused"})
    public static ModdedItem getModdedItem(ItemStack stack)
    {
        if (stack == null || stack.getType() == Material.AIR || !stack.getItemMeta().getPersistentDataContainer().has(SpigotModding.getContentKey(), PersistentDataType.STRING))
        {
            return null;
        }

        String stackContent = stack.getItemMeta().getPersistentDataContainer().get(SpigotModding.getContentKey(), PersistentDataType.STRING);

        for (Mod mod : SpigotModding.getRegisteredMods())
        {
            for (ModdedItem item : mod.getRegisteredItems())
            {
                if (stackContent.equals(item.getItem().getItemMeta().getPersistentDataContainer().get(SpigotModding.getContentKey(), PersistentDataType.STRING)))
                {
                    return item;
                }
            }
        }

        return null;
    }

    //search with itemstack within a set of items
    @SuppressWarnings("ConstantConditions")
    public static ModdedItem getModdedItem(ItemStack stack, List<ModdedItem> list)
    {
        if (stack == null || stack.getType() == Material.AIR || !stack.getItemMeta().getPersistentDataContainer().has(SpigotModding.getContentKey(), PersistentDataType.STRING))
        {
            return null;
        }

        String stackContent = stack.getItemMeta().getPersistentDataContainer().get(SpigotModding.getContentKey(), PersistentDataType.STRING);

        for (ModdedItem item : list)
        {
            if (stackContent.equals(item.getItem().getItemMeta().getPersistentDataContainer().get(SpigotModding.getContentKey(), PersistentDataType.STRING)))
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

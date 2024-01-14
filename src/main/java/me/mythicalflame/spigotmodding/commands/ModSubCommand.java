package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.items.ModdedArmorSet;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.Mod;
import me.mythicalflame.spigotmodding.utilities.SpigotModdingAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ModSubCommand
{
    public static void modSubCommand(CommandSender sender, String[] args)
    {
        if (!sender.hasPermission("spigotmodding.commandspigotmodding.mod"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding mod command!");
            return;
        }

        if (args.length < 2)
        {
            sender.sendMessage(ChatColor.RED + "/spigotmodding mod <modNamespace>");
            return;
        }

        Mod mod = SpigotModdingAPI.getMod(args[1]);

        if (mod == null)
        {
            sender.sendMessage(ChatColor.RED + "Could not find mod with namespace \"" + args[1] + "\"");
            return;
        }

        StringBuilder result = new StringBuilder(ChatColor.GOLD + "SpigotModding Mod Inspection Results:\n" + ChatColor.RESET + "Name: " + mod.getDisplayName() + "\nNamespace: " + mod.getNamespace() + "\nRegistered Items: ");

        if (mod.getRegisteredItems().size() == 0)
        {
            result.append("None");
        }
        else
        {
            for (short i = 0; i < mod.getRegisteredItems().size(); i++)
            {
                ModdedItem item = mod.getRegisteredItems().get(i);

                result.append(item.getNamespace()).append(":").append(item.getID());

                if (i < mod.getRegisteredItems().size() - 1)
                {
                    result.append(", ");
                }
            }
        }

        result.append("\nRegistered Armor Sets: ");

        if (mod.getRegisteredArmor().size() == 0)
        {
            result.append("None");
        }
        else
        {
            for (short i = 0; i < mod.getRegisteredArmor().size(); i++)
            {
                ModdedArmorSet set = mod.getRegisteredArmor().get(i);

                result.append(set.getClass().getSimpleName());

                if (i < mod.getRegisteredArmor().size() - 1)
                {
                    result.append(", ");
                }
            }
        }

        sender.sendMessage(result.toString());
    }
}

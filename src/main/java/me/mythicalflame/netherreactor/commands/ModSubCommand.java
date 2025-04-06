package me.mythicalflame.netherreactor.commands;

import me.mythicalflame.netherreactor.items.ModdedArmorSet;
import me.mythicalflame.netherreactor.items.ModdedItem;
import me.mythicalflame.netherreactor.utilities.Mod;
import me.mythicalflame.netherreactor.utilities.NetherReactorAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ModSubCommand
{
    public static void modSubCommand(CommandSender sender, String[] args)
    {
        if (!sender.hasPermission("netherreactor.commandnetherreactor.mod"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the netherreactor mod command!");
            return;
        }

        if (args.length < 2)
        {
            sender.sendMessage(ChatColor.RED + "/netherreactor mod <modNamespace>");
            return;
        }

        Mod mod = NetherReactorAPI.getMod(args[1]);

        if (mod == null)
        {
            sender.sendMessage(ChatColor.RED + "Could not find mod with namespace \"" + args[1] + "\"");
            return;
        }

        StringBuilder result = new StringBuilder(ChatColor.GOLD + "NetherReactor Mod Inspection Results:\n" + ChatColor.RESET + "Name: " + mod.getDisplayName() + "\nNamespace: " + mod.getNamespace() + "\nRegistered Items: ");

        if (mod.getRegisteredItems().isEmpty())
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

        if (mod.getRegisteredArmor().isEmpty())
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

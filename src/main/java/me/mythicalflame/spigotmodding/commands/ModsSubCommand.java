package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.utilities.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ModsSubCommand
{
    public static void modsSubCommand(CommandSender sender)
    {
        if (!sender.hasPermission("spigotmodding.commandspigotmodding.mods"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding mods command!");
            return;
        }

        StringBuilder result = new StringBuilder(ChatColor.GOLD + "SpigotModding Registered Mods:\n" + ChatColor.RESET);
        for (short i = 0; i < SpigotModding.getRegisteredMods().size(); i++)
        {
            Mod mod = SpigotModding.getRegisteredMods().get(i);
            result.append(mod.getDisplayName()).append(" (").append(mod.getNamespace()).append(")");

            if (i < SpigotModding.getRegisteredMods().size() - 1)
            {
                result.append(", ");
            }
        }

        sender.sendMessage(result.toString());
    }
}

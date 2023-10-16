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

        String result = ChatColor.GOLD + "SpigotModding Registered Mods:\n" + ChatColor.RESET;
        for (short i = 0; i < SpigotModding.getRegisteredMods().size(); i++)
        {
            Mod mod = SpigotModding.getRegisteredMods().get(i);
            result += mod.getDisplayName() + " (" + mod.getNamespace() + ")";

            if (i < SpigotModding.getRegisteredMods().size() - 1)
            {
                result += ", ";
            }
        }

        sender.sendMessage(result);
    }
}

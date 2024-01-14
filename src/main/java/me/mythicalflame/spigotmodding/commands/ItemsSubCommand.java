package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ItemsSubCommand
{
    public static void itemsSubCommand(CommandSender sender)
    {
        if (!sender.hasPermission("spigotmodding.commandspigotmodding.items"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding items command!");
            return;
        }

        StringBuilder result = new StringBuilder(ChatColor.GOLD + "SpigotModding Registered Items:\n" + ChatColor.RESET);

        for (int i = 0; i < SpigotModding.getRegisteredMods().size(); i++)
        {
            Mod mod = SpigotModding.getRegisteredMods().get(i);

            for (int j = 0; j < mod.getRegisteredItems().size(); j++)
            {
                ModdedItem item = mod.getRegisteredItems().get(j);
                result.append(item.getNamespace()).append(":").append(item.getID());

                if (i < SpigotModding.getRegisteredMods().size() - 1 || j < mod.getRegisteredItems().size() - 1)
                {
                    result.append(", ");
                }
            }
        }

        sender.sendMessage(result.toString());
    }
}

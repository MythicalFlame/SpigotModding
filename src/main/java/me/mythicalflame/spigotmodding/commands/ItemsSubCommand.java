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

        String result = ChatColor.GOLD + "SpigotModding Registered Items:\n" + ChatColor.RESET;

        for (short i = 0; i < SpigotModding.getRegisteredMods().size(); i++)
        {
            Mod mod = SpigotModding.getRegisteredMods().get(i);

            for (short j = 0; j < mod.getRegisteredItems().size(); j++)
            {
                ModdedItem item = mod.getRegisteredItems().get(j);
                result += item.getNamespace() + ":" + item.getID();

                if (i < SpigotModding.getRegisteredMods().size() - 1 || j < mod.getRegisteredItems().size())
                {
                    result += ", ";
                }
            }
        }

        sender.sendMessage(result);
    }
}

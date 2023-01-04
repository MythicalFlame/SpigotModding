package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpigotModding implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;

        if (args.length == 0)
        {
            helpMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("give"))
        {
            if (!player.hasPermission("spigotmodding.commandspigotmodding.give"))
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding give command!");
                return true;
            }

            if (args.length <= 2)
            {
                sender.sendMessage(ChatColor.DARK_GREEN + "/spigotmodding give <itemNamespace> <itemID>");
                return true;
            }
            //check namespace existence
            boolean namespaceExists = false;
            for (ModdedItem item : SpigotModding.registeredItems)
            {
                if (args[1].equalsIgnoreCase(item.getNamespace()))
                {
                    namespaceExists = true;
                    break;
                }
            }

            if (namespaceExists)
            {
                ModdedItem receivingItem = null;
                //check if item with namespace exists
                boolean exactItemExists = false;
                for (ModdedItem item : SpigotModding.registeredItems)
                {
                    if (args[1].equalsIgnoreCase(item.getNamespace()) && args[2].equalsIgnoreCase(item.getID()))
                    {
                        receivingItem = item;
                        exactItemExists = true;
                        break;
                    }
                }

                if (exactItemExists)
                {
                    //give requested item
                    player.getInventory().addItem(receivingItem.getItem());
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "Could not find item " + args[1] + ":" + args[2] + "!");
                    sender.sendMessage(ChatColor.DARK_GREEN + "/spigotmodding give <itemNamespace> <itemID>");
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "Could not find namespace " + args[1] + "!");
                sender.sendMessage(ChatColor.DARK_GREEN + "/spigotmodding give <itemNamespace> <itemID>");
            }
        }
        else if (args[0].equalsIgnoreCase("items"))
        {
            if (!player.hasPermission("spigotmodding.commandspigotmodding.items"))
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding items command!");
                return true;
            }

            String result = "List of registered items: ";
            for (ModdedItem item : SpigotModding.registeredItems)
            {
                result += item.getNamespace() + ":" + item.getID() + ", ";
            }

            sender.sendMessage(result);
        }
        else
        {
            helpMessage(sender);
        }
        return true;
    }

    public void helpMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.GOLD + "SpigotModding commands help:\n" + ChatColor.DARK_GREEN + "/spigotmodding help " + ChatColor.RESET + "Displays this message.\n" + ChatColor.DARK_GREEN + "/spigotmodding give " + ChatColor.RESET + "Gives custom features.\n" + ChatColor.DARK_GREEN + "/spigotmodding items " + ChatColor.RESET + "Displays all registered items.\n");
    }
}

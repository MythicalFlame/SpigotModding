package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSpigotModding implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 0)
        {
            helpMessage(sender);
            return true;
        }

        switch (args[0].toLowerCase())
        {
            case "items":
                ItemsSubCommand.itemsSubCommand(sender);
                break;
            case "item":
                ItemSubCommand.itemSubCommand(sender, args);
                break;
            case "give":
                GiveSubCommand.giveSubCommand(sender, args);
                break;
            case "mod":
                ModSubCommand.modSubCommand(sender, args);
                break;
            case "mods":
                ModsSubCommand.modsSubCommand(sender);
                break;
            default:
                helpMessage(sender);
                break;
        }

        return true;
    }

    //helper methods
    public void helpMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.GOLD + "SpigotModding commands help:\n" + ChatColor.DARK_GREEN + "/spigotmodding help " + ChatColor.RESET + "Displays this message.\n" + ChatColor.DARK_GREEN + "/spigotmodding give " + ChatColor.RESET + "Gives custom features.\n" + ChatColor.DARK_GREEN + "/spigotmodding items " + ChatColor.RESET + "Displays all registered items.\n" + ChatColor.DARK_GREEN + "/spigotmodding item " + ChatColor.RESET + "Displays information about an item.\n");
    }
}

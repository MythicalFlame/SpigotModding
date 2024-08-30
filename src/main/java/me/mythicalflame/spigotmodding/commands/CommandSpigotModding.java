package me.mythicalflame.spigotmodding.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public class CommandSpigotModding implements CommandExecutor
{
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args)
    {
        if (args.length == 0)
        {
            helpMessage(sender);
            return true;
        }

        switch (args[0].toLowerCase())
        {
            case "items" -> ItemsSubCommand.itemsSubCommand(sender);
            case "item" -> ItemSubCommand.itemSubCommand(sender, args);
            case "give" -> GiveSubCommand.giveSubCommand(sender, args);
            case "mod" -> ModSubCommand.modSubCommand(sender, args);
            case "mods" -> ModsSubCommand.modsSubCommand(sender);
            default -> helpMessage(sender);
        }

        return true;
    }

    //helper methods
    public void helpMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.GOLD + "SpigotModding commands help:\n" + ChatColor.DARK_GREEN + "/spigotmodding help " + ChatColor.RESET + "Displays this message.\n" + ChatColor.DARK_GREEN + "/spigotmodding give " + ChatColor.RESET + "Gives custom features.\n" + ChatColor.DARK_GREEN + "/spigotmodding items " + ChatColor.RESET + "Displays all registered items.\n" + ChatColor.DARK_GREEN + "/spigotmodding item " + ChatColor.RESET + "Displays information about an item.\n");
    }
}

package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.utilities.SpigotModdingAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ItemSubCommand
{
    public static void itemSubCommand(CommandSender sender, String[] args)
    {
        if (!sender.hasPermission("spigotmodding.commandspigotmodding.item"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding item command!");
            return;
        }

        if (args.length < 2)
        {
            sender.sendMessage(ChatColor.RED + "/spigotmodding item <itemNamespace:itemID>");
            return;
        }

        //index 0 = namespace, index 1 = ID
        String[] itemEntered = args[1].split(":");

        //ensure that itemEntered has 2 elements (correct input from command sender)
        if (itemEntered.length != 2)
        {
            sender.sendMessage(ChatColor.RED + "/spigotmodding item <itemNamespace:itemID>");
            return;
        }

        ModdedItem itemFound = SpigotModdingAPI.getModdedItem(itemEntered[0], itemEntered[1]);

        if (itemFound == null)
        {
            sender.sendMessage(ChatColor.RED + "Could not find item \"" + itemEntered[0].toLowerCase() + ":" + itemEntered[1].toLowerCase() + "\"");
        }
        else
        {
            int customModelData = itemFound.hasCustomModelData() ? itemFound.getCustomModelData() : -1;
            sender.sendMessage(ChatColor.GOLD + "SpigotModding Item Inspection Results:\n" + ChatColor.RESET + "Mod : " + Objects.requireNonNull(SpigotModdingAPI.getMod(itemFound)).getDisplayName() + "\nName: " + itemFound.getDisplayName() + "\nNamespace: " + itemFound.getNamespace() + "\nID: " + itemFound.getID() + "\nMaterial: " + itemFound.getMaterial() + "\nCustom Model Data: " + (customModelData == -1 ? "None" : customModelData));
        }
    }
}

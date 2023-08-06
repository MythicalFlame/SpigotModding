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
                itemsCommand(sender);
                break;
            case "item":
                itemCommand(sender, args);
                break;
            case "give":
                giveCommand(sender, args);
                break;
            default:
                helpMessage(sender);
                break;
        }

        return true;
    }

    public void itemsCommand(CommandSender sender)
    {
        if (!sender.hasPermission("spigotmodding.commandspigotmodding.items"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding items command!");
            return;
        }

        String result = ChatColor.GOLD + "SpigotModding Registered Items:\n" + ChatColor.RESET;
        for (ModdedItem item : SpigotModding.getRegisteredItems())
        {
            result += item.getNamespace() + ":" + item.getID() + ", ";
        }

        sender.sendMessage(result);
    }

    //subcommand methods
    public void itemCommand(CommandSender sender, String[] args)
    {
        if (args.length < 2)
        {
            sender.sendMessage(ChatColor.RED + "/spigotmodding item <itemNamespace:itemID>");
            return;
        }

        if (!sender.hasPermission("spigotmodding.commandspigotmodding.item"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding item command!");
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

        ModdedItem itemFound = SpigotModding.getRegisteredItem(itemEntered[0], itemEntered[1]);

        if (itemFound == null)
        {
            sender.sendMessage(ChatColor.RED + "Could not find item \"" + itemEntered[0].toLowerCase() + ":" + itemEntered[1].toLowerCase() + "\"");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "SpigotModding Item Inspection Results:\n" + ChatColor.RESET + "Name: " + itemFound.getItem().getItemMeta().getDisplayName() + "\nNamespace: " + itemFound.getNamespace() + "\nID: " + itemFound.getID() + "\nMaterial: " + itemFound.getMaterial() + "\nCustom Model Data: " + itemFound.getCustomModelData());
        }
    }

    public void giveCommand(CommandSender sender, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "Only players may use the spigotmodding give command!");
            return;
        }

        Player commandUser = (Player) sender;

        if (!commandUser.hasPermission("spigotmodding.commandspigotmodding.give"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding give command!");
            return;
        }

        if (args.length < 3)
        {
            sender.sendMessage(ChatColor.DARK_GREEN + "/spigotmodding give <playerName> <itemNamespace:itemID> [amount]");
            return;
        }

        /*get player*/
        Player receiver = Bukkit.getPlayer(args[1]);

        if (receiver == null)
        {
            sender.sendMessage(ChatColor.RED + "Could not find online player \"" + args[1] + "\"");
            return;
        }

        /*get item*/
        //index 0 = namespace, index 1 = ID
        String[] itemEntered = args[2].split(":");

        //ensure that itemEntered has 2 elements (correct input from command sender)
        if (itemEntered.length != 2)
        {
            sender.sendMessage(ChatColor.RED + "/spigotmodding give <playerName> <itemNamespace:itemID> [amount]");
            return;
        }

        ModdedItem itemFound = SpigotModding.getRegisteredItem(itemEntered[0], itemEntered[1]);

        if (itemFound == null)
        {
            sender.sendMessage(ChatColor.RED + "Could not find item \"" + itemEntered[0].toLowerCase() + ":" + itemEntered[1].toLowerCase() + "\"");
            return;
        }

        /*give item*/
        int itemAmount = 1;
        if (args.length > 3)
        {
            itemAmount = Integer.parseInt(args[3]);
        }

        ItemStack giveItem = itemFound.getItem();
        giveItem.setAmount(itemAmount);

        receiver.getInventory().addItem(giveItem);

        sender.sendMessage("Successfully added " + itemAmount + " of item \"" + itemFound.getItem().getItemMeta().getDisplayName() + "\" to player \"" + args[1] + "\"'s inventory.");
    }

    //helper methods
    public void helpMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.GOLD + "SpigotModding commands help:\n" + ChatColor.DARK_GREEN + "/spigotmodding help " + ChatColor.RESET + "Displays this message.\n" + ChatColor.DARK_GREEN + "/spigotmodding give " + ChatColor.RESET + "Gives custom features.\n" + ChatColor.DARK_GREEN + "/spigotmodding items " + ChatColor.RESET + "Displays all registered items.\n" + ChatColor.DARK_GREEN + "/spigotmodding item " + ChatColor.RESET + "Displays information about an item.\n");
    }
}

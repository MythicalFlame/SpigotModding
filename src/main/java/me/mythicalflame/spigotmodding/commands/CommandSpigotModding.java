package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.SpigotModding;
import me.mythicalflame.spigotmodding.items.ModdedItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class CommandSpigotModding implements CommandExecutor
{
    ModdedItem commandItem;
    String[] values;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;

        if (args.length == 0)
        {
            helpMessage(sender);
            return true;
        }

        String enteredSubCommand = args[0].toLowerCase();

        switch (enteredSubCommand)
        {
            //give command
            case "give":
                Player commandGiver = null;
                if (!player.hasPermission("spigotmodding.commandspigotmodding.give"))
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding give command!");
                    return true;
                }

                if (args.length <= 2)
                {
                    sender.sendMessage(ChatColor.DARK_GREEN + "/spigotmodding give <playerName> <itemNamespace:itemID> [amount]");
                    return true;
                }
                //attempt to find player
                try
                {
                    commandGiver = getServer().getPlayer(args[1]);
                }
                catch (Exception e)
                {
                    sender.sendMessage(ChatColor.RED + "[SpigotModding] could not find online player \"" + args[1] + "\". Ensure that command arguments are valid.");
                }
                //attempt to find item
                values = parseItem(args[2]);

                try
                {
                    commandItem = findItem(args[2]);
                }
                catch (Exception e)
                {
                    sender.sendMessage(ChatColor.RED + "[SpigotModding] could not find item \"" + values[0] + ":" + values[1] + "\". Ensure that command arguments are valid.");
                }
                //figure out if amount is entered
                int itemAmount = 1;
                if (args.length > 3)
                {
                    itemAmount = Integer.parseInt(args[3]);
                }
                //attempt to give item
                try
                {
                    ItemStack giveItem = commandItem.getItem();
                    giveItem.setAmount(itemAmount);

                    commandGiver.getInventory().addItem(giveItem);

                    sender.sendMessage("[SpigotModding] successfully added " + itemAmount + " of item \"" + commandItem.getNamespace() + ":" + commandItem.getID() + "\" to player \"" + args[1] + "\"'s inventory.");
                }
                catch (Exception e)
                {
                    sender.sendMessage(ChatColor.RED + "[SpigotModding] could not add item \"" + commandItem.getNamespace() + ":" + commandItem.getID() + "\" to online player \"" + args[1] + "\"'s inventory. Ensure that command arguments are valid.");
                }
                return true;
            //items command
            case "items":
                if (!player.hasPermission("spigotmodding.commandspigotmodding.items"))
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding items command!");
                    return true;
                }

                String result = ChatColor.GOLD + "SpigotModding Registered Items:\n" + ChatColor.RESET;
                for (ModdedItem item : SpigotModding.getRegisteredItems())
                {
                    result += item.getNamespace() + ":" + item.getID() + ", ";
                }

                sender.sendMessage(result);
                return true;
            //item command
            case "item":
                if (!player.hasPermission("spigotmodding.commandspigotmodding.item"))
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use the spigotmodding item command!");
                    return true;
                }

                if (args.length < 2)
                {
                    sender.sendMessage(ChatColor.DARK_GREEN + "/spigotmodding item <itemNamespace:itemID>");
                    return true;
                }

                values = parseItem(args[1]);

                try
                {
                    commandItem = findItem(args[1]);
                    sender.sendMessage(ChatColor.GOLD + "SpigotModding Item Inspection Results:\n" + ChatColor.RESET + "Namespace: " + commandItem.getNamespace() + "\nID: " + commandItem.getID() + "\nMaterial: " + commandItem.getMaterial().toString() + "\nCustom Model Data: " + commandItem.getCustomModelData());
                }
                catch (Exception e)
                {
                    sender.sendMessage(ChatColor.RED + "[SpigotModding] could not find item \"" + values[0] + ":" + values[1] + "\". Ensure that command arguments are valid.");
                }

                return true;
            default:
                helpMessage(sender);
                return true;
        }
    }
    //other methods
    public ModdedItem findItem(String together)
    {
        String[] values = parseItem(together);
        return SpigotModding.getRegisteredItem(values[0], values[1]);
    }

    public String[] parseItem(String together)
    {
        ArrayList<Character> namespaceList = new ArrayList<Character>();
        ArrayList<Character> IDList = new ArrayList<Character>();
        //phase 0 - counting namespace; phase 1 - counting id
        int phase = 0;
        String namespace = "";
        String ID = "";

        for (char character : together.toCharArray())
        {
            if (character != ':')
            {
                //namespace
                if (phase == 0)
                {
                    namespaceList.add(character);
                }
                else //id
                {
                    IDList.add(character);
                }
            }
            else //colon used = namespace done, now on ID
            {
                phase++;
            }
        }
        //build namespace string
        for (char character : namespaceList)
        {
            namespace += character;
        }

        //build ID string
        for (char character : IDList)
        {
            ID += character;
        }

        String[] toReturn = {namespace, ID};
        return toReturn;
    }

    public void helpMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.GOLD + "SpigotModding commands help:\n" + ChatColor.DARK_GREEN + "/spigotmodding help " + ChatColor.RESET + "Displays this message.\n" + ChatColor.DARK_GREEN + "/spigotmodding give " + ChatColor.RESET + "Gives custom features.\n" + ChatColor.DARK_GREEN + "/spigotmodding items " + ChatColor.RESET + "Displays all registered items.\n" + ChatColor.DARK_GREEN + "/spigotmodding item " + ChatColor.RESET + "Displays information about an item.\n");
    }
}

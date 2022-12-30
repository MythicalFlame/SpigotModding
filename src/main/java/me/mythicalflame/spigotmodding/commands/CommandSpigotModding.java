package me.mythicalflame.spigotmodding.commands;

import me.mythicalflame.spigotmodding.items.ModdedItem;
import me.mythicalflame.spigotmodding.items.ModdedItemTinSword;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpigotModding implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        //TODO: give command
        /*Player player = (Player) sender;
        ModdedItem testItem = new ModdedItemTinSword();
        player.getInventory().addItem(testItem.getItem());*/
        return true;
    }
}

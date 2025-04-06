package me.mythicalflame.netherreactor.commands;

import me.mythicalflame.netherreactor.NetherReactorModLoader;
import me.mythicalflame.netherreactor.items.ModdedItem;
import me.mythicalflame.netherreactor.utilities.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ItemsSubCommand
{
    public static void itemsSubCommand(CommandSender sender)
    {
        if (!sender.hasPermission("netherreactor.commandnetherreactor.items"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the netherreactor items command!");
            return;
        }

        StringBuilder result = new StringBuilder(ChatColor.GOLD + "NetherReactor Registered Items:\n" + ChatColor.RESET);

        for (int i = 0; i < NetherReactorModLoader.getRegisteredMods().size(); i++)
        {
            Mod mod = NetherReactorModLoader.getRegisteredMods().get(i);

            for (int j = 0; j < mod.getRegisteredItems().size(); j++)
            {
                ModdedItem item = mod.getRegisteredItems().get(j);
                result.append(item.getNamespace()).append(":").append(item.getID());

                if (i < NetherReactorModLoader.getRegisteredMods().size() - 1 || j < mod.getRegisteredItems().size() - 1)
                {
                    result.append(", ");
                }
            }
        }

        sender.sendMessage(result.toString());
    }
}

package me.mythicalflame.netherreactor.commands;

import me.mythicalflame.netherreactor.NetherReactorModLoader;
import me.mythicalflame.netherreactor.utilities.Mod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ModsSubCommand
{
    public static void modsSubCommand(CommandSender sender)
    {
        if (!sender.hasPermission("netherreactor.commandnetherreactor.mods"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use the netherreactor mods command!");
            return;
        }

        StringBuilder result = new StringBuilder(ChatColor.GOLD + "NetherReactor Registered Mods:\n" + ChatColor.RESET);
        for (short i = 0; i < NetherReactorModLoader.getRegisteredMods().size(); i++)
        {
            Mod mod = NetherReactorModLoader.getRegisteredMods().get(i);
            result.append(mod.getDisplayName()).append(" (").append(mod.getNamespace()).append(")");

            if (i < NetherReactorModLoader.getRegisteredMods().size() - 1)
            {
                result.append(", ");
            }
        }

        sender.sendMessage(result.toString());
    }
}

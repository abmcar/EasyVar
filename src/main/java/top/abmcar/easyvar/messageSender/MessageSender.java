package top.abmcar.easyvar.messageSender;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.EasyVar;

public class MessageSender
{
    private static Plugin plugin = EasyVar.getPlugin();

    public static void sendMessage(CommandSender commandSender, String message)
    {
        message = message.replace("&", "§");
        if (commandSender instanceof Player)
        {
            Player nowPlayer = ((Player) commandSender).getPlayer();
            nowPlayer.sendMessage(message);
        } else
        {
            message = message.replace("§7", "");
            message = message.replace("§a", "");
            message = message.replace("§f", "");
            message = message.replace("§b", "");
            message = message.replace("\n", "");
            plugin.getLogger().info(message);
        }
    }
}

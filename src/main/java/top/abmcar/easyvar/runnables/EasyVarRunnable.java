package top.abmcar.easyvar.runnables;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.abmcar.easyvar.EasyVar;
import top.abmcar.easyvar.config.ConfigData;
import top.abmcar.easyvar.script.ScriptManager;

import java.util.Collection;
import java.util.List;

public class EasyVarRunnable extends BukkitRunnable
{
    @Override
    public void run()
    {
        if (!ConfigData.INSTANCE.runTask) return;
       Collection<? extends  Player>  Players = Bukkit.getOnlinePlayers();
       for (Player player : Players)
       {
           ScriptManager.runScript(ConfigData.INSTANCE.runPlayerScriptName, player);
       }
    }

}

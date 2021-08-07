package top.abmcar.easyvar.commandListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.EasyVar;
import top.abmcar.easyvar.messageSender.MessageSender;
import top.abmcar.easyvar.script.ScriptManager;
import top.abmcar.easyvar.var.GlobalVar;
import top.abmcar.easyvar.var.PlayerVar;

import java.util.HashMap;

public class displayCommandListener implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (strings.length == 4)
        {
            if (strings[0].equalsIgnoreCase("set"))
            {
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    return true;
                }
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowKey = strings[2];
                    Integer nowVal = Integer.parseInt(strings[3]);
                    GlobalVar.setValue(nowKey, nowVal);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("add"))
            {
                if (!commandSender.hasPermission("ev.var.set"))
                    return true;
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowKey = strings[2];
                    Integer nowVal = Integer.parseInt(strings[3]);
                    EasyVar.getVarManager().addGlobalValue(nowKey, nowVal);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("reduce"))
            {
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    return true;
                }
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowKey = strings[2];
                    Integer nowVal = Integer.parseInt(strings[3]);
                    EasyVar.getVarManager().reduceGlobalValue(nowKey, nowVal);
                    return true;
                }
            }
        }
        if (strings.length == 5)
        {
            if (strings[0].equalsIgnoreCase("set"))
            {
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    return true;
                }
                if (strings[1].equalsIgnoreCase("player"))
                {
                    String nowPlayerName = strings[2];
                    if (nowPlayerName.equalsIgnoreCase("<player>") && commandSender instanceof Player)
                    {
                        Player player = (Player) commandSender;
                        nowPlayerName = player.getName();
                    }
                    String nowVarName = strings[3];
                    Integer nowVal = Integer.parseInt(strings[4]);
                    EasyVar.getVarManager().setPlayerValue(nowPlayerName, nowVarName, nowVal);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("add"))
            {
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    return true;
                }
                if (strings[1].equalsIgnoreCase("player"))
                {
                    String nowPlayerName = strings[2];
                    if (nowPlayerName.equalsIgnoreCase("<player>") && commandSender instanceof Player)
                    {
                        Player player = (Player) commandSender;
                        nowPlayerName = player.getName();
                    }
                    String nowVarName = strings[3];
                    Integer nowVal = Integer.parseInt(strings[4]);
                    EasyVar.getVarManager().addPlayerValue(nowPlayerName, nowVarName, nowVal);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("reduce"))
            {
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    return true;
                }
                if (strings[1].equalsIgnoreCase("player"))
                {
                    String nowPlayerName = strings[2];
                    if (nowPlayerName.equalsIgnoreCase("<player>") && commandSender instanceof Player)
                    {
                        Player player = (Player) commandSender;
                        nowPlayerName = player.getName();
                    }
                    String nowVarName = strings[3];
                    Integer nowVal = Integer.parseInt(strings[4]);
                    EasyVar.getVarManager().reducePlayerValue(nowPlayerName, nowVarName, nowVal);
                    return true;
                }
            }
        }
        if (strings.length == 7)
        {
            if (!strings[0].equalsIgnoreCase("script"))
                return false;
            if (strings[1].equalsIgnoreCase("set"))
            {
                if (!commandSender.hasPermission("ev.script.set"))
                {
                    return true;
                }
                String scriptName = strings[2];
                String varType = strings[3];
                String varName = strings[4];
                Integer varValue = Integer.parseInt(strings[5]);
                boolean isOp = strings[6].equalsIgnoreCase("true");
                ScriptManager.createScript(scriptName, varType, varName, varValue, isOp);
                return true;
            }
        }
        if (strings.length == 3)
        {
            if (!strings[0].equalsIgnoreCase("script"))
                return false;
            if (strings[1].equalsIgnoreCase("run"))
            {
                String scriptName = strings[2];
                ScriptManager.runScript(scriptName, commandSender);
                return true;
            }
        }
        return false;
    }
}

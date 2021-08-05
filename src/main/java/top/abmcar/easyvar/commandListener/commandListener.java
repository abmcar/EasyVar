package top.abmcar.easyvar.commandListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.EasyVar;
import top.abmcar.easyvar.messageSender.MessageSender;
import top.abmcar.easyvar.var.GlobalVar;
import top.abmcar.easyvar.var.PlayerVar;

import javax.print.DocFlavor;
import java.util.HashMap;

public class commandListener implements CommandExecutor
{
    private static Plugin plugin = EasyVar.getPlugin();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (strings.length == 1)
        {
            if (strings[0].equalsIgnoreCase("help"))
            {
                String nowMessage1 = "&7使用&b/ev show player <playerName>&f查看该玩家拥有变量,playerName为玩家名字\n";
                String nowMessage2 = "&7使用&b/ev global&f来查看全局变量\n";
                String nowMessage3 = "&7使用&b/ev set player <playerName> <varName> <varValue>&f修改玩家变量\n";
                String nowMessage4 = "&7使用&b/ev set global <varName> <varValue>&f修改全局变量\n";
                String nowMessage5 = "&7使用&b/ev add player <playerName> <varName> <varValue>&f增加玩家变量\n";
                String nowMessage6 = "&7使用&b/ev add global <varName> <varValue>&f增加全局变量\n";
                String nowMessage7 = "&7使用&b/ev reduce player <playerName> <varName> <varValue>&f减少玩家变量\n";
                String nowMessage8 = "&7使用&b/ev reduce global <varName> <varValue>&f减少全局变量\n";
                MessageSender.sendMessage(commandSender, nowMessage1);
                MessageSender.sendMessage(commandSender, nowMessage2);
                MessageSender.sendMessage(commandSender, nowMessage3);
                MessageSender.sendMessage(commandSender, nowMessage4);
                MessageSender.sendMessage(commandSender, nowMessage5);
                MessageSender.sendMessage(commandSender, nowMessage6);
                MessageSender.sendMessage(commandSender, nowMessage7);
                MessageSender.sendMessage(commandSender, nowMessage8);
                return true;
            }
        }
        if (strings.length == 2)
        {
            if (strings[0].equals("show"))
            {
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowMessage0 = "&a全局变量:";
                    MessageSender.sendMessage(commandSender,nowMessage0);
                    HashMap<String, Integer> globalVars = GlobalVar.getMap();
                    for (String it : globalVars.keySet())
                    {
                        String nowMessage = it + " : " + globalVars.get(it) + "\n";
                        MessageSender.sendMessage(commandSender, nowMessage);
                    }
                    return true;
                }
                String nowString1 = "&7使用&b/ev show player <playerName>&f查看该玩家拥有变量,playerName为玩家名字\n";
                String nowString2 = "&7使用&b/ev show global&f来查看全局变量\n";
                MessageSender.sendMessage(commandSender, nowString1);
                MessageSender.sendMessage(commandSender, nowString1);
                return true;
            }
        }
        if (strings.length == 3)
        {
            if (strings[0].equalsIgnoreCase("show"))
            {
                if (strings[1].equalsIgnoreCase("player"))
                {
                    PlayerVar nowPlayerVar = EasyVar.getVarManager().getPlayerVars(strings[2]);
                    if (nowPlayerVar == null)
                    {
                        String nowString = "玩家" + strings[2] + "当前没有变量";
                        MessageSender.sendMessage(commandSender, nowString);
                    } else
                    {
                        String nowMessage0 = "玩家 " + strings[2] + "变量：";
                        MessageSender.sendMessage(commandSender,nowMessage0);
                        HashMap<String, Integer> playerVars = nowPlayerVar.getMap();
                        for (String it : playerVars.keySet())
                        {
                            String nowMessage = it + " " + playerVars.get(it) + "\n";
                            MessageSender.sendMessage(commandSender, nowMessage);
                        }
                    }
                    return true;
                }
            }
        }
        if (strings.length == 4)
        {
            if (strings[0].equalsIgnoreCase("set"))
            {
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowKey = strings[2];
                    Integer nowVal = Integer.parseInt(strings[3]);
                    GlobalVar.setValue(nowKey, nowVal);
                    String nowMessage = "全局变量 " + nowKey + " 设置为 " + nowVal;
                    MessageSender.sendMessage(commandSender, nowMessage);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("add"))
            {
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowKey = strings[2];
                    Integer nowVal = Integer.parseInt(strings[3]);
                    Integer oldVal = EasyVar.getVarManager().getGlobalValue(nowKey);
                    Integer newVal = nowVal + oldVal;
                    String nowMessage = "全局变量 " + nowKey + " : " + oldVal + " -> " + newVal;
                    EasyVar.getVarManager().addGlobalValue(nowKey, nowVal);
                    MessageSender.sendMessage(commandSender, nowMessage);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("reduce"))
            {
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowKey = strings[2];
                    Integer nowVal = Integer.parseInt(strings[3]);
                    Integer oldVal = EasyVar.getVarManager().getGlobalValue(nowKey);
                    Integer newVal = oldVal - nowVal;
                    String nowMessage = "全局变量 " + nowKey + " : " + oldVal + " -> " + newVal;
                    EasyVar.getVarManager().reduceGlobalValue(nowKey, nowVal);
                    MessageSender.sendMessage(commandSender, nowMessage);
                    return true;
                }
            }
        }
        if (strings.length == 5)
        {
            if (strings[0].equalsIgnoreCase("set"))
            {
                if (strings[1].equalsIgnoreCase("player"))
                {
                    String nowPlayerName = strings[2];
                    String nowVarName = strings[3];
                    Integer nowVal = Integer.parseInt(strings[4]);
                    EasyVar.getVarManager().setPlayerValue(nowPlayerName, nowVarName, nowVal);
                    String nowMessage = "玩家 " + nowPlayerName + " 的变量 " + nowVarName + " 设置为" + nowVal + "\n";
                    MessageSender.sendMessage(commandSender, nowMessage);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("add"))
            {
                if (strings[1].equalsIgnoreCase("player"))
                {
                    String nowPlayerName = strings[2];
                    String nowVarName = strings[3];
                    Integer nowVal = Integer.parseInt(strings[4]);
                    Integer oldVal = EasyVar.getVarManager().getPlayerValue(nowPlayerName, nowVarName);
                    Integer newVal = oldVal + nowVal;
                    EasyVar.getVarManager().addPlayerValue(nowPlayerName, nowVarName, nowVal);
                    String nowMessage = "玩家 " + nowPlayerName + " 的变量 " + nowVarName + " : " + oldVal + " -> " + newVal + "\n";
                    MessageSender.sendMessage(commandSender, nowMessage);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("reduce"))
            {
                if (strings[1].equalsIgnoreCase("player"))
                {
                    String nowPlayerName = strings[2];
                    String nowVarName = strings[3];
                    Integer nowVal = Integer.parseInt(strings[4]);
                    Integer oldVal = EasyVar.getVarManager().getPlayerValue(nowPlayerName, nowVarName);
                    Integer newVal = oldVal - nowVal;
                    EasyVar.getVarManager().reducePlayerValue(nowPlayerName, nowVarName, nowVal);
                    String nowMessage = "玩家 " + nowPlayerName + " 的变量 " + nowVarName + " : " + oldVal + " -> " + newVal + "\n";
                    MessageSender.sendMessage(commandSender, nowMessage);
                    return true;
                }
            }
        }
        return false;
    }
}

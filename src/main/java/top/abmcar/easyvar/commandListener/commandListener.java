package top.abmcar.easyvar.commandListener;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.EasyVar;
import top.abmcar.easyvar.messageSender.MessageSender;
import top.abmcar.easyvar.script.ScriptManager;
import top.abmcar.easyvar.stringUtil.StringUtil;
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
                String nowMessage2 = "&7使用&b/ev show global&f来查看全局变量\n";
                String nowMessage3 = "&7使用&b/ev set player <playerName> <varName> <varValue>&f修改玩家变量\n";
                String nowMessage4 = "&7使用&b/ev set global <varName> <varValue>&f修改全局变量\n";
                String nowMessage5 = "&7使用&b/ev add player <playerName> <varName> <varValue>&f增加玩家变量\n";
                String nowMessage6 = "&7使用&b/ev add global <varName> <varValue>&f增加全局变量\n";
                String nowMessage7 = "&7使用&b/ev reduce player <playerName> <varName> <varValue>&f减少玩家变量\n";
                String nowMessage8 = "&7使用&b/ev reduce global <varName> <varValue>&f减少全局变量\n";
                String nowMessage9 = "&7使用&b/ev script set <scriptName> <varType> <varName> <varValue> <isOp> &f创建或设置脚本\n";
                String nowMessage10 = "&7例如&b/ev script set script1 global test 10 true &f创建script1当全局变量test大于等于10时以OP执行命令\n";
                String nowMessage11 = "&7例如&b/ev script set script2 Abmcar test1 10 false &f创建script2当玩家Abmcar的变量test1大于等于10时以自身权限执行命令\n";
                String nowMessage12 = "&7使用&b/ev script add <scriptName> <command>&f添加脚本执行命令\n";
                String nowMessage13 = "&7使用&b/ev script delete <scriptName> <command>&f删除脚本执行命令\n";
                String nowMessage14 = "&7命令中的空格请用_代替,例如&b/ev script delete testScript ev_show_global\n";
                String nowMessage15 = "&7使用&b/ev script show <scriptName> &f查看脚本执行命令\n";
                String nowMessage16 = "&7使用&b/ev script run <scriptName> &f执行脚本\n";
                String nowMessage17 = "&7<player>为占位符,表示执行命令的玩家的名字\n";
                String nowMessage18 = "&7例如&b/ev add player <player> testVar 1 &f给自己的testVar变量加1\n";
                MessageSender.sendMessage(commandSender, nowMessage1);
                MessageSender.sendMessage(commandSender, nowMessage2);
                MessageSender.sendMessage(commandSender, nowMessage3);
                MessageSender.sendMessage(commandSender, nowMessage4);
                MessageSender.sendMessage(commandSender, nowMessage5);
                MessageSender.sendMessage(commandSender, nowMessage6);
                MessageSender.sendMessage(commandSender, nowMessage7);
                MessageSender.sendMessage(commandSender, nowMessage8);
                MessageSender.sendMessage(commandSender, nowMessage9);
                MessageSender.sendMessage(commandSender, nowMessage10);
                MessageSender.sendMessage(commandSender, nowMessage11);
                MessageSender.sendMessage(commandSender, nowMessage12);
                MessageSender.sendMessage(commandSender, nowMessage13);
                MessageSender.sendMessage(commandSender, nowMessage14);
                MessageSender.sendMessage(commandSender, nowMessage15);
                MessageSender.sendMessage(commandSender, nowMessage16);
                MessageSender.sendMessage(commandSender, nowMessage17);
                MessageSender.sendMessage(commandSender, nowMessage18);
                return true;
            }
        }
        if (strings.length == 2)
        {
            if (strings[0].equals("show"))
            {
                if (!commandSender.hasPermission("ev.var.show"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
                if (strings[1].equalsIgnoreCase("global"))
                {
                    String nowMessage0 = "&a全局变量:";
                    MessageSender.sendMessage(commandSender, nowMessage0);
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
                if (!commandSender.hasPermission("ev.var.show"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
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
                        MessageSender.sendMessage(commandSender, nowMessage0);
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
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
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
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
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
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
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
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
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
                    String nowMessage = "玩家 " + nowPlayerName + " 的变量 " + nowVarName + " 设置为" + nowVal + "\n";
                    MessageSender.sendMessage(commandSender, nowMessage);
                    return true;
                }
            }
            if (strings[0].equalsIgnoreCase("add"))
            {
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
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
                if (!commandSender.hasPermission("ev.var.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
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
                    Integer oldVal = EasyVar.getVarManager().getPlayerValue(nowPlayerName, nowVarName);
                    Integer newVal = oldVal - nowVal;
                    EasyVar.getVarManager().reducePlayerValue(nowPlayerName, nowVarName, nowVal);
                    String nowMessage = "玩家 " + nowPlayerName + " 的变量 " + nowVarName + " : " + oldVal + " -> " + newVal + "\n";
                    MessageSender.sendMessage(commandSender, nowMessage);
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
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
                String scriptName = strings[2];
                String varType = strings[3];
                String varName = strings[4];
                Integer varValue = Integer.parseInt(strings[5]);
                boolean isOp = false;
                if (strings[6].equalsIgnoreCase("true"))
                    isOp = true;
                ScriptManager.createScript(scriptName, varType, varName, varValue, isOp);
                String nowString = scriptName + "创建成功, \n" + "当 " + varType + "的变量" + varName + " 大于等于" + varValue.toString() + "时以" + (isOp ? "OP" : "玩家") + "权限执行命令.";
                MessageSender.sendMessage(commandSender, nowString);
                return true;
            }
        }
        if (strings.length == 4)
        {
            if (!strings[0].equalsIgnoreCase("script"))
                return false;
            if (strings[1].equalsIgnoreCase("add"))
            {
                if (!commandSender.hasPermission("ev.script.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
                String scriptName = strings[2];
                String nowCommand = strings[3];
                if (ScriptManager.addScriptCommand(scriptName, nowCommand))
                {
                    String nowString = "已向 " + scriptName + " 添加命令： " + ScriptManager.getString(nowCommand);
                    MessageSender.sendMessage(commandSender, nowString);
                } else
                {
                    String nowString = "添加失败:无效的脚本名称.";
                    MessageSender.sendMessage(commandSender, nowString);
                }
                return true;
            }

            if (strings[1].equalsIgnoreCase("delete"))
            {
                if (!commandSender.hasPermission("ev.script.set"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
                String scriptName = strings[2];
                String nowCommand = strings[3];
                if (ScriptManager.deleteCommand(scriptName, nowCommand))
                {
                    String nowString = "已向 " + scriptName + " 删除命令： " + ScriptManager.getString(nowCommand);
                    MessageSender.sendMessage(commandSender, nowString);
                } else
                {
                    String nowString = "删除失败:无效的脚本名称或者脚本没有该命令.";
                    MessageSender.sendMessage(commandSender, nowString);
                }
                return true;
            }
        }
        if (strings.length == 3)
        {

            if (!strings[0].equalsIgnoreCase("script"))
                return false;
            if (strings[1].equalsIgnoreCase("show"))
            {
                if (!commandSender.hasPermission("ev.script.show"))
                {
                    String nowString = "权限不足";
                    MessageSender.sendMessage(commandSender, nowString);
                    return true;
                }
                String scriptName = strings[2];
                ScriptManager.showCommands(commandSender, scriptName);
                return true;
            }
            if (strings[1].equalsIgnoreCase("run"))
            {
                String scriptName = strings[2];
                if (ScriptManager.runScript(scriptName, commandSender))
                {
                    String nowString = "执行成功.";
                    MessageSender.sendMessage(commandSender, nowString);
                } else
                {
                    String nowString = "执行失败:不符合条件或其他原因.";
                    MessageSender.sendMessage(commandSender, nowString);
                }
                return true;
            }
        }
        return false;
    }
}
//ec script add <>
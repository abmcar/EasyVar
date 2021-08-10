package top.abmcar.easyvar.script;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.abmcar.easyvar.messageSender.MessageSender;

import java.util.List;

public class ScriptManager
{
    public static String getString(String string)
    {
        return string.replace("_", " ");
    }

    public static boolean runScript(String scriptName, CommandSender commandSender)
    {
        Script script = new Script(scriptName);
        boolean ok;
        if (commandSender instanceof Player && script.getVarType().equalsIgnoreCase("<player>"))
        {
            Player player = (Player) commandSender;
            ok = script.run(player.getName());
        }else
            ok = script.run();
        if (ok)
            script.executeCommand(commandSender);
        return ok;
    }

    public static void createScript(String scriptName, String varType, String varName, Integer varValue, boolean isOp)
    {
        Script script = new Script(scriptName);
        script.setVarType(varType);
        script.setVarName(varName);
        script.setRequireVal(varValue);
        script.setOp(isOp);
        script.saveFile();
    }

    public static boolean addScriptCommand(String scriptName, String command)
    {
        command = getString(command);
        Script script = new Script(scriptName);
        if (script.isNewScript())
        {
            return false;
        }
        script.addCommand(command);
        script.saveFile();
        return true;
    }

    public static void showCommands(CommandSender commandSender, String scriptName)
    {
        Script script = new Script(scriptName);
        if (script.isNewScript())
        {
            String nowString = "无效脚本名称,此脚本确实执行条件或不存在.";
            MessageSender.sendMessage(commandSender, nowString);
            return;
        }
        List<String> nowCommands = script.getCommands();
        if (nowCommands.size() == 0)
        {
            String nowString = "此脚本暂无执行命令.";
            MessageSender.sendMessage(commandSender, nowString);
        } else
        {
            String nowString = scriptName + " 执行命令如下：";
            MessageSender.sendMessage(commandSender, nowString);
            for (String nowCommand : nowCommands)
            {
                MessageSender.sendMessage(commandSender, nowCommand);
            }
        }
    }

    public static boolean deleteCommand(String scriptName, String command)
    {
        command = getString(command);
        Script script = new Script(scriptName);
        if (script.isNewScript())
            return false;
        return script.deleteCommand(command);
//        script.saveFile();
    }
}

package top.abmcar.easyvar.script;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import top.abmcar.easyvar.EasyVar;
import top.abmcar.easyvar.config.Config;
import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.config.ConfigUtil;
import top.abmcar.easyvar.stringUtil.StringUtil;
import top.abmcar.easyvar.var.PlayerVar;

import java.util.ArrayList;
import java.util.List;

public class Script
{
    private Config config;
    private String scriptName;
    private String configName;
    private boolean isOp;
    private List<String> commands = null;
    private String varType;
    private String varName;
    private Integer requireVal;
    private EasyVar plugin;
    private boolean newScript = true;

    private void setScriptName(String scriptName)
    {
        this.configName = scriptName + ".yml";
    }

    private void loadFile()
    {
        config = ConfigUtil.loadScriptConfig(plugin, configName);
        YamlConfiguration nowConfig = config.getConfigYaml();
        commands = nowConfig.getStringList("commands");
        requireVal = nowConfig.getInt("requireValue");
        varType = nowConfig.getString("varType");
        varName = nowConfig.getString("varName");
        isOp = nowConfig.getBoolean("isOp");
        if (varType == null)
            newScript = true;
        else if (varType.length() > 0)
            newScript = false;

    }

    public boolean isNewScript()
    {
        return newScript;
    }

    public void setNewScript()
    {
        newScript = false;
    }

    public void executeCommand(CommandSender commandSender)
    {
        if (commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if (isOp)
            {
                boolean playerOp = player.isOp();
                if (!playerOp)
                    player.setOp(true);
                for (String nowCommand : commands)
                {
                    nowCommand = StringUtil.Instance.getString(nowCommand,player);
                    player.performCommand(nowCommand);
                }
                if (!playerOp)
                    player.setOp(false);
            } else
            {
                for (String nowCommand : commands)
                {
                    nowCommand = StringUtil.Instance.getString(nowCommand,player);
                    player.performCommand(nowCommand);
                }
            }
        } else
        {
            for (String nowCommand : commands)
                Bukkit.dispatchCommand(commandSender, nowCommand);
        }
    }

    private boolean runGlobalScript(String varName)
    {
        Integer nowValue = EasyVar.getVarManager().getGlobalValue(varName);
        return nowValue >= requireVal;

    }

    private boolean runPlayerScript(String playerName, String varName)
    {
        Integer nowValue = EasyVar.getVarManager().getPlayerValue(playerName, varName);
        return nowValue >= requireVal;
    }

    public boolean run()
    {
        boolean successExecute;
        if (varType.equalsIgnoreCase("global"))
        {
            successExecute = runGlobalScript(varName);
        } else
        {
            successExecute = runPlayerScript(varType, varName);
        }
        return successExecute;
    }

    public boolean run(String playerName)
    {
        boolean successExecute;
        successExecute = runPlayerScript(playerName, varName);
        return successExecute;
    }

    public String getVarType()
    {
        return varType;
    }

    public String getVarName()
    {
        return varName;
    }

    public void setscriptName(String scriptName)
    {
        this.scriptName = scriptName;
    }

    public void setVarType(String varType)
    {
        config.getConfigYaml().set("varType", varType);
        this.varType = varType;
        saveFile();
    }

    public void setVarName(String varName)
    {
        config.getConfigYaml().set("varName", varName);
        this.varName = varName;
        saveFile();
    }

    public void setRequireVal(Integer requireVal)
    {
        config.getConfigYaml().set("requireValue", requireVal);
        this.requireVal = requireVal;
        saveFile();
    }

    public void setOp(boolean op)
    {
        config.getConfigYaml().set("isOp", op);
        this.isOp = op;
        saveFile();
    }

    public boolean deleteCommand(String command)
    {
        for (String nowCommand : commands)
        {
            if (command.equalsIgnoreCase(nowCommand))
            {
                commands.remove(nowCommand);
                config.getConfigYaml().set("commands", commands);
                saveFile();
                return true;
            }
        }
        return false;
    }

    public void addCommand(String command)
    {
        commands.add(command);
        config.getConfigYaml().set("commands", commands);
        saveFile();
    }

    public List<String> getCommands()
    {
        return commands;
    }

    public void saveFile()
    {
        ConfigUtil.saveFile(config);
    }

    public Script(String scriptName)
    {
        setScriptName(scriptName);
        plugin = EasyVar.getPlugin();
        commands = new ArrayList<>();
        setScriptName(scriptName);
        loadFile();
        saveFile();
    }
}

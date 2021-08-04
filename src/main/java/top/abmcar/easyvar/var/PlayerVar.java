package top.abmcar.easyvar.var;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.EasyVar;
import top.abmcar.easyvar.config.Config;
import top.abmcar.easyvar.config.ConfigUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerVar
{
    private HashMap<String,Integer> integerHashMap;
    private String playerName;
    private Config config;
    private static String varType = "playerVar";
    private List<String> varList;

    public PlayerVar(String playerName)
    {
        integerHashMap = new HashMap<String,Integer>();
        varList = new ArrayList<String>();
        setPlayerName(playerName);
        loadFile();
    }

    private void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public Integer getValue(String key)
    {
        if (!integerHashMap.containsKey(key))
        {
            setValue(key,0);
        }
        return integerHashMap.get(key);
    }

    public void setValue(String key, Integer value)
    {
        if (integerHashMap.containsKey(key))
        {
            integerHashMap.remove(key);
        }else
        {
            varList.add(key);
            config.getConfigYaml().set("varlist",varList);
            config.getConfigYaml().set(key,value);
            saveFile();

        }
        integerHashMap.put(key,value);
    }

    public HashMap<String,Integer> getMap()
    {
        return integerHashMap;
    }

    private void loadFile()
    {
        config = ConfigUtil.loadVarConfig(EasyVar.getPlugin(), varType, playerName + ".yml");
        YamlConfiguration yamlConfiguration = config.getConfigYaml();
        List<String> varList = yamlConfiguration.getStringList("varlist");
        for (String nowVar : varList)
        {
            int varVal = yamlConfiguration.getInt(nowVar);
            integerHashMap.put(nowVar,varVal);
        }
    }

    public void saveFile()
    {
        ConfigUtil.saveFile(config);
    }

}

package top.abmcar.easyvar.var;

import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.EasyVar;

import java.util.HashMap;

public class VarManager
{
    public static VarManager Instance = new VarManager();
    private HashMap<String, PlayerVar> playerVarHashMap;
    private Plugin plugin;

    public VarManager()
    {
        playerVarHashMap = new HashMap<String, PlayerVar>();
        plugin = EasyVar.getPlugin();
        GlobalVar.loadFile();
    }

    public PlayerVar getPlayerVars(String playerName)
    {
        PlayerVar nowPlayerVar = null;
        if(playerVarHashMap.containsKey(playerName))
            return playerVarHashMap.get(playerName);
        else
        {
            nowPlayerVar = new PlayerVar(playerName);
            playerVarHashMap.put(playerName,nowPlayerVar);
            return nowPlayerVar;
        }
//        for (String it : playerVarHashMap.keySet())
//        {
//            if (it.equals(playerName))
//            {
//                nowPlayerVar = playerVarHashMap.get(it);
//                break;
//            }
//        }
//        if (nowPlayerVar == null)
//        return nowPlayerVar;
    }

    private void setPlayerVarHashMap(String key, PlayerVar val)
    {
        if (playerVarHashMap.containsKey(key))
        {
            playerVarHashMap.remove(key);
        }
        playerVarHashMap.put(key, val);
    }

    public Integer getPlayerValue(String playerName, String varName)
    {
//        if (!playerVarHashMap.containsKey(playerName))
//        {
//            PlayerVar newPlayerVar = new PlayerVar(playerName);
//            playerVarHashMap.put(playerName, newPlayerVar);
//        }
//        PlayerVar nowPlayer = playerVarHashMap.get(playerName);
        PlayerVar nowPlayer = getPlayerVars(playerName);
        Integer nowValue = nowPlayer.getValue(varName);
        return nowValue;
    }

    public Integer getGlobalValue(String varName)
    {
        return GlobalVar.getValue(varName);
    }

    public void setGlobalValue(String varName, Integer value)
    {
        GlobalVar.setValue(varName, value);
    }

    public void setPlayerValue(String playerName, String varName, Integer value)
    {
        PlayerVar nowPlayerVar = getPlayerVars(playerName);
        nowPlayerVar.setValue(varName, value);
        setPlayerVarHashMap(playerName, nowPlayerVar);
    }

    public void addGlobalValue(String varName, Integer value)
    {
        GlobalVar.setValue(varName, getGlobalValue(varName) + value);
    }

    public void addPlayerValue(String playerName, String varName, Integer value)
    {
        PlayerVar nowPlayerVar = getPlayerVars(playerName);
        nowPlayerVar.setValue(varName, getPlayerValue(playerName, varName) + value);
        setPlayerVarHashMap(playerName, nowPlayerVar);
    }

    public void reduceGlobalValue(String varName, Integer value)
    {
        GlobalVar.setValue(varName, getGlobalValue(varName) - value);
    }

    public void reducePlayerValue(String playerName, String varName, Integer value)
    {
        PlayerVar nowPlayerVar = getPlayerVars(playerName);
        nowPlayerVar.setValue(varName, getPlayerValue(playerName, varName) - value);
        setPlayerVarHashMap(playerName, nowPlayerVar);
    }

    public void save()
    {
        for (String it : playerVarHashMap.keySet())
        {
            PlayerVar nowPlayerVar = playerVarHashMap.get(it);
            nowPlayerVar.saveFile();
        }
        GlobalVar.saveFile();
    }
}

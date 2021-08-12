package top.abmcar.easyvar.var;

import java.util.HashMap;

public class VarManager {
    private final HashMap<String, PlayerVar> playerVarHashMap;

    public VarManager() {
        playerVarHashMap = new HashMap<>();
        GlobalVar.loadFile();
    }

    public PlayerVar getPlayerVars(String playerName) {
        PlayerVar nowPlayerVar;
        if (playerVarHashMap.containsKey(playerName))
            return playerVarHashMap.get(playerName);
        else {
            nowPlayerVar = new PlayerVar(playerName);
            playerVarHashMap.put(playerName, nowPlayerVar);
            return nowPlayerVar;
        }
    }

    private void setPlayerVarHashMap(String key, PlayerVar val) {
        playerVarHashMap.remove(key);
        playerVarHashMap.put(key, val);
    }

    public Integer getPlayerValue(String playerName, String varName) {
//        if (!playerVarHashMap.containsKey(playerName))
//        {
//            PlayerVar newPlayerVar = new PlayerVar(playerName);
//            playerVarHashMap.put(playerName, newPlayerVar);
//        }
//        PlayerVar nowPlayer = playerVarHashMap.get(playerName);
        PlayerVar nowPlayer = getPlayerVars(playerName);
        return nowPlayer.getValue(varName);
    }

    public Integer getGlobalValue(String varName) {
        return GlobalVar.getValue(varName);
    }

    public void setGlobalValue(String varName, Integer value) {
        GlobalVar.setValue(varName, value);
    }

    public void setPlayerValue(String playerName, String varName, Integer value) {
        PlayerVar nowPlayerVar = getPlayerVars(playerName);
        nowPlayerVar.setValue(varName, value);
        setPlayerVarHashMap(playerName, nowPlayerVar);
    }

    public void addGlobalValue(String varName, Integer value) {
        GlobalVar.setValue(varName, getGlobalValue(varName) + value);
    }

    public void addPlayerValue(String playerName, String varName, Integer value) {
        PlayerVar nowPlayerVar = getPlayerVars(playerName);
        nowPlayerVar.setValue(varName, getPlayerValue(playerName, varName) + value);
        setPlayerVarHashMap(playerName, nowPlayerVar);
    }

    public void reduceGlobalValue(String varName, Integer value) {
        GlobalVar.setValue(varName, getGlobalValue(varName) - value);
    }

    public void reducePlayerValue(String playerName, String varName, Integer value) {
        PlayerVar nowPlayerVar = getPlayerVars(playerName);
        nowPlayerVar.setValue(varName, getPlayerValue(playerName, varName) - value);
        setPlayerVarHashMap(playerName, nowPlayerVar);
    }

    public void save() {
        for (String it : playerVarHashMap.keySet()) {
            PlayerVar nowPlayerVar = playerVarHashMap.get(it);
            nowPlayerVar.saveFile();
        }
        GlobalVar.saveFile();
    }
}

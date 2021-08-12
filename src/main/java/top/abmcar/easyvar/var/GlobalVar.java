package top.abmcar.easyvar.var;

import org.bukkit.configuration.file.YamlConfiguration;
import top.abmcar.easyvar.EasyVar;
import top.abmcar.easyvar.config.Config;
import top.abmcar.easyvar.config.ConfigUtil;

import java.util.HashMap;
import java.util.List;

public class GlobalVar {
    private static final HashMap<String, Integer> integerHashMap = new HashMap<>();
    private static final String varType = "globalVar";
    private static Config config;
    private static List<String> varList;

    public static Integer getValue(String key) {
        if (!integerHashMap.containsKey(key)) {
            setValue(key, 0);
        }
        return integerHashMap.get(key);
    }

    public static void setValue(String key, Integer value) {
        loadFile();
        if (integerHashMap.containsKey(key)) {
            integerHashMap.remove(key);
        } else {
            varList.add(key);
            config.getConfigYaml().set("varlist", varList);
        }
        integerHashMap.put(key, value);
        config.getConfigYaml().set(key, value);
        saveFile();
    }

    public static HashMap<String, Integer> getMap() {
        return integerHashMap;
    }

    public static void loadFile() {
        config = ConfigUtil.loadVarConfig(EasyVar.getPlugin(), varType, "Global.yml");
        YamlConfiguration yamlConfiguration = config.getConfigYaml();
        varList = yamlConfiguration.getStringList("varlist");
        for (String nowVar : varList) {
            int varVal = yamlConfiguration.getInt(nowVar);
            integerHashMap.put(nowVar, varVal);
        }
    }

    public static void saveFile() {
        ConfigUtil.saveFile(config);
    }

}

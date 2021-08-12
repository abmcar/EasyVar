package top.abmcar.easyvar.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import top.abmcar.easyvar.EasyVar;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("all")
public class ConfigUtil {
    public static YamlConfiguration loadYaml(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        File configFile = new File(pluginDataFolder, configName);
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        if (!configFile.exists()) {
            EasyVar.getPlugin().saveResource(configName, true);
        }
        return YamlConfiguration.loadConfiguration(configFile);
    }

    public static void CreateBaseScript(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        File scriptFolder = new File(pluginDataFolder, "scripts");
        if (!scriptFolder.exists()) scriptFolder.mkdir();
        File scriptConfig = new File(scriptFolder, configName + ".yml");
        if (!scriptConfig.exists())
            EasyVar.getPlugin().saveResource("scripts\\" + configName + ".yml", true);
    }

    public static Config loadVarConfig(Plugin plugin, String varType, String configName) {
        File pluginDataFolder = plugin.getDataFolder();//插件数据文件夹
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        File varFolder = new File(pluginDataFolder, varType);
        if (varType.equalsIgnoreCase("globalVar"))//global变量存在根目录
            varFolder = pluginDataFolder;
        if (!varFolder.exists()) varFolder.mkdir();//PlayerVar文件夹
        File configFile = new File(varFolder, configName);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                plugin.getLogger().info("创建" + configFile.toString() + "文件失败");
                plugin.getLogger().info(e.toString());
            }
        }
        Config config = new Config(configFile, YamlConfiguration.loadConfiguration(configFile));
        return config;
    }

    public static Config loadScriptConfig(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        File scriptFolder = new File(pluginDataFolder, "scripts");
        if (!scriptFolder.exists()) scriptFolder.mkdir();
        File scriptConfig = new File(scriptFolder, configName);
        if (!scriptConfig.exists()) {
            try {
                scriptConfig.createNewFile();
            } catch (Exception e) {
                plugin.getLogger().info("创建" + scriptConfig.toString() + "文件失败");
                plugin.getLogger().info(e.toString());
            }
        }
        Config config = new Config(scriptConfig, YamlConfiguration.loadConfiguration(scriptConfig));
        return config;
    }

    public static Config loadConfig(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        File configFile = new File(pluginDataFolder, configName);
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        if (!configFile.exists()) {
            try {
                if (!configName.equalsIgnoreCase("config.yml"))
                    configFile.createNewFile();
                else
                    EasyVar.getPlugin().saveResource(configName, true);
            } catch (Exception e) {
                plugin.getLogger().info("创建" + configFile.toString() + "文件失败");
                plugin.getLogger().info(e.toString());
            }
        }
        Config config = new Config(configFile, YamlConfiguration.loadConfiguration(configFile));
        return config;
    }

    public static void saveFile(Config config) {
        YamlConfiguration yaml = config.getConfigYaml();
        try {
            yaml.save(config.getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

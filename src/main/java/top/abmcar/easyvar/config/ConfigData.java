package top.abmcar.easyvar.config;

import org.bukkit.configuration.file.YamlConfiguration;
import top.abmcar.easyvar.EasyVar;

public class ConfigData
{
    public static ConfigData INSTANCE=new ConfigData();
    private ConfigData() {}
    private YamlConfiguration config=ConfigUtil.loadYaml(EasyVar.getPlugin(),"config.yml");

    public boolean runTask = config.getBoolean("runTask");
    public double runTaskTime = config.getDouble("runTaskTime");
    public String runPlayerScriptName = config.getString("runPlayerScriptName");

}

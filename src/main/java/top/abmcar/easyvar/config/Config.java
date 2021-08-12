package top.abmcar.easyvar.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private File configFile;
    private YamlConfiguration configYaml;

    public Config(File configFile, YamlConfiguration configYaml) {
        setConfigFile(configFile);
        setConfigYaml(configYaml);
    }

    public YamlConfiguration getConfigYaml() {
        return configYaml;
    }

    public void setConfigYaml(YamlConfiguration configYaml) {
        this.configYaml = configYaml;
    }

    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }


}

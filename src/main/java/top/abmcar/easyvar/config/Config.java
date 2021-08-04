package top.abmcar.easyvar.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config
{
    private File configFile;
    private YamlConfiguration configYaml;
    private File dataFolder;

    public Config(File dataFolder, File configFile, YamlConfiguration configYaml)
    {
        setDataFolder(dataFolder);
        setConfigFile(configFile);
        setConfigYaml(configYaml);
    }

    public File getDataFolder()
    {
        return dataFolder;
    }

    public void setDataFolder(File dataFolder)
    {
        this.dataFolder = dataFolder;
    }

    public YamlConfiguration getConfigYaml()
    {
        return configYaml;
    }

    public void setConfigYaml(YamlConfiguration configYaml)
    {
        this.configYaml = configYaml;
    }

    public File getConfigFile()
    {
        return configFile;
    }

    public void setConfigFile(File configFile)
    {
        this.configFile = configFile;
    }


}

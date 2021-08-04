package top.abmcar.easyvar;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import top.abmcar.easyvar.config.Config;
import top.abmcar.easyvar.config.ConfigUtil;
import top.abmcar.easyvar.var.VarManager;
import top.abmcar.easyvar.commandListener.commandListener;


public final class EasyVar extends JavaPlugin
{
    private static  EasyVar plugin;
//    private YamlConfiguration config;
    private static Config config;
    private static VarManager varManager;

    @Override
    public void onEnable()
    {
//        config = ConfigUtil.loadConfig(this,"config.yml");
        plugin = this;
        varManager = new VarManager();
        Bukkit.getPluginCommand("ev").setExecutor(new commandListener());
        this.getLogger().info("插件加载完成,Bug反馈+QQ1114654975");
        // Plugin startup login
    }

    @Override
    public void onDisable()
    {
        varManager.save();
        this.getLogger().info("插件卸载完成,Bug反馈+QQ1114654975");
        // Plugin shutdown logic
    }

    public static VarManager getVarManager()
    {
        return varManager;
    }
    public static EasyVar getPlugin() {
        return plugin;
    }
}

package top.abmcar.easyvar;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import top.abmcar.easyvar.commandListener.displayCommandListener;
import top.abmcar.easyvar.config.Config;
import top.abmcar.easyvar.config.ConfigData;
import top.abmcar.easyvar.config.ConfigUtil;
import top.abmcar.easyvar.runnables.EasyVarRunnable;
import top.abmcar.easyvar.script.ScriptManager;
import top.abmcar.easyvar.var.VarManager;
import top.abmcar.easyvar.commandListener.commandListener;


public final class EasyVar extends JavaPlugin
{
    private static EasyVar plugin;
        private YamlConfiguration config;
    private static VarManager varManager;
    private static ScriptManager scriptManager;
    public EasyVarRunnable easyVarRunnable;
    @Override
    public void onEnable()
    {
        plugin = this;
        varManager = new VarManager();
        scriptManager = new ScriptManager();
        config = ConfigUtil.loadYaml(this,"config.yml");
        ConfigUtil.CreateBaseScript(plugin, ConfigData.INSTANCE.runPlayerScriptName);
        Bukkit.getPluginCommand("ev").setExecutor(new commandListener());
        Bukkit.getPluginCommand("evdisplay").setExecutor(new displayCommandListener());
        easyVarRunnable = new EasyVarRunnable();
        easyVarRunnable.runTaskTimer(this, 1, (int)(ConfigData.INSTANCE.runTaskTime*20));
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

    public static ScriptManager getScriptManager()
    {
        return scriptManager;
    }

    public static VarManager getVarManager()
    {
        return varManager;
    }

    public static EasyVar getPlugin()
    {
        return plugin;
    }
}

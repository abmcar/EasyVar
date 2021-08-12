package top.abmcar.easyvar;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.abmcar.easyvar.command.CommandListener;
import top.abmcar.easyvar.command.DisplayCommandListener;
import top.abmcar.easyvar.config.ConfigData;
import top.abmcar.easyvar.config.ConfigUtil;
import top.abmcar.easyvar.runnable.EasyVarRunnable;
import top.abmcar.easyvar.var.VarManager;

import java.util.Objects;


public final class EasyVar extends JavaPlugin {
    private static EasyVar plugin;
    private static VarManager varManager;
    public EasyVarRunnable easyVarRunnable;

    @Override
    public void onEnable() {
        plugin = this;
        varManager = new VarManager();
        ConfigUtil.CreateBaseScript(plugin, ConfigData.INSTANCE.runPlayerScriptName);
        Objects.requireNonNull(Bukkit.getPluginCommand("ev")).setExecutor(new CommandListener());
        Objects.requireNonNull(Bukkit.getPluginCommand("evdisplay")).setExecutor(new DisplayCommandListener());
        easyVarRunnable = new EasyVarRunnable();
        easyVarRunnable.runTaskTimer(this, 1, (int) (ConfigData.INSTANCE.runTaskTime * 20));
        this.getLogger().info("插件加载完成,Bug反馈+QQ1114654975");
        // Plugin startup login
    }

    @Override
    public void onDisable() {
        varManager.save();
        this.getLogger().info("插件卸载完成,Bug反馈+QQ1114654975");
        // Plugin shutdown logic
    }


    public static VarManager getVarManager() {
        return varManager;
    }

    public static EasyVar getPlugin() {
        return plugin;
    }
}

package top.abmcar.easyvar.stringUtil;


import org.bukkit.entity.Player;

public class StringUtil
{
    public static StringUtil Instance = new StringUtil();
    private StringUtil() {};
    public String getString(String string, Player player)
    {
        string = string.replaceAll("<player>",player.getName());
        return string;
    }
}

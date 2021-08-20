package top.abmcar.easyvar.util;


import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderUtil {
    public static String placeHolders(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player,string);
    }
}

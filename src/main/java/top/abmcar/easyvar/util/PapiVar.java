package top.abmcar.easyvar.util;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import top.abmcar.easyvar.EasyVar;

import java.util.Map;

/**
 * @author: xbaimiao
 * @date: 2022年03月12日 20:02
 * @description:
 */
public class PapiVar extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "ev";
    }

    @Override
    public String getAuthor() {
        return "xbaimiao";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        Map<String, Integer> vars = EasyVar.getVarManager().getPlayerVars(p.getName()).getMap();
        if (vars.containsKey(params)) {
            return vars.get(params).toString();
        }
        return "";
    }
}

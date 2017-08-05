package gg.frog.mc.treasuremap.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import gg.frog.mc.treasuremap.PluginMain;
import gg.frog.mc.treasuremap.config.LangCfg;
import gg.frog.mc.treasuremap.utils.StrUtil;

public class MainListener implements Listener {

    private PluginMain pm;

    public MainListener(PluginMain pm) {
        this.pm = pm;
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        if (StrUtil.messageFormat(LangCfg.INVENTORY_NAME + "&r&5&9&2&0&r").equals(event.getInventory().getName())) {
            event.setCancelled(true);
        }
    }
}

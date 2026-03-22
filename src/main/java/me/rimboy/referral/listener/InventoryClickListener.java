package me.rimboy.referral.listener;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.util.ColorUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final RimboyReferralPlugin plugin;

    public InventoryClickListener(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String title = ColorUtil.color(plugin.getConfigUtil().getGuiConfig().getString("admin-menu.title", "&8Коды"));
        if (event.getView().getTitle().equals(title)) {
            event.setCancelled(true);
        }
    }
}
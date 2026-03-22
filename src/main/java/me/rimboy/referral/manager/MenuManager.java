package me.rimboy.referral.manager;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.gui.AdminMenu;
import org.bukkit.entity.Player;

public class MenuManager {

    private final RimboyReferralPlugin plugin;

    public MenuManager(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    public void openAdminMenu(Player player) {
        new AdminMenu(plugin).open(player);
    }
}
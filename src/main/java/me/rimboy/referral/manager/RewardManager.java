package me.rimboy.referral.manager;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.model.PromoCode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RewardManager {

    private final RimboyReferralPlugin plugin;

    public RewardManager(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    public void giveRewards(Player player, PromoCode code) {
        for (String command : code.getRewards()) {
            String parsed = command.replace("%player%", player.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsed);
        }
    }
}
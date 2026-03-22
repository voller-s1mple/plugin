package me.rimboy.referral.manager;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.util.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class StatsManager {

    private final RimboyReferralPlugin plugin;

    public StatsManager(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    public void addUse(String codeName) {
        FileConfiguration config = plugin.getConfigUtil().getStatsConfig();
        String path = "stats." + codeName.toLowerCase();
        int current = config.getInt(path, 0);
        config.set(path, current + 1);
        save();
    }

    public int getUses(String codeName) {
        return plugin.getConfigUtil().getStatsConfig().getInt("stats." + codeName.toLowerCase(), 0);
    }

    public void sendStats(CommandSender sender) {
        FileConfiguration config = plugin.getConfigUtil().getStatsConfig();
        ConfigurationSection section = config.getConfigurationSection("stats");

        if (section == null || section.getKeys(false).isEmpty()) {
            sender.sendMessage(ColorUtil.color("&7Статистика пока пустая."));
            return;
        }

        for (String key : section.getKeys(false)) {
            int value = section.getInt(key);
            sender.sendMessage(ColorUtil.color("&e" + key + " &7-> &f" + value));
        }
    }

    public void save() {
        plugin.getConfigUtil().saveStats();
    }
}
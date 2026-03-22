package me.rimboy.referral.manager;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.model.PromoCode;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerDataManager {

    private final RimboyReferralPlugin plugin;

    public PlayerDataManager(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean hasUsedCode(UUID uuid, String code) {
        FileConfiguration config = plugin.getConfigUtil().getPlayerDataConfig();
        List<String> usedCodes = config.getStringList("players." + uuid + ".used-codes");
        return usedCodes.contains(code.toLowerCase());
    }

    public boolean hasUsedExclusiveGroup(UUID uuid, String group) {
        FileConfiguration config = plugin.getConfigUtil().getPlayerDataConfig();
        return config.contains("players." + uuid + ".exclusive-groups." + group);
    }

    public void markCodeUsed(UUID uuid, PromoCode code) {
        FileConfiguration config = plugin.getConfigUtil().getPlayerDataConfig();

        String basePath = "players." + uuid;
        List<String> usedCodes = new ArrayList<>(config.getStringList(basePath + ".used-codes"));
        usedCodes.add(code.getName().toLowerCase());
        config.set(basePath + ".used-codes", usedCodes);

        if (!code.getExclusiveGroup().isEmpty()) {
            config.set(basePath + ".exclusive-groups." + code.getExclusiveGroup(), code.getName().toLowerCase());
        }

        save();
    }

    public void save() {
        plugin.getConfigUtil().savePlayerData();
    }
}
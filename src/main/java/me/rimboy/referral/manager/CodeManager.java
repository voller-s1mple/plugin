package me.rimboy.referral.manager;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.model.CodeType;
import me.rimboy.referral.model.PromoCode;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeManager {

    private final RimboyReferralPlugin plugin;
    private final Map<String, PromoCode> codes = new HashMap<>();

    public CodeManager(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        codes.clear();

        FileConfiguration config = plugin.getConfigUtil().getCodesConfig();
        ConfigurationSection section = config.getConfigurationSection("codes");
        if (section == null) return;

        for (String key : section.getKeys(false)) {
            String path = "codes." + key;

            CodeType type = CodeType.valueOf(config.getString(path + ".type", "PROMO").toUpperCase());
            boolean enabled = config.getBoolean(path + ".enabled", true);
            boolean oneTime = config.getBoolean(path + ".one-time", true);
            String exclusiveGroup = config.getString(path + ".exclusive-group", "");
            String owner = config.getString(path + ".owner", "");
            List<String> rewards = config.getStringList(path + ".rewards");

            PromoCode code = new PromoCode(key, type, enabled, oneTime, exclusiveGroup, owner, rewards);
            codes.put(key.toLowerCase(), code);
        }
    }

    public void reload() {
        plugin.getConfigUtil().reloadAll();
        load();
    }

    public PromoCode getCode(String name) {
        return codes.get(name.toLowerCase());
    }

    public Collection<PromoCode> getAllCodes() {
        return codes.values();
    }
}
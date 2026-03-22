package me.rimboy.referral.util;

import me.rimboy.referral.RimboyReferralPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {

    private final RimboyReferralPlugin plugin;

    private File messagesFile;
    private File codesFile;
    private File guiFile;
    private File playerDataFile;
    private File statsFile;

    private FileConfiguration messagesConfig;
    private FileConfiguration codesConfig;
    private FileConfiguration guiConfig;
    private FileConfiguration playerDataConfig;
    private FileConfiguration statsConfig;

    public ConfigUtil(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
        loadAll();
    }

    public void loadAll() {
        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        codesFile = new File(plugin.getDataFolder(), "codes.yml");
        guiFile = new File(plugin.getDataFolder(), "gui.yml");
        playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");
        statsFile = new File(plugin.getDataFolder(), "stats.yml");

        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        codesConfig = YamlConfiguration.loadConfiguration(codesFile);
        guiConfig = YamlConfiguration.loadConfiguration(guiFile);
        playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
        statsConfig = YamlConfiguration.loadConfiguration(statsFile);
    }

    public void reloadAll() {
        loadAll();
    }

    public String getMessage(String path) {
        return messagesConfig.getString("messages." + path, "&cMessage not found: " + path);
    }

    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

    public FileConfiguration getCodesConfig() {
        return codesConfig;
    }

    public FileConfiguration getGuiConfig() {
        return guiConfig;
    }

    public FileConfiguration getPlayerDataConfig() {
        return playerDataConfig;
    }

    public FileConfiguration getStatsConfig() {
        return statsConfig;
    }

    public void savePlayerData() {
        save(playerDataConfig, playerDataFile);
    }

    public void saveStats() {
        save(statsConfig, statsFile);
    }

    private void save(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
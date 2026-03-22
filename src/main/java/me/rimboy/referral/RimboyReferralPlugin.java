package me.rimboy.referral;

import me.rimboy.referral.command.RefAdminCommand;
import me.rimboy.referral.command.RefCommand;
import me.rimboy.referral.listener.InventoryClickListener;
import me.rimboy.referral.manager.CodeManager;
import me.rimboy.referral.manager.MenuManager;
import me.rimboy.referral.manager.PlayerDataManager;
import me.rimboy.referral.manager.RewardManager;
import me.rimboy.referral.manager.StatsManager;
import me.rimboy.referral.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class RimboyReferralPlugin extends JavaPlugin {

    private static RimboyReferralPlugin instance;

    private ConfigUtil configUtil;
    private CodeManager codeManager;
    private PlayerDataManager playerDataManager;
    private StatsManager statsManager;
    private RewardManager rewardManager;
    private MenuManager menuManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResourceIfNotExists("messages.yml");
        saveResourceIfNotExists("codes.yml");
        saveResourceIfNotExists("gui.yml");
        saveResourceIfNotExists("playerdata.yml");
        saveResourceIfNotExists("stats.yml");

        this.configUtil = new ConfigUtil(this);
        this.codeManager = new CodeManager(this);
        this.playerDataManager = new PlayerDataManager(this);
        this.statsManager = new StatsManager(this);
        this.rewardManager = new RewardManager(this);
        this.menuManager = new MenuManager(this);

        getCommand("ref").setExecutor(new RefCommand(this));
        getCommand("refadmin").setExecutor(new RefAdminCommand(this));

        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);

        getLogger().info("RimboyReferral enabled.");
    }

    @Override
    public void onDisable() {
        playerDataManager.save();
        statsManager.save();
        getLogger().info("RimboyReferral disabled.");
    }

    private void saveResourceIfNotExists(String fileName) {
        if (!new java.io.File(getDataFolder(), fileName).exists()) {
            saveResource(fileName, false);
        }
    }

    public static RimboyReferralPlugin getInstance() {
        return instance;
    }

    public ConfigUtil getConfigUtil() {
        return configUtil;
    }

    public CodeManager getCodeManager() {
        return codeManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }

    public RewardManager getRewardManager() {
        return rewardManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }
}
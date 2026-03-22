package me.rimboy.referral.command;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.model.PromoCode;
import me.rimboy.referral.util.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefCommand implements CommandExecutor {

    private final RimboyReferralPlugin plugin;

    public RefCommand(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("player-only")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("usage-ref")));
            return true;
        }

        String inputCode = args[0];
        PromoCode code = plugin.getCodeManager().getCode(inputCode);

        if (code == null) {
            player.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("code-not-found")));
            return true;
        }

        if (!code.isEnabled()) {
            player.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("code-disabled")));
            return true;
        }

        if (plugin.getPlayerDataManager().hasUsedCode(player.getUniqueId(), code.getName())) {
            player.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("code-already-used")));
            return true;
        }

        if (!code.getExclusiveGroup().isEmpty()
                && plugin.getPlayerDataManager().hasUsedExclusiveGroup(player.getUniqueId(), code.getExclusiveGroup())) {
            player.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("code-group-blocked")));
            return true;
        }

        plugin.getRewardManager().giveRewards(player, code);
        plugin.getPlayerDataManager().markCodeUsed(player.getUniqueId(), code);
        plugin.getStatsManager().addUse(code.getName());

        String message = plugin.getConfigUtil().getMessage("code-success").replace("%code%", code.getName());
        player.sendMessage(ColorUtil.color(message));
        return true;
    }
}
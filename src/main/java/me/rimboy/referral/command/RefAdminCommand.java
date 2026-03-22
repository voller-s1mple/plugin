package me.rimboy.referral.command;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.util.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefAdminCommand implements CommandExecutor {

    private final RimboyReferralPlugin plugin;

    public RefAdminCommand(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rimboyref.admin")) {
            sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("no-permission")));
            return true;
        }

        if (args.length == 0) {
            if (sender instanceof Player) {
                plugin.getMenuManager().openAdminMenu((Player) sender);
                sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("admin-menu-opened")));
            } else {
                sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("usage-admin")));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getConfigUtil().reloadAll();
            plugin.getCodeManager().reload();
            sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("admin-reload")));
            return true;
        }

        if (args[0].equalsIgnoreCase("stats")) {
            sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("admin-stats-header")));
            plugin.getStatsManager().sendStats(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("menu") && sender instanceof Player) {
            plugin.getMenuManager().openAdminMenu((Player) sender);
            sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("admin-menu-opened")));
            return true;
        }

        sender.sendMessage(ColorUtil.color(plugin.getConfigUtil().getMessage("usage-admin")));
        return true;
    }
}
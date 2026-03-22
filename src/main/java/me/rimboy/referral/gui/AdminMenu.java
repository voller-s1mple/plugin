package me.rimboy.referral.gui;

import me.rimboy.referral.RimboyReferralPlugin;
import me.rimboy.referral.model.PromoCode;
import me.rimboy.referral.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AdminMenu {

    private final RimboyReferralPlugin plugin;

    public AdminMenu(RimboyReferralPlugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        String title = ColorUtil.color(plugin.getConfigUtil().getGuiConfig().getString("admin-menu.title", "&8Коды"));
        int size = plugin.getConfigUtil().getGuiConfig().getInt("admin-menu.size", 27);

        Inventory inventory = Bukkit.createInventory(null, size, title);

        int slot = 0;
        for (PromoCode code : plugin.getCodeManager().getAllCodes()) {
            if (slot >= size) break;

            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(ColorUtil.color("&eКод: &f" + code.getName()));

                List<String> lore = new ArrayList<>();
                lore.add(ColorUtil.color("&7Тип: &f" + code.getType().name()));
                lore.add(ColorUtil.color("&7Включён: &f" + code.isEnabled()));
                lore.add(ColorUtil.color("&7Владелец: &f" + code.getOwner()));
                lore.add(ColorUtil.color("&7Использований: &f" + plugin.getStatsManager().getUses(code.getName())));
                lore.add(ColorUtil.color("&7Группа: &f" + code.getExclusiveGroup()));

                meta.setLore(lore);
                item.setItemMeta(meta);
            }

            inventory.setItem(slot, item);
            slot++;
        }

        player.openInventory(inventory);
    }
}
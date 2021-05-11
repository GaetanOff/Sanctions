package com.gaetan.sanctions.listener;

import com.gaetan.sanctions.SanctionsPlugin;
import com.gaetan.sanctions.data.Ban;
import com.gaetan.sanctions.data.Mute;
import com.gaetan.sanctions.enums.Lang;
import com.gaetan.sanctions.manager.managers.ConfigManager;
import com.gaetan.sanctions.manager.managers.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public final class InventoryListener implements Listener {
    /**
     * Reference to the main class
     */
    private final SanctionsPlugin sanctionsPlugin;

    /**
     * Constructor for the InventoryListener class.
     *
     * @param sanctionsPlugin Reference to the main class
     */
    public InventoryListener(final SanctionsPlugin sanctionsPlugin) {
        this.sanctionsPlugin = sanctionsPlugin;
        this.sanctionsPlugin.getServer().getPluginManager().registerEvents(this, this.sanctionsPlugin);
    }

    /**
     * When the player click in an inventory
     */
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final ItemStack item = e.getCurrentItem();
        final Player player = (Player) e.getWhoClicked();
        if (item == null || item.getType() == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null)
            return;

        final InventoryManager inventoryManager = this.sanctionsPlugin.getManagerHandler().getInventoryManager();
        final ConfigManager configManager = this.sanctionsPlugin.getManagerHandler().getConfigManager();
        final String target = this.sanctionsPlugin.getTarget(player);
        final String title = e.getInventory().getTitle();

        if (title.contains(Lang.MAIN_INVENTORY.getText())) {
            e.setCancelled(true);
            switch (item.getType()) {
                case BOOK: {
                    inventoryManager.openMuteInventory(player);
                    break;
                }
                case CHAINMAIL_CHESTPLATE: {
                    inventoryManager.openBanInventory(player);
                    break;
                }
            }
        }
        if (title.contains(Lang.BAN_INVENTORY.getText()) || title.contains(Lang.MUTE_INVENTORY.getText())) {
            e.setCancelled(true);
            switch (item.getType()) {
                case BOOK: {
                    if (title.contains(Lang.BAN_INVENTORY.getText())) {
                        final Ban ban = configManager.getBan(this.reverse(item.getItemMeta().getDisplayName()));
                        player.chat("/ban " + target + " " + ban.getBanTime() + ban.getName().replace("&c", " "));
                    } else {
                        final Mute mute = configManager.getMute(this.reverse(item.getItemMeta().getDisplayName()));
                        player.chat("/mute " + target + " " + mute.getMuteTime() + mute.getName().replace("&c", " "));
                    }
                    player.closeInventory();
                    break;
                }
                case INK_SACK: {
                    player.closeInventory();
                    inventoryManager.openMainInventory(player, target);
                    break;
                }
            }
        }
    }

    private String reverse(final String string) {
        return string.replace('§', '&');
    }
}

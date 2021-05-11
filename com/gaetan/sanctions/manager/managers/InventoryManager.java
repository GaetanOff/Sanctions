package com.gaetan.sanctions.manager.managers;

import com.gaetan.api.item.ItemBuilder;
import com.gaetan.api.message.Message;
import com.gaetan.sanctions.data.Ban;
import com.gaetan.sanctions.data.Mute;
import com.gaetan.sanctions.enums.Lang;
import com.gaetan.sanctions.manager.Manager;
import com.gaetan.sanctions.manager.ManagerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class InventoryManager extends Manager {
    /**
     * Constructor for the InventoryManager class.
     *
     * @param handler Reference to the ManagerHandler
     */
    public InventoryManager(final ManagerHandler handler) {
        super(handler);
    }

    /**
     * Open the main inventory.
     *
     * @param player To open
     * @param target To SS
     */
    public void openMainInventory(final Player player, final String target) {
        final Inventory inventory = this.handler.getSanctionsPlugin().getServer()
                .createInventory(null, 27, Lang.MAIN_INVENTORY.getText());

        inventory.setItem(4, this.getSkull(Bukkit.getPlayer(target)));

        for (int i = 9; i < 18; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 10).setName(" ").toItemStack());
        }

        inventory.setItem(18, new ItemBuilder(Material.BOOK).setName(Lang.MAIN_INVENTORY_MUTE.getText()).toItemStack());
        inventory.setItem(19, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName(Lang.MAIN_INVENTORY_BAN.getText()).removeFlags().toItemStack());

        player.openInventory(inventory);

        this.handler.getSanctionsPlugin().getPlayers().put(player, target);
    }

    /**
     * Open the ban inventory.
     *
     * @param player To open
     */
    public void openBanInventory(final Player player) {
        final Inventory inventory = this.handler.getSanctionsPlugin().getServer()
                .createInventory(null, this.handler.getConfigManager().getBanSlots(), Lang.BAN_INVENTORY.getText());

        int slots = 0;

        for (final Map.Entry<String, Ban> mapEntry : this.handler.getConfigManager().getBanMap().entrySet()) {
            final Ban ban = mapEntry.getValue();
            final List<String> lore = new ArrayList<>();
            for (final String string : ban.getLore())
                lore.add(Message.tl(string));

            final ItemStack itemStack =
                    new ItemBuilder(Material.BOOK)
                            .setLore(lore)
                            .setName(Message.tl(ban.getName())).toItemStack();

            inventory.setItem(slots, itemStack);
            ++slots;
        }

        inventory.setItem(26, new ItemBuilder(Material.INK_SACK, 1, (short) 1).setName(Lang.BACK_ITEMS.getText()).toItemStack());

        player.openInventory(inventory);
    }

    /**
     * Open the mute inventory.
     *
     * @param player To open
     */
    public void openMuteInventory(final Player player) {
        final Inventory inventory = this.handler.getSanctionsPlugin().getServer()
                .createInventory(null, this.handler.getConfigManager().getMuteSlots(), Lang.MUTE_INVENTORY.getText());

        int slots = 0;

        for (final Map.Entry<String, Mute> mapEntry : this.handler.getConfigManager().getMuteMap().entrySet()) {
            final Mute mute = mapEntry.getValue();
            final List<String> lore = new ArrayList<>();
            for (final String string : mute.getLore())
                lore.add(Message.tl(string));

            final ItemStack itemStack =
                    new ItemBuilder(Material.BOOK)
                            .setLore(lore)
                            .setName(Message.tl(mute.getName())).toItemStack();

            inventory.setItem(slots, itemStack);
            ++slots;
        }

        inventory.setItem(26, new ItemBuilder(Material.INK_SACK, 1, (short) 1).setName(Lang.BACK_ITEMS.getText()).toItemStack());

        player.openInventory(inventory);
    }

    /**
     * Getter to get the skull of a Player.
     *
     * @param player Skull of this player
     * @return The skull of @param player
     */
    private ItemStack getSkull(final Player player) {
        final ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        final SkullMeta playerSkullM = (SkullMeta) this.handler.getSanctionsPlugin().getServer().getItemFactory().getItemMeta(Material.SKULL_ITEM);
        playerSkullM.setDisplayName(Message.YELLOW + "✪ " + Message.GOLD + Message.BOLD + "Profil");
        playerSkullM.setLore(Arrays.asList(" ", Message.GOLD + "┃ " + Message.WHITE + "Pseudo: " + player.getName()));
        playerSkullM.setOwner(player.getName());
        playerSkull.setItemMeta(playerSkullM);
        return playerSkull;
    }
}

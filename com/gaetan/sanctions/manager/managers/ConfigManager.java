package com.gaetan.sanctions.manager.managers;

import com.gaetan.sanctions.data.Ban;
import com.gaetan.sanctions.data.Mute;
import com.gaetan.sanctions.manager.Manager;
import com.gaetan.sanctions.manager.ManagerHandler;
import com.google.common.collect.Maps;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

public final class ConfigManager extends Manager {
    private final Map<String, Ban> banMap = Maps.newConcurrentMap();
    private final Map<String, Mute> muteMap = Maps.newConcurrentMap();
    private final int banSlots, muteSlots;

    /**
     * Constructor for the ConfigManager class.
     *
     * @param handler Reference to the ManagerHandler
     */
    public ConfigManager(final ManagerHandler handler) {
        super(handler);

        final FileConfiguration config = this.handler.getSanctionsPlugin().getConfig();

        this.banSlots = config.getInt("ban.slots");
        this.muteSlots = config.getInt("mute.slots");

        this.loadBan();
        this.loadMute();
    }

    /**
     * Load and cache all the ban from the config
     */
    private void loadBan() {
        final FileConfiguration fileConfig = this.handler.getSanctionsPlugin().getConfig();
        final ConfigurationSection section = fileConfig.getConfigurationSection("ban");
        if (section != null) {
            for (final String banName : section.getKeys(false)) {
                if (!banName.equals("slots")) {
                    final String banTime = section.getString(banName + ".banTime");
                    final List<String> lore = section.getStringList(banName + ".lore");
                    final Ban ban = new Ban(banName, banTime, lore);
                    this.banMap.put(banName, ban);
                }
            }
        }
    }

    /**
     * Load and cache all the mute from the config
     */
    private void loadMute() {
        final FileConfiguration fileConfig = this.handler.getSanctionsPlugin().getConfig();
        final ConfigurationSection section = fileConfig.getConfigurationSection("mute");
        if (section != null) {
            for (final String muteName : section.getKeys(false)) {
                if (!muteName.equals("slots")) {
                    final String muteTime = section.getString(muteName + ".muteTime");
                    final List<String> lore = section.getStringList(muteName + ".lore");
                    final Mute mute = new Mute(muteName, muteTime, lore);
                    this.muteMap.put(muteName, mute);
                }
            }
        }
    }

    public void reload() {
        this.banMap.clear();
        this.muteMap.clear();
        this.loadMute();
        this.loadBan();
    }

    /**
     * Getter to get the ban slots.
     *
     * @return The reference ban slots
     */
    public int getBanSlots() {
        return this.banSlots;
    }

    /**
     * Getter to get the mute slots.
     *
     * @return The reference mute slots
     */
    public int getMuteSlots() {
        return this.muteSlots;
    }

    /**
     * Getter to get the ban map.
     *
     * @return The reference BanMap
     */
    public Map<String, Ban> getBanMap() {
        return this.banMap;
    }

    /**
     * Getter to get the mute map.
     *
     * @return The reference MuteMap
     */
    public Map<String, Mute> getMuteMap() {
        return this.muteMap;
    }

    /**
     * Getter to get the ban map.
     *
     * @param name Name of the ban
     * @return The reference BanMap
     */
    public Ban getBan(final String name) {
        return this.banMap.get(name);
    }

    /**
     * Getter to get the mute map.
     *
     * @param name Name of the mute
     * @return The reference MuteMap
     */
    public Mute getMute(final String name) {
        return this.muteMap.get(name);
    }
}

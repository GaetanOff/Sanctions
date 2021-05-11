package com.gaetan.sanctions.manager;

import com.gaetan.sanctions.SanctionsPlugin;
import com.gaetan.sanctions.manager.managers.ConfigManager;
import com.gaetan.sanctions.manager.managers.InventoryManager;

public class ManagerHandler {
    /**
     * Reference to the main class
     */
    private final SanctionsPlugin sanctionsPlugin;

    /**
     * Reference to all the Managers
     */
    private final InventoryManager inventoryManager;
    private final ConfigManager configManager;

    /**
     * Constructor for the ManagerHandler class.
     *
     * @param sanctionsPlugin Reference to the main class
     */
    public ManagerHandler(final SanctionsPlugin sanctionsPlugin) {
        this.sanctionsPlugin = sanctionsPlugin;

        this.inventoryManager = new InventoryManager(this);
        this.configManager = new ConfigManager(this);
    }

    /**
     * Getter to get the SanctionsPlugin reference.
     *
     * @return The reference SanctionsPlugin
     */
    public SanctionsPlugin getSanctionsPlugin() {
        return this.sanctionsPlugin;
    }

    /**
     * Getter to get the Managers reference.
     */
    public InventoryManager getInventoryManager() {
        return this.inventoryManager;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }
}

package com.gaetan.sanctions;

import com.gaetan.api.annotation.GaetanApplication;
import com.gaetan.api.plugin.SimplePlugin;
import com.gaetan.sanctions.command.SanctionsCommand;
import com.gaetan.sanctions.listener.InventoryListener;
import com.gaetan.sanctions.listener.PlayerListener;
import com.gaetan.sanctions.manager.ManagerHandler;
import com.google.common.collect.Maps;
import org.bukkit.entity.Player;

import java.util.Map;

@GaetanApplication(name = "Sanctions", authors = "GaetanOff", version = "1.0", main = "com.gaetan.sanctions.SanctionsPlugin", depend = "")
public final class SanctionsPlugin extends SimplePlugin {
    /**
     * Map to stock the target
     */
    private final Map<Player, String> players = Maps.newConcurrentMap();

    /**
     * Reference to the ManagerHandler
     */
    private ManagerHandler managerHandler;

    /**
     * Method to launch the plugin
     * Note: This is the same as the classic onEnable
     */
    @Override
    protected void onPluginStart() {
        this.saveDefaultConfig();

        this.registerCommands(new SanctionsCommand(this));

        this.managerHandler = new ManagerHandler(this);
    }

    /**
     * Method to register listener
     * Note: This will be trigger after the loading of the server
     */
    @Override
    protected void registerListener() {
        new InventoryListener(this);
        new PlayerListener(this);
    }

    /**
     * Getter to get the ManagerHandler reference.
     *
     * @return The reference ManagerHandler
     */
    public ManagerHandler getManagerHandler() {
        return this.managerHandler;
    }

    /**
     * Getter to get the target from the Player
     *
     * @param player The choosen Player object
     * @return The target
     */
    public String getTarget(final Player player) {
        return this.players.get(player);
    }

    /**
     * Getter to get the Map of all Target.
     *
     * @return The map containing all the target
     */
    public Map<Player, String> getPlayers() {
        return this.players;
    }
}

package com.gaetan.sanctions.listener;

import com.gaetan.sanctions.SanctionsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerListener implements Listener {
    /**
     * Reference to the main class
     */
    private final SanctionsPlugin sanctionsPlugin;

    /**
     * Constructor for the PlayerListener class.
     *
     * @param sanctionsPlugin Reference to the main class
     */
    public PlayerListener(final SanctionsPlugin sanctionsPlugin) {
        this.sanctionsPlugin = sanctionsPlugin;
        this.sanctionsPlugin.getServer().getPluginManager().registerEvents(this, this.sanctionsPlugin);
    }

    /**
     * When a player left the server
     */
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        this.sanctionsPlugin.getPlayers().remove(event.getPlayer());
    }
}

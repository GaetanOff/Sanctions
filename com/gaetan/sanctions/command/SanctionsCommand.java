package com.gaetan.sanctions.command;

import com.gaetan.api.command.utils.annotation.Command;
import com.gaetan.api.command.utils.command.Context;
import com.gaetan.api.command.utils.target.CommandTarget;
import com.gaetan.sanctions.SanctionsPlugin;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public final class SanctionsCommand {
    /**
     * Reference to the main class
     */
    private final SanctionsPlugin sanctionsPlugin;

    /**
     * Constructor for the SanctionsCommand class.
     *
     * @param sanctionsPlugin Reference to the main class
     */
    public SanctionsCommand(final SanctionsPlugin sanctionsPlugin) {
        this.sanctionsPlugin = sanctionsPlugin;
    }

    /**
     * Command to open Sanctions Gui
     * Note: This can only be used by a player with freeze.use permission
     *
     * @param context The command argument
     */
    @Command(name = "ss", aliases = {"sanction", "sanctions"}, permission = "earth.ss", target = CommandTarget.PLAYER)
    public void handleCommand(final Context<ConsoleCommandSender> context, final Player target) {
        this.sanctionsPlugin.getManagerHandler().getInventoryManager()
                .openMainInventory((Player) context.getSender(), target.getName());
    }

    @Command(name = "ssreload", permission = "earth.reload", target = CommandTarget.PLAYER)
    public void handleCommand_Reload(final Context<ConsoleCommandSender> context) {
        this.sanctionsPlugin.getManagerHandler().getConfigManager().reload();
        context.sendMessage("Vous avez reload les sanctions");
    }
}

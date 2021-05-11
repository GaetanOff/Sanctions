package com.gaetan.sanctions.enums;

import com.gaetan.api.message.Message;

public enum Lang {
    MAIN_INVENTORY(Message.GRAY + "EarthSky ➜ Sanctions"),
    MAIN_INVENTORY_MUTE(Message.RED + Message.BOLD + "✸ " + Message.DARK_RED + Message.BOLD + "Sanctions " + Message.GRAY + "◆ " + Message.RED + "Mute"),
    MAIN_INVENTORY_BAN(Message.RED + Message.BOLD + "✸ " + Message.DARK_RED + Message.BOLD + "Sanctions " + Message.GRAY + "◆ " + Message.RED + "Banissement"),
    BAN_INVENTORY(Message.GRAY + "EarthSky ➜ Bannissement"),
    MUTE_INVENTORY(Message.GRAY + "EarthSky ➜ Mute"),
    BACK_ITEMS(Message.DARK_RED + Message.BOLD + "✦ " + Message.RED + Message.BOLD + "Retour");

    private final String text;

    public String getText() {
        return this.text;
    }

    Lang(final String text) {
        this.text = text;
    }
}

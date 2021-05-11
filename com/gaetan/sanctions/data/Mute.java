package com.gaetan.sanctions.data;

import java.util.List;

public final class Mute {
    /**
     * Name and Time
     */
    private final String name, muteTime;

    /**
     * Lore for this mute
     */
    private final List<String> lore;

    /**
     * Constructor for the Mute class.
     *
     * @param name     Name for this mute
     * @param muteTime Time for this mute
     * @param lore     Lore for this mute
     */
    public Mute(final String name, final String muteTime, final List<String> lore) {
        this.name = name;
        this.muteTime = muteTime;
        this.lore = lore;
    }

    /**
     * Getter to get the lore of the ban
     *
     * @return The lore of the ban
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter to get the lore of the ban
     *
     * @return The lore of the ban
     */
    public String getMuteTime() {
        return this.muteTime;
    }

    /**
     * Getter to get the lore of the ban
     *
     * @return The lore of the ban
     */
    public List<String> getLore() {
        return this.lore;
    }
}

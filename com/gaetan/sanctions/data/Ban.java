package com.gaetan.sanctions.data;

import java.util.List;

public final class Ban {
    /**
     * Name and Time
     */
    private final String name, banTime;

    /**
     * Lore for this ban
     */
    private final List<String> lore;

    /**
     * Constructor for the Ban class.
     *
     * @param name    Name for this ban
     * @param banTime Time for this ban
     * @param lore    Lore for this ban
     */
    public Ban(final String name, final String banTime, final List<String> lore) {
        this.name = name;
        this.banTime = banTime;
        this.lore = lore;
    }

    /**
     * Getter to get the name of the ban
     *
     * @return The name of the ban
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter to get the time of the ban
     *
     * @return The time of the ban
     */
    public String getBanTime() {
        return this.banTime;
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

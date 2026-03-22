package me.rimboy.referral.model;

import java.util.List;

public class PromoCode {

    private final String name;
    private final CodeType type;
    private final boolean enabled;
    private final boolean oneTime;
    private final String exclusiveGroup;
    private final String owner;
    private final List<String> rewards;

    public PromoCode(String name, CodeType type, boolean enabled, boolean oneTime,
                     String exclusiveGroup, String owner, List<String> rewards) {
        this.name = name;
        this.type = type;
        this.enabled = enabled;
        this.oneTime = oneTime;
        this.exclusiveGroup = exclusiveGroup;
        this.owner = owner;
        this.rewards = rewards;
    }

    public String getName() {
        return name;
    }

    public CodeType getType() {
        return type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public String getExclusiveGroup() {
        return exclusiveGroup == null ? "" : exclusiveGroup;
    }

    public String getOwner() {
        return owner == null ? "" : owner;
    }

    public List<String> getRewards() {
        return rewards;
    }
}
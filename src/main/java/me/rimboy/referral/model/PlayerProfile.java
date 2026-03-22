package me.rimboy.referral.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerProfile {

    private final UUID uuid;
    private final List<String> usedCodes;
    private final Map<String, String> exclusiveGroups;

    public PlayerProfile(UUID uuid, List<String> usedCodes, Map<String, String> exclusiveGroups) {
        this.uuid = uuid;
        this.usedCodes = usedCodes;
        this.exclusiveGroups = exclusiveGroups;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getUsedCodes() {
        return usedCodes;
    }

    public Map<String, String> getExclusiveGroups() {
        return exclusiveGroups;
    }
}
/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_268
 *  net.minecraft.class_310
 *  net.minecraft.class_634
 *  net.minecraft.class_640
 *  net.minecraft.class_746
 *  net.minecraft.class_8685
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.FriendManager;
import com.papack.maceattackassistance.client.WorldPlayerData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_268;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_640;
import net.minecraft.class_746;
import net.minecraft.class_8685;

public class WorldPlayerManager {
    private static final List<WorldPlayerData> worldPlayers = new ArrayList<WorldPlayerData>();

    public static void clear() {
        worldPlayers.clear();
    }

    public static void setWorldPlayers(class_310 client) {
        class_634 networkHandler = client.method_1562();
        if (networkHandler != null) {
            Collection entryCollection = networkHandler.method_45732();
            for (class_640 entry : entryCollection) {
                if (entry == null) continue;
                UUID uuid = entry.method_2966().id();
                WorldPlayerManager.addPlayer(entry.method_2966().name(), uuid, entry.method_52810(), entry.method_2955(), FriendManager.isFriend(uuid));
            }
        }
    }

    public static void addPlayer(String name, UUID uuid, class_8685 skin, class_268 team, boolean friend) {
        class_746 player = class_310.method_1551().field_1724;
        if (player != null && !player.method_5667().equals(uuid) && !WorldPlayerManager.isContainsUuid(uuid)) {
            worldPlayers.add(new WorldPlayerData(name, uuid, skin, team, friend));
        }
    }

    public static boolean isContainsName(String name) {
        return worldPlayers.stream().anyMatch(f -> f.name().equals(name));
    }

    public static boolean isContainsUuid(UUID uuid) {
        return worldPlayers.stream().anyMatch(f -> f.uuid().equals(uuid));
    }

    public static UUID getUUID(String name) {
        for (WorldPlayerData playerData : worldPlayers) {
            if (!playerData.name().equals(name)) continue;
            return playerData.uuid();
        }
        return null;
    }

    public static WorldPlayerData getPlayerByName(String name) {
        for (WorldPlayerData playerData : worldPlayers) {
            if (!playerData.name().equals(name)) continue;
            return playerData;
        }
        return null;
    }

    public static boolean removePlayerByName(String name) {
        return worldPlayers.removeIf(p -> p.name().equals(name));
    }

    public static boolean removePlayerByUuid(UUID uuid) {
        return worldPlayers.removeIf(p -> p.uuid().equals(uuid));
    }

    public static List<WorldPlayerData> getWorldPlayers() {
        return worldPlayers;
    }

    public static class_268 getTeam(UUID uuid) {
        for (WorldPlayerData playerData : worldPlayers) {
            if (!playerData.uuid().equals(uuid)) continue;
            return playerData.team();
        }
        return null;
    }

    public static List<WorldPlayerData> getSortedList() {
        ArrayList<WorldPlayerData> friendList = new ArrayList<WorldPlayerData>();
        ArrayList<WorldPlayerData> notFriendList = new ArrayList<WorldPlayerData>();
        for (WorldPlayerData data : worldPlayers) {
            if (data.friend()) {
                friendList.add(data);
                continue;
            }
            notFriendList.add(data);
        }
        WorldPlayerManager.clear();
        worldPlayers.addAll(friendList);
        worldPlayers.addAll(notFriendList);
        return worldPlayers;
    }
}


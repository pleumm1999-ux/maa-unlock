/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1041
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_310
 *  net.minecraft.class_3675
 *  net.minecraft.class_437
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.FriendListScreen;
import com.papack.maceattackassistance.client.FriendManager;
import com.papack.maceattackassistance.client.SwitchAssist;
import com.papack.maceattackassistance.client.TickScheduler;
import com.papack.maceattackassistance.client.WorldPlayerManager;
import net.minecraft.class_1041;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import net.minecraft.class_746;

public class FriendKeyHandler {
    private static int wait = 0;

    public static void tick() {
        if (wait > 0) {
            --wait;
        }
    }

    public static void friendKeyHandler(class_310 client) {
        boolean flag;
        class_746 player = client.field_1724;
        class_1041 handle = client.method_22683();
        if (player == null) {
            return;
        }
        boolean sneak = client.field_1690.field_1832.method_1434();
        boolean ctrl = client.field_1690.field_1867.method_1434();
        boolean alt = class_3675.method_15987((class_1041)handle, (int)342);
        boolean bl = flag = ctrl || sneak || alt;
        if (wait == 0) {
            if (!flag) {
                WorldPlayerManager.clear();
                WorldPlayerManager.setWorldPlayers(client);
                TickScheduler.setDelayTask(1, () -> client.method_1507((class_437)new FriendListScreen(client.field_1755)));
            } else if (!sneak && !ctrl) {
                SwitchAssist.changeSettings(SwitchAssist.SwitchKey.FRIEND_PROTECTION);
            } else {
                class_1657 other;
                class_1297 target = client.field_1692;
                if (target instanceof class_1657 && (other = (class_1657)target) != player) {
                    if (!ctrl && !alt) {
                        FriendManager.removeFriend(other.method_5667());
                        SwitchAssist.displayMessage("Removed: " + other.method_5477().getString());
                    } else if (!sneak && !alt) {
                        FriendManager.addFriend(other.method_5477().getString(), other.method_5667());
                        SwitchAssist.displayMessage("Added: " + other.method_5477().getString());
                    }
                }
            }
            wait = 20;
        }
    }
}


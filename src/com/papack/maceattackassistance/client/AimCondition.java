/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_2338;
import net.minecraft.class_746;

public class AimCondition {
    private static boolean flagFallThreshold;

    public static void tick(class_746 clientPlayer) {
        double vy = clientPlayer.method_18798().method_10214();
        class_2338 entityPos = clientPlayer.method_24515();
        if (!flagFallThreshold && !clientPlayer.method_24828() && vy < 0.0) {
            int threshold = Config.AIM_FALL_THRESHOLD - 1;
            if (AimCondition.playerCondition(clientPlayer) || Utils.verifyGround(clientPlayer, threshold)) {
                flagFallThreshold = true;
            }
        }
        if (flagFallThreshold && (vy >= 0.0 || clientPlayer.method_24828())) {
            flagFallThreshold = false;
        }
    }

    public static boolean canAim() {
        return flagFallThreshold;
    }

    private static boolean playerCondition(class_746 clientPlayer) {
        return JobManager.checkStatus(StatusType.DOUBLE_TAP) || clientPlayer.method_6128() || Utils.speedOverThreshold(clientPlayer) > 0;
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1781
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1781;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_746;

@Environment(value=EnvType.CLIENT)
public class RocketBlitz {
    public static int getRocketSlotId(class_746 clientPlayer) {
        int hotBarSlotId = -1;
        if (Config.ROCKET_BLITZ_SLOT != 0) {
            class_1799 itemStack = clientPlayer.method_31548().method_5438(Config.ROCKET_BLITZ_SLOT - 1);
            if (RocketBlitz.isRocket(itemStack)) {
                hotBarSlotId = Config.ROCKET_BLITZ_SLOT - 1;
            }
        } else {
            hotBarSlotId = Utils.findItemInHotbar(class_1781.class, true);
        }
        return hotBarSlotId;
    }

    private static boolean isRocket(class_1799 itemStack) {
        return itemStack.method_31574(class_1802.field_8639);
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1799
 *  net.minecraft.class_1887
 *  net.minecraft.class_1893
 *  net.minecraft.class_5321
 *  net.minecraft.class_746
 *  net.minecraft.class_9362
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_1887;
import net.minecraft.class_1893;
import net.minecraft.class_5321;
import net.minecraft.class_746;
import net.minecraft.class_9362;

@Environment(value=EnvType.CLIENT)
public class HotSwap {
    public static int getPrimaryMaceSlotId(class_746 clientPlayer) {
        int hotBarSlotId = -1;
        if (Config.MACE_PRIMARY > -1) {
            class_1799 itemStack = clientPlayer.method_31548().method_5438(Config.MACE_PRIMARY);
            if (HotSwap.isMace(itemStack)) {
                hotBarSlotId = Config.MACE_PRIMARY;
            }
        } else {
            hotBarSlotId = Utils.findItemInHotbar(class_9362.class, true);
        }
        return hotBarSlotId;
    }

    public static boolean isMace(class_1799 itemStack) {
        return itemStack.method_7909() instanceof class_9362;
    }

    public static int getBreachMaceSlotId(class_746 clientPlayer) {
        int hotBarSlotId = -1;
        if (Config.MACE_BREACH > -1 && Config.MACE_BREACH < 9) {
            class_1799 itemStack = clientPlayer.method_31548().method_5438(Config.MACE_BREACH);
            if (HotSwap.isBreachMace(clientPlayer, itemStack)) {
                hotBarSlotId = Config.MACE_BREACH;
            }
        } else {
            hotBarSlotId = Utils.findItemInHotbar(class_9362.class, false, (class_5321<class_1887>)class_1893.field_50158);
        }
        return hotBarSlotId;
    }

    public static boolean isBreachMace(class_746 clientPlayer, class_1799 itemStack) {
        if (!(itemStack.method_7909() instanceof class_9362)) {
            return false;
        }
        return Utils.getEnchantLevel(clientPlayer, itemStack, (class_5321<class_1887>)class_1893.field_50158) > 0;
    }
}


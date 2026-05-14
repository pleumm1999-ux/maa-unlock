/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1304
 *  net.minecraft.class_1661
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_310
 *  net.minecraft.class_3489
 *  net.minecraft.class_636
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.AutoRefill;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_636;
import net.minecraft.class_746;

@Environment(value=EnvType.CLIENT)
public class ToggleElytra {
    public static int toggleElytra() {
        int endSlot;
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        class_636 interactionManager = client.field_1761;
        if (clientPlayer == null || interactionManager == null) {
            return -1;
        }
        int airSlot = -1;
        int armorSlot = -1;
        int elytraSlot = -1;
        int targetSlot = -1;
        int n = endSlot = Config.ALSO_SEARCH_INVENTORY ? 36 : 9;
        if (Config.TOGGLE_SLOT != 0) {
            class_1799 itemStack = clientPlayer.method_31548().method_5438(Config.TOGGLE_SLOT - 1);
            if (ToggleElytra.isElytra(itemStack) || ToggleElytra.isAir(itemStack) || ToggleElytra.isChestPlate(itemStack)) {
                targetSlot = (Config.ELYTRA_MANUAL_MODE ? 0 : 36) + Config.TOGGLE_SLOT - 1;
            }
        } else {
            class_1661 inventory = clientPlayer.method_31548();
            for (int i = 0; i < endSlot; ++i) {
                class_1799 stack = inventory.method_5438(i);
                if (airSlot == -1 && ToggleElytra.isAir(stack)) {
                    airSlot = i;
                }
                if (armorSlot == -1 && ToggleElytra.isChestPlate(stack)) {
                    armorSlot = i;
                }
                if (elytraSlot != -1 || !ToggleElytra.isElytra(stack)) continue;
                elytraSlot = i;
            }
            class_1799 currEquip = clientPlayer.method_6118(class_1304.field_6174);
            if (ToggleElytra.isElytra(currEquip)) {
                int n2 = targetSlot = armorSlot > -1 ? armorSlot : airSlot;
            }
            if (ToggleElytra.isChestPlate(currEquip)) {
                targetSlot = elytraSlot;
            }
            if (ToggleElytra.isAir(currEquip)) {
                int n3 = targetSlot = elytraSlot > -1 ? elytraSlot : armorSlot;
            }
        }
        if (Config.ELYTRA_MANUAL_MODE) {
            if (targetSlot > -1 && Utils.isHotBar(targetSlot)) {
                return targetSlot;
            }
        } else if (targetSlot > -1) {
            AutoRefill.equipmentSwap(interactionManager, clientPlayer, targetSlot);
        }
        return -1;
    }

    public static boolean isElytra(class_1799 itemStack) {
        return itemStack.method_31574(class_1802.field_8833);
    }

    private static boolean isAir(class_1799 itemStack) {
        return itemStack.method_31574(class_1802.field_8162);
    }

    public static boolean isChestPlate(class_1799 itemStack) {
        return itemStack.method_31573(class_3489.field_48296);
    }
}


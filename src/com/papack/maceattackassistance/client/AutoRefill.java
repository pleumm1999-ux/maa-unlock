/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1713
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_636
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_636;
import net.minecraft.class_746;

public class AutoRefill {
    public static void autoRefill(class_636 interactionManager, class_746 clientPlayer, int slot) {
        if (slot < 0 || clientPlayer.method_68878()) {
            return;
        }
        int containerId = clientPlayer.field_7498.field_7763;
        interactionManager.method_2906(containerId, slot, 0, class_1713.field_7790, (class_1657)clientPlayer);
        interactionManager.method_2906(containerId, slot, 0, class_1713.field_7793, (class_1657)clientPlayer);
        interactionManager.method_2906(containerId, slot, 0, class_1713.field_7790, (class_1657)clientPlayer);
    }

    public static void autoRefillInventory(class_636 interactionManager, class_746 clientPlayer, int inventory, int hotbar) {
        if (inventory < 9 || inventory > 35) {
            return;
        }
        if (hotbar < 36 || hotbar > 44) {
            return;
        }
        if (clientPlayer.method_68878()) {
            return;
        }
        int containerId = clientPlayer.field_7498.field_7763;
        interactionManager.method_2906(containerId, inventory, 0, class_1713.field_7790, (class_1657)clientPlayer);
        interactionManager.method_2906(containerId, inventory, 0, class_1713.field_7793, (class_1657)clientPlayer);
        interactionManager.method_2906(containerId, hotbar, 0, class_1713.field_7790, (class_1657)clientPlayer);
        interactionManager.method_2906(containerId, inventory, 0, class_1713.field_7790, (class_1657)clientPlayer);
    }

    public static boolean checkStockQuantity(class_746 clientPlayer, int slot) {
        if (slot < 0 || clientPlayer.method_68878()) {
            return false;
        }
        class_1799 itemStack = slot == 9 ? clientPlayer.method_6079() : clientPlayer.method_31548().method_5438(slot);
        if (itemStack.method_7914() == itemStack.method_7947()) {
            return false;
        }
        class_1792 item = itemStack.method_7909();
        return itemStack.method_7946() && AutoRefill.checkStockQuantity(clientPlayer, item);
    }

    public static boolean checkStockQuantity(class_746 clientPlayer, class_1792 item) {
        if (clientPlayer.method_68878()) {
            return false;
        }
        class_1661 inventory = clientPlayer.method_31548();
        for (int i = 9; i < 36; ++i) {
            if (!inventory.method_5438(i).method_31574(item)) continue;
            return true;
        }
        return false;
    }

    public static void equipmentSwap(class_636 interactionManager, class_746 clientPlayer, int targetSlot) {
        int containerId = clientPlayer.field_7498.field_7763;
        int equipmentSlot = 6;
        targetSlot = targetSlot < 9 ? targetSlot + 36 : targetSlot;
        interactionManager.method_2906(containerId, targetSlot, 0, class_1713.field_7790, (class_1657)clientPlayer);
        interactionManager.method_2906(containerId, equipmentSlot, 0, class_1713.field_7790, (class_1657)clientPlayer);
        interactionManager.method_2906(containerId, targetSlot, 0, class_1713.field_7790, (class_1657)clientPlayer);
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1792
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3489
 *  net.minecraft.class_3675$class_306
 *  net.minecraft.class_6862
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.HotSwap;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.PrevSlotManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import com.papack.maceattackassistance.mixin.KeyBindingInvoker;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_3675;
import net.minecraft.class_6862;
import net.minecraft.class_746;

public class SpearAttacks {
    public static void spearAssist(class_310 client, class_746 clientPlayer) {
        if (ZoomState.KeyManager.keyManager()) {
            return;
        }
        if (Config.SPEAR_ASSIST && client.field_1690.field_1886.method_1434()) {
            boolean isThereTarget;
            int currentSlot = clientPlayer.method_31548().method_67532();
            int weaponSlot = -1;
            boolean isFalling = clientPlayer.method_18798().method_10214() < -0.447;
            class_1297 target = client.field_1692;
            boolean bl = isThereTarget = Utils.getLivingEntityInView(clientPlayer, 0.0, 4.5) != null || target != null || !Config.WEAPON_SWING;
            if (ZoomState.MAAClientState.antiCheat) {
                return;
            }
            if (JobManager.jumpOption() && target instanceof class_1657) {
                return;
            }
            if (client.field_1690.field_1886.method_1434() && Utils.waitingToAttack(clientPlayer)) {
                if (Utils.isSpear(clientPlayer) && isThereTarget) {
                    if (isFalling) {
                        weaponSlot = HotSwap.getPrimaryMaceSlotId(clientPlayer);
                    } else if (!client.field_1690.field_1867.method_1434()) {
                        weaponSlot = Utils.findItemInHotbarByTags((class_6862<class_1792>)class_3489.field_42611);
                    }
                } else if (!isFalling && Utils.getLivingEntityInView(clientPlayer, 3.0, 4.5) != null) {
                    SpearAttacks.autoLungeAttack(client, clientPlayer);
                }
                if (weaponSlot > -1) {
                    class_304.method_1420((class_3675.class_306)((KeyBindingInvoker)client.field_1690.field_1852[weaponSlot]).accessorBoundKey());
                    if (currentSlot != weaponSlot && Config.RETURN_TO_PREV_SLOT) {
                        PrevSlotManager.setPrevSlot(StatusType.NONE, currentSlot, 2);
                    }
                }
            }
        }
    }

    public static void manualLungeAttack(class_310 client, class_746 clientPlayer) {
        int currentSlot = clientPlayer.method_31548().method_67532();
        int spearSlot = Utils.findItemInHotbarByTags((class_6862<class_1792>)class_3489.field_63257);
        if (spearSlot > -1) {
            class_304.method_1420((class_3675.class_306)((KeyBindingInvoker)client.field_1690.field_1886).accessorBoundKey());
            class_304.method_1420((class_3675.class_306)((KeyBindingInvoker)client.field_1690.field_1852[spearSlot]).accessorBoundKey());
            if (currentSlot != spearSlot && Config.RETURN_TO_PREV_SLOT) {
                PrevSlotManager.setPrevSlot(StatusType.NONE, currentSlot, 2);
            }
        }
    }

    public static void autoLungeAttack(class_310 client, class_746 clientPlayer) {
        int currentSlot = clientPlayer.method_31548().method_67532();
        int spearSlot = Utils.findItemInHotbarByTags((class_6862<class_1792>)class_3489.field_63257);
        if (spearSlot > -1) {
            class_304.method_1420((class_3675.class_306)((KeyBindingInvoker)client.field_1690.field_1852[spearSlot]).accessorBoundKey());
            if (currentSlot != spearSlot && Config.RETURN_TO_PREV_SLOT) {
                PrevSlotManager.setPrevSlot(StatusType.NONE, currentSlot, 2);
            }
        }
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1661
 *  net.minecraft.class_1743
 *  net.minecraft.class_1799
 *  net.minecraft.class_310
 *  net.minecraft.class_636
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.HotSwap;
import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.PrevSlotManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.config.Config;
import com.papack.maceattackassistance.mixin.ClientPlayerInteractionManagerInvoker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1661;
import net.minecraft.class_1743;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_636;
import net.minecraft.class_746;

@Environment(value=EnvType.CLIENT)
public class StunSlam {
    public static int CHK_PLAYER_AGE = 0;
    public static boolean flagPreAxeCalled = false;
    public static int lastSlot = -1;

    public static boolean preSelectAxe(class_310 client, class_746 clientPlayer, class_1297 target) {
        flagPreAxeCalled = true;
        class_636 interactionManager = client.field_1761;
        class_1661 playerInventory = clientPlayer.method_31548();
        class_1799 mainHandStack = clientPlayer.method_6047();
        boolean mainHandItemIsAxe = StunSlam.isAxe(mainHandStack);
        if (Config.STUN_SLAMMING && interactionManager != null && target != null && clientPlayer.method_18798().method_10214() < 0.0) {
            try {
                if (target instanceof class_1309) {
                    int axeSlot;
                    class_1309 livingEntity = (class_1309)target;
                    MaceAttackAssistanceClient.flagPreAxe = MaceAttackAssistanceClient.shouldShieldBreak = livingEntity.method_6039();
                    if (MaceAttackAssistanceClient.shouldShieldBreak && !mainHandItemIsAxe && (axeSlot = StunSlam.getAxeSlotId(clientPlayer)) > -1) {
                        PrevSlotManager.setPrevSlot(StatusType.NONE, playerInventory.method_67532(), 0);
                        playerInventory.method_61496(axeSlot);
                        ((ClientPlayerInteractionManagerInvoker)interactionManager).syncSelectedSlotInvoker();
                    }
                    return MaceAttackAssistanceClient.shouldShieldBreak;
                }
            }
            catch (Exception e) {
                MaceAttackAssistanceClient.LOGGER.info(e.getMessage());
            }
        }
        return false;
    }

    public static int getAxeSlotId(class_746 clientPlayer) {
        int hotBarSlotId;
        block3: {
            block2: {
                hotBarSlotId = -1;
                if (Config.AXE_SLOT <= -1) break block2;
                class_1799 itemStack = clientPlayer.method_31548().method_5438(Config.AXE_SLOT);
                if (!StunSlam.isAxe(itemStack)) break block3;
                hotBarSlotId = Config.AXE_SLOT;
                break block3;
            }
            for (int i = 0; i < 9; ++i) {
                class_1799 itemStack = clientPlayer.method_31548().method_5438(i);
                if (!StunSlam.isAxe(itemStack)) continue;
                hotBarSlotId = i;
                break;
            }
        }
        if (Config.SHIELD_DRAINING && hotBarSlotId == -1 && clientPlayer.method_18798().method_10214() < Config.VELOCITY_BY_DISTANCE[2]) {
            hotBarSlotId = HotSwap.getPrimaryMaceSlotId(clientPlayer);
        }
        return hotBarSlotId;
    }

    public static boolean isAxe(class_1799 itemStack) {
        return itemStack.method_7909() instanceof class_1743;
    }
}


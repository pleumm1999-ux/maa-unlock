/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1269
 *  net.minecraft.class_1309
 *  net.minecraft.class_1588
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.HotSwap;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.StunSlam;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_1269;
import net.minecraft.class_1309;
import net.minecraft.class_1588;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_746;

public class MacroController {
    /*
     * Enabled aggressive block sorting
     */
    public static class_1269 macroController(class_746 clientPlayer, class_1937 world, class_1309 livingEntity, int highSpeed) {
        StatusType selector = StatusType.NONE;
        if (world.method_8608() && JobManager.checkOrderIsEmpty()) {
            if (Config.HOT_SWAP) {
                if (Config.SNAPBACK && MacroController.canSnapback(clientPlayer, livingEntity)) {
                    if (!MacroController.isActiveShield(livingEntity)) {
                        selector = StatusType.AIR_BREACH;
                        JobManager.setOrder(selector, clientPlayer.method_31548().method_67532());
                        return class_1269.field_5814;
                    }
                    selector = StatusType.AIR_SHIELD_BREACH;
                } else if (clientPlayer.method_18798().method_10214() > -0.447) {
                    if ((!Config.BREACH_LIMITED || livingEntity instanceof class_1588 || livingEntity instanceof class_1657) && MacroController.isActiveShield(livingEntity)) {
                        selector = StatusType.STUN_SLAM;
                    }
                } else if (MacroController.isActiveShield(livingEntity)) {
                    if (StunSlam.isAxe(clientPlayer.method_6047())) {
                        switch (highSpeed) {
                            case 2: {
                                selector = StatusType.VERY_HIGH_SPEED;
                                break;
                            }
                            case 1: {
                                selector = StatusType.HIGH_SPEED;
                                break;
                            }
                            case 0: {
                                selector = StatusType.STUN_SLAM;
                                break;
                            }
                        }
                    } else {
                        selector = StatusType.NORMAL;
                    }
                } else {
                    selector = Config.DOUBLE_TAP && clientPlayer.method_6128() ? StatusType.DOUBLE_TAP : StatusType.HOT_SWAP;
                }
            }
            if (JobManager.jumpOption() && livingEntity instanceof class_1657) {
                selector = StatusType.NONE;
            }
            if (selector != StatusType.NONE) {
                int slot = JobManager.beforeAxeSlot > -1 ? JobManager.beforeAxeSlot : clientPlayer.method_31548().method_67532();
                JobManager.setOrder(selector, slot);
            }
        }
        return class_1269.field_5811;
    }

    public static boolean isActiveShield(class_1309 livingEntity) {
        return Config.STUN_SLAMMING && livingEntity.method_6039();
    }

    public static boolean canSnapback(class_746 clientPlayer, class_1309 livingEntity) {
        if (HotSwap.getBreachMaceSlotId(clientPlayer) < 0) {
            return false;
        }
        if (livingEntity.method_24828()) {
            return false;
        }
        if (!Utils.verifyGround(clientPlayer, Config.SNAPBACK_THRESHOLD)) {
            return false;
        }
        if (!Utils.verifyGround(livingEntity, 2)) {
            return false;
        }
        if (clientPlayer.method_6128()) {
            return false;
        }
        if (livingEntity.method_6128()) {
            return false;
        }
        double playerYV = clientPlayer.method_18798().method_10214();
        double targetYV = livingEntity.method_18798().method_10214();
        if (playerYV > 0.0) {
            return false;
        }
        if (playerYV < Config.VELOCITY_BY_DISTANCE[13]) {
            return false;
        }
        return targetYV < playerYV + (double)Config.SNAPBACK_TOLERANCE * 0.01;
    }
}


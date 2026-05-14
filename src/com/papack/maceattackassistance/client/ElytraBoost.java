/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1747
 *  net.minecraft.class_1755
 *  net.minecraft.class_1776
 *  net.minecraft.class_1777
 *  net.minecraft.class_1786
 *  net.minecraft.class_1787
 *  net.minecraft.class_1792
 *  net.minecraft.class_1794
 *  net.minecraft.class_1799
 *  net.minecraft.class_1801
 *  net.minecraft.class_1802
 *  net.minecraft.class_1806
 *  net.minecraft.class_1811
 *  net.minecraft.class_1812
 *  net.minecraft.class_1819
 *  net.minecraft.class_1821
 *  net.minecraft.class_1823
 *  net.minecraft.class_1840
 *  net.minecraft.class_1843
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_3489
 *  net.minecraft.class_4537
 *  net.minecraft.class_638
 *  net.minecraft.class_746
 *  net.minecraft.class_9239
 *  net.minecraft.class_9334
 *  net.minecraft.class_9463
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.Debug;
import com.papack.maceattackassistance.client.DebugScreen;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.WallClimbing;
import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_1755;
import net.minecraft.class_1776;
import net.minecraft.class_1777;
import net.minecraft.class_1786;
import net.minecraft.class_1787;
import net.minecraft.class_1792;
import net.minecraft.class_1794;
import net.minecraft.class_1799;
import net.minecraft.class_1801;
import net.minecraft.class_1802;
import net.minecraft.class_1806;
import net.minecraft.class_1811;
import net.minecraft.class_1812;
import net.minecraft.class_1819;
import net.minecraft.class_1821;
import net.minecraft.class_1823;
import net.minecraft.class_1840;
import net.minecraft.class_1843;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_4537;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_9239;
import net.minecraft.class_9334;
import net.minecraft.class_9463;

public class ElytraBoost {
    private static class_1268 windChargeHand;
    private static int cooldownCounter;
    public static boolean flag_elytra_boost;

    public static void elytraBoost(class_310 client, class_746 clientPlayer) {
        if (cooldownCounter > 0) {
            --cooldownCounter;
        }
        if (Config.ELYTRA_BOOST && cooldownCounter == 0 && clientPlayer.method_6128() && client.field_1690.field_1903.method_1434() && (!client.field_1690.field_1886.method_1434() || client.field_1692 == null) && JobManager.checkOrderIsEmpty()) {
            class_638 world = client.field_1687;
            if (world == null) {
                return;
            }
            class_243 playerVelocity = clientPlayer.method_18798();
            double flyingSpeed = playerVelocity.method_1033();
            double horizontalSpeed = ElytraBoost.getHorizontalSpeed(playerVelocity);
            if (flyingSpeed >= 0.173 && horizontalSpeed >= 0.27 && ElytraBoost.shouldTrigger(client, clientPlayer) && windChargeHand != null) {
                double y = clientPlayer.method_23318();
                double fractional = y - Math.floor(y);
                double vy = -playerVelocity.method_10214();
                int tick = vy != 0.0 ? (int)Math.ceil(fractional / vy) : 0;
                DebugScreen.lastY = y;
                Debug.previous_y = -64.0;
                ElytraBoost.elytraBoostValueSetting(tick);
                cooldownCounter = Config.COOL_DOWN_TICKS;
                JobManager.setOrder(StatusType.ELYTRA_BOOST, clientPlayer.method_31548().method_67532());
            }
        }
    }

    private static boolean shouldTrigger(class_310 client, class_746 clientPlayer) {
        WallClimbing.ClimbingStatus climbingStatus;
        windChargeHand = Utils.getHandHoldingWindCharge(client, clientPlayer);
        if (windChargeHand == null) {
            if (Config.AUTO_WIND_CHARGE_SELECT) {
                if (Utils.isSuccessFoundItItemInHotbar(class_9239.class, true)) {
                    windChargeHand = class_1268.field_5808;
                }
            } else {
                return false;
            }
        }
        if ((climbingStatus = WallClimbing.getClimbingStatus(clientPlayer, -1)).canClimbing() && climbingStatus.offset() == 1) {
            WallClimbing.resetClimbingStatus();
            return true;
        }
        return false;
    }

    private static void elytraBoostValueSetting(int tick) {
        int boostThrow;
        JobManager.setBoostValue(switch (tick) {
            case 0, 1, 2, 3 -> {
                boostThrow = 5;
                yield 4;
            }
            default -> {
                boostThrow = 4;
                yield 3;
            }
        }, boostThrow);
    }

    public static double getHorizontalSpeed(class_243 velocity) {
        double vx = velocity.method_10216();
        double vz = velocity.method_10215();
        return Math.sqrt(vx * vx + vz * vz);
    }

    public static boolean isNotUsableItems(class_310 client, class_1799 itemStack) {
        class_746 player = client.field_1724;
        if (player == null) {
            return false;
        }
        class_1792 item = itemStack.method_7909();
        class_239 hitResult = client.field_1765;
        class_2338 targetPos = null;
        boolean result = hitResult != null;
        boolean isCooldown = ElytraBoost.isCooldown(player, itemStack);
        if (isCooldown) {
            return true;
        }
        if (result) {
            targetPos = class_2338.method_49638((class_2374)hitResult.method_17784());
        }
        if (result && hitResult.method_17783() != class_239.class_240.field_1333) {
            if (item instanceof class_1794) {
                return false;
            }
            if (item instanceof class_1821) {
                return false;
            }
        }
        if (item instanceof class_1819) {
            return false;
        }
        if (item instanceof class_9463) {
            return false;
        }
        if (item instanceof class_1811) {
            return false;
        }
        if (item instanceof class_1776) {
            return false;
        }
        if (item instanceof class_1777) {
            return false;
        }
        if (item instanceof class_1823) {
            return false;
        }
        if (item instanceof class_1755) {
            return false;
        }
        if (item instanceof class_1787) {
            return false;
        }
        if (item instanceof class_1786) {
            return false;
        }
        if (item instanceof class_1812) {
            return false;
        }
        if (item instanceof class_4537) {
            return false;
        }
        if (item instanceof class_1840) {
            return false;
        }
        if (item instanceof class_1843) {
            return false;
        }
        if (item instanceof class_1801) {
            return false;
        }
        if (item instanceof class_1806) {
            return false;
        }
        if (itemStack.method_31573(class_3489.field_48296)) {
            return false;
        }
        if (itemStack.method_31573(class_3489.field_48297)) {
            return false;
        }
        if (itemStack.method_31573(class_3489.field_48295)) {
            return false;
        }
        if (itemStack.method_31573(class_3489.field_48294)) {
            return false;
        }
        if (itemStack.method_31574(class_1802.field_8833)) {
            return false;
        }
        if (item.method_7876().contains("_bucket")) {
            return false;
        }
        if (itemStack.method_58694(class_9334.field_50075) != null) {
            return false;
        }
        return targetPos == null || !(item instanceof class_1747);
    }

    public static boolean isElytraBoostIdle() {
        return cooldownCounter <= 0;
    }

    private static boolean isCooldown(class_746 player, class_1799 itemStack) {
        if (itemStack.method_31574(class_1802.field_8162)) {
            return true;
        }
        return player.method_7357().method_7904(itemStack);
    }

    static {
        cooldownCounter = 0;
        flag_elytra_boost = false;
    }
}


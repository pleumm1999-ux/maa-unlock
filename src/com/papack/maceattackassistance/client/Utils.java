/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1304$class_1305
 *  net.minecraft.class_1309
 *  net.minecraft.class_1508
 *  net.minecraft.class_1510
 *  net.minecraft.class_1657
 *  net.minecraft.class_1675
 *  net.minecraft.class_1684
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1887
 *  net.minecraft.class_1937
 *  net.minecraft.class_2189
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_310
 *  net.minecraft.class_3489
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_5321
 *  net.minecraft.class_638
 *  net.minecraft.class_6862
 *  net.minecraft.class_6880
 *  net.minecraft.class_6880$class_6883
 *  net.minecraft.class_746
 *  net.minecraft.class_7924
 *  net.minecraft.class_9239
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.ElytraBoost;
import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.config.Config;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1508;
import net.minecraft.class_1510;
import net.minecraft.class_1657;
import net.minecraft.class_1675;
import net.minecraft.class_1684;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1887;
import net.minecraft.class_1937;
import net.minecraft.class_2189;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_5321;
import net.minecraft.class_638;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import net.minecraft.class_7924;
import net.minecraft.class_9239;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Utils {
    public static boolean canDoubleTap() {
        class_746 clientPlayer = class_310.method_1551().field_1724;
        if (clientPlayer == null) {
            return false;
        }
        if (!Config.DOUBLE_TAP) {
            return false;
        }
        return clientPlayer.method_6128();
    }

    public static boolean checkPlayerUUID(class_1297 entity) {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        boolean chkUuid = false;
        if (clientPlayer != null) {
            chkUuid = entity.method_5667().equals(clientPlayer.method_5667());
        }
        return chkUuid;
    }

    public static boolean isInAttackableRange(class_746 clientPlayer, class_1297 targetEntity) {
        if (clientPlayer != null && targetEntity != null) {
            double dz;
            double dx = clientPlayer.method_23317() - targetEntity.method_23317();
            return Math.sqrt(dx * dx + (dz = clientPlayer.method_23321() - targetEntity.method_23321()) * dz) <= 3.0;
        }
        return false;
    }

    public static boolean isNotUsingElytra(class_746 clientPlayer) {
        return !clientPlayer.method_6128() || !clientPlayer.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
    }

    public static boolean isOnSlimeBlock(class_746 clientPlayer) {
        class_2338 pos = class_2338.method_49637((double)clientPlayer.method_23317(), (double)(clientPlayer.method_23318() - 1.0), (double)clientPlayer.method_23321());
        class_2680 state = clientPlayer.method_73183().method_8320(pos);
        return state.method_27852(class_2246.field_10030);
    }

    public static boolean isSimpleVisibleFromPlayer(class_310 client, class_746 clientPlayer, class_1297 target) {
        class_243 playerEyePos = clientPlayer.method_33571();
        class_243 targetEyePos = target.method_33571();
        class_638 world = client.field_1687;
        if (world != null) {
            class_3965 hitResultEye = world.method_17742(new class_3959(playerEyePos, targetEyePos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)clientPlayer));
            return hitResultEye.method_17783() != class_239.class_240.field_1332;
        }
        return false;
    }

    public static boolean isUsingElytra(class_746 clientPlayer) {
        return clientPlayer.method_6128() || clientPlayer.method_6118(class_1304.field_6174).method_7909().equals(class_1802.field_8833.method_8389());
    }

    public static boolean isVisibleFromPlayer(class_310 client, class_746 clientPlayer, class_1297 target) {
        class_243 playerEyePos = clientPlayer.method_33571();
        class_243 targetEyePos = target.method_33571();
        class_243 targetCenterPos = target.method_73189();
        class_638 world = client.field_1687;
        if (world != null) {
            class_3965 hitResultEye = world.method_17742(new class_3959(playerEyePos, targetEyePos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)clientPlayer));
            class_3965 hitResultCenter = world.method_17742(new class_3959(playerEyePos, targetCenterPos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)clientPlayer));
            return hitResultEye.method_17783() != class_239.class_240.field_1332 || hitResultCenter.method_17783() != class_239.class_240.field_1332;
        }
        return false;
    }

    public static boolean shouldRenderCustomCrosshair() {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer == null) {
            return false;
        }
        if (Config.CROSSHAIR_MODE == Config.CrosshairMode.Off) {
            return false;
        }
        if (Config.CROSSHAIR_MODE == Config.CrosshairMode.While_Gliding) {
            return clientPlayer.method_6128();
        }
        return clientPlayer.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
    }

    public static boolean verifyGround(class_746 clientPlayer, int height) {
        return Utils.verifyGround((class_1309)clientPlayer, height);
    }

    public static boolean verifyGround(class_1309 livingEntity, int height) {
        if (height < 1) {
            return true;
        }
        class_1937 world = livingEntity.method_73183();
        class_2338 entityPos = livingEntity.method_24515();
        for (int i = 1; i <= height; ++i) {
            class_2338 footPosition = entityPos.method_10069(0, -1 * i, 0);
            if (world.method_8320(footPosition).method_26204() instanceof class_2189) continue;
            return false;
        }
        return true;
    }

    public static double xzDistance(class_746 clientPlayer, class_1297 target) {
        double dx = clientPlayer.method_23317() - target.method_23317();
        double dz = clientPlayer.method_23321() - target.method_23321();
        return Math.sqrt(dx * dx + dz * dz);
    }

    public static double xzSquareDistance(class_243 futurePos, class_243 targetPos) {
        double dx = futurePos.method_10216() - targetPos.method_10216();
        double dz = futurePos.method_10215() - targetPos.method_10215();
        return dx * dx + dz * dz;
    }

    public static float lerpPitch(float start, float end, float delta) {
        return start + delta * (end - start);
    }

    public static float lerpYaw(float start, float end, float delta) {
        return start + delta * Utils.normalizeAngle(end - start);
    }

    public static float normalizeAngle(float angle) {
        if ((angle %= 360.0f) > 180.0f) {
            angle -= 360.0f;
        } else if (angle < -180.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    public static class_1268 getHandHoldingWindCharge(class_310 client, class_746 clientPlayer) {
        class_1799 mainHandStack = clientPlayer.method_6047();
        if (mainHandStack.method_31574(class_1802.field_49098)) {
            return class_1268.field_5808;
        }
        if (clientPlayer.method_6079().method_31574(class_1802.field_49098) && (Config.PRIORITIZE_WIND_CHARGE || ElytraBoost.isNotUsableItems(client, mainHandStack))) {
            return class_1268.field_5810;
        }
        return null;
    }

    public static int findItemInHotbarByTags(class_6862<class_1792> tags) {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer == null) {
            return -1;
        }
        int currentSlot = clientPlayer.method_31548().method_67532();
        for (int i = 0; i <= 8; ++i) {
            if (i == currentSlot || !clientPlayer.method_31548().method_5438(i).method_31573(tags)) continue;
            return i;
        }
        return -1;
    }

    public static int findItemInHotbar(Class<? extends class_1792> targetClass, boolean isLeftStart) {
        return Utils.findItemInHotbar(targetClass, isLeftStart, null);
    }

    public static int findItemInHotbar(Class<? extends class_1792> targetClass, boolean isLeftStart, @Nullable class_5321<class_1887> enchantment) {
        int i;
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer == null) {
            return -1;
        }
        int n = i = isLeftStart ? 0 : 8;
        while (isLeftStart ? i <= 8 : i >= 0) {
            class_1799 stack = clientPlayer.method_31548().method_5438(i);
            class_1792 item = stack.method_7909();
            if (targetClass.isInstance(item)) {
                if (enchantment == null) {
                    return i;
                }
                if (Utils.getEnchantLevel(clientPlayer, stack, enchantment) > 0) {
                    return i;
                }
            }
            i += isLeftStart ? 1 : -1;
        }
        return -1;
    }

    public static int findItemInInventory(class_1792 targetItem) {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer == null) {
            return -1;
        }
        for (int i = 9; i < 36; ++i) {
            class_1799 stack = clientPlayer.method_31548().method_5438(i);
            class_1792 item = stack.method_7909();
            if (item != targetItem) continue;
            return i;
        }
        return -1;
    }

    public static int getEnchantLevel(class_746 clientPlayer, class_1799 itemStack, class_5321<class_1887> enchantments) {
        class_1937 world = clientPlayer.method_73183();
        if (world == null) {
            return -1;
        }
        class_6880.class_6883 enchantment = world.method_30349().method_30530(class_7924.field_41265).method_46747(enchantments);
        return itemStack.method_58657().method_57536((class_6880)enchantment);
    }

    public static class_243 getDragonPos(class_1510 dragon, float delta) {
        class_1508 hitPart = dragon.method_5690()[1];
        class_1297 obj = (class_1297)Objects.requireNonNullElse(hitPart, dragon);
        return obj.method_30950(delta).method_1031(0.0, obj.method_5829().method_17940() / 2.0, 0.0);
    }

    public static class_243 getTargetPos(class_1297 target, boolean eyePos, float delta) {
        class_243 class_2432;
        double eyeHeight = target.method_18381(target.method_18376());
        if (target instanceof class_1510) {
            class_1510 enderDragonEntity = (class_1510)target;
            class_2432 = Utils.getDragonPos(enderDragonEntity, delta);
        } else {
            class_2432 = eyePos ? target.method_30950(delta).method_1031(0.0, eyeHeight, 0.0) : target.method_73189();
        }
        return class_2432;
    }

    public static class_1268 findToSetWindCharge(class_746 clientPlayer) {
        if (!Config.AUTO_WIND_CHARGE_SELECT) {
            return null;
        }
        int findWindChargeSlot = Utils.findItemInHotbar(class_9239.class, true);
        if (findWindChargeSlot > -1) {
            class_1799 stack = clientPlayer.method_31548().method_5438(findWindChargeSlot);
            if (clientPlayer.method_7357().method_7904(stack)) {
                return null;
            }
            MaceAttackAssistanceClient.afterJump(clientPlayer, clientPlayer.method_31548().method_67532(), -1);
            clientPlayer.method_31548().method_61496(findWindChargeSlot);
            return class_1268.field_5808;
        }
        return null;
    }

    public static boolean isSuccessFoundItItemInHotbar(Class<? extends class_1792> targetClass, boolean isLeftStart) {
        return Utils.findItemInHotbar(targetClass, isLeftStart) > -1;
    }

    public static boolean isHotBar(int slot) {
        return slot > -1 && slot < 9;
    }

    public static class_1297 getCrosshairEntity() {
        class_310 client = class_310.method_1551();
        if (client.field_1765 != null && client.field_1765.method_17783() == class_239.class_240.field_1331) {
            return ((class_3966)client.field_1765).method_17782();
        }
        return null;
    }

    public static boolean canStunSlam(class_1297 entity) {
        class_746 clientPlayer = class_310.method_1551().field_1724;
        if (clientPlayer == null) {
            return false;
        }
        if (!(entity instanceof class_1309)) {
            return false;
        }
        class_1309 target = (class_1309)entity;
        if (!target.method_6039()) {
            return false;
        }
        if (!Config.STUN_SLAMMING) {
            return false;
        }
        if (clientPlayer.method_18798().method_10214() > 0.0) {
            return false;
        }
        return !clientPlayer.method_6128();
    }

    public static int speedOverThreshold(class_746 clientPlayer) {
        class_243 vec = clientPlayer.method_18798();
        double length = vec.method_1033();
        double vx = vec.method_10216();
        double vz = vec.method_10215();
        double v = Math.sqrt(vx * vx + vz * vz);
        if (!clientPlayer.method_24828()) {
            if (length > 1.74) {
                return 2;
            }
            if (v > 0.82 && length > 1.65) {
                return 2;
            }
            if (v > 0.55) {
                return 1;
            }
        }
        return 0;
    }

    public static int getHandToSlot(class_746 clientPlayer, class_1268 hand) {
        if (hand == class_1268.field_5810) {
            return 9;
        }
        return clientPlayer.method_31548().method_67532();
    }

    public static class_243 simulateFuturePos(class_243 startPos, class_243 velocity, int ticksAhead) {
        class_243 pos = startPos;
        class_243 vel = velocity;
        for (int i = 0; i < ticksAhead; ++i) {
            vel = vel.method_1031(0.0, -0.08, 0.0);
            vel = vel.method_18805(0.91, 0.91, 0.91);
            pos = pos.method_1019(vel);
        }
        return pos;
    }

    public static class_1309 findCrosshairClosestTarget(class_1657 player, double range, double radius) {
        class_1937 world = player.method_73183();
        class_243 eyePos = player.method_5836(1.0f);
        class_243 lookVec = player.method_5828(1.0f).method_1029();
        class_243 endPos = eyePos.method_1019(lookVec.method_1021(range));
        class_238 box = player.method_5829().method_18804(lookVec.method_1021(range)).method_1014(radius);
        List candidates = world.method_8390(class_1309.class, box, e -> e != player && e.method_5805() && MaceAttackAssistanceClient.isAllowedTarget((class_1297)e));
        class_1309 bestTarget = null;
        double bestCrosshairDist = Double.MAX_VALUE;
        for (class_1309 target : candidates) {
            class_3965 hit;
            class_243 targetPos = target.method_5836(1.0f);
            double crosshairDist = Utils.distancePointToSegment(targetPos, eyePos, endPos);
            if (!(crosshairDist <= radius) || (hit = world.method_17742(new class_3959(eyePos, targetPos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)player))).method_17783() != class_239.class_240.field_1333 && hit.method_17784().method_1025(eyePos) < target.method_5858((class_1297)player) || !(crosshairDist < bestCrosshairDist)) continue;
            bestCrosshairDist = crosshairDist;
            bestTarget = target;
        }
        return bestTarget;
    }

    private static double distancePointToSegment(class_243 point, class_243 segA, class_243 segB) {
        class_243 ab = segB.method_1020(segA);
        class_243 ap = point.method_1020(segA);
        double t = ap.method_1026(ab) / ab.method_1027();
        t = Math.max(0.0, Math.min(1.0, t));
        class_243 closest = segA.method_1019(ab.method_1021(t));
        return point.method_1022(closest);
    }

    public static boolean isWearingArmor(class_1297 entity) {
        if (entity instanceof class_1309) {
            class_1309 living = (class_1309)entity;
            for (class_1304 slot : class_1304.values()) {
                class_1799 stack;
                if (slot.method_5925() != class_1304.class_1305.field_6178 && slot.method_5925() != class_1304.class_1305.field_48825 || (stack = living.method_6118(slot)).method_7960()) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean checkRightBtnAndSpear(class_310 client, class_746 clientPlayer) {
        class_1799 mainHandStack = clientPlayer.method_6047();
        class_1799 offHandStack = clientPlayer.method_6079();
        return (mainHandStack.method_31573(class_3489.field_63257) || offHandStack.method_31573(class_3489.field_63257)) && client.field_1690.field_1904.method_1434();
    }

    public static boolean checkLeftClickBtnAndSpear(class_310 client, class_746 clientPlayer) {
        class_1799 mainHandStack = clientPlayer.method_6047();
        return mainHandStack.method_31573(class_3489.field_63257) && client.field_1690.field_1886.method_1434();
    }

    public static boolean isSpear(class_746 clientPlayer) {
        class_1799 mainHandStack = clientPlayer.method_6047();
        return mainHandStack.method_31573(class_3489.field_63257);
    }

    public static boolean isSwordOrAxe(class_746 clientPlayer) {
        class_1799 mainHandStack = clientPlayer.method_6047();
        return mainHandStack.method_31573(class_3489.field_42611) || mainHandStack.method_31573(class_3489.field_42612);
    }

    public static boolean waitingToAttack(class_746 clientPlayer) {
        return clientPlayer.method_7261(0.0f) >= 0.3f;
    }

    public static boolean canAttack(class_310 client) {
        return client.field_1692 != null;
    }

    @Nullable
    public static class_1309 getLivingEntityInView(class_746 player, double minRange, double maxRange) {
        class_238 box;
        class_243 look;
        class_243 end;
        double maxRangeSqr = maxRange * maxRange;
        class_243 start = player.method_5836(1.0f);
        class_3966 hit = class_1675.method_18075((class_1297)player, (class_243)start, (class_243)(end = start.method_1019((look = player.method_5828(1.0f)).method_1021(maxRange))), (class_238)(box = player.method_5829().method_18804(look.method_1021(maxRange)).method_1014(1.0)), entity -> entity instanceof class_1309 && entity != player && !entity.method_7325() && entity.method_5805(), (double)maxRangeSqr);
        if (hit == null) {
            return null;
        }
        class_1297 entity2 = hit.method_17782();
        double distance = player.method_5739(entity2);
        if (distance > minRange && distance <= maxRange) {
            return (class_1309)entity2;
        }
        return null;
    }

    public static float rangeByWeapons(@NotNull class_746 clientPlayer) {
        return Utils.isSpear(clientPlayer) ? 4.5f : 3.0f;
    }

    public static class_1297 findNearestEnderPearl(class_310 client, class_746 clientPlayer) {
        double MAX_RANGE = 10.0;
        double closestDistance = 10.0;
        class_1297 nearestMob = null;
        class_243 playerPos = clientPlayer.method_73189();
        class_238 pBox = clientPlayer.method_5829();
        class_238 searchArea = pBox.method_1014(10.0);
        if (client.field_1687 != null) {
            List nearbyEntities = client.field_1687.method_8333((class_1297)clientPlayer, searchArea, entity -> {
                if (!(entity instanceof class_1684)) return false;
                class_1684 enderPearl = (class_1684)entity;
                if (!clientPlayer.method_5667().equals(Objects.requireNonNull(enderPearl.method_24921()).method_5667())) return false;
                return true;
            });
            for (class_1297 entity2 : nearbyEntities) {
                double distance = playerPos.method_1022(entity2.method_73189());
                if (!(distance < closestDistance)) continue;
                closestDistance = distance;
                nearestMob = entity2;
            }
        }
        return nearestMob;
    }

    public static class_241 getLookAngles(class_746 player, class_243 targetPos) {
        class_243 eyes = player.method_33571();
        double dx = targetPos.field_1352 - eyes.field_1352;
        double dy = targetPos.field_1351 - eyes.field_1351;
        double dz = targetPos.field_1350 - eyes.field_1350;
        double distXZ = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float pitch = (float)(-Math.toDegrees(Math.atan2(dy, distXZ)));
        return new class_241(yaw, pitch);
    }
}


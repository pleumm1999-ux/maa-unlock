/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_3489
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.BeamRenderHandler;
import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.config.Config;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_746;

public class AutoElytraSwap {
    private static boolean flagAutoElytraSwap = false;
    private static class_1268 handStatus = null;

    public static void setFlag(Boolean flag) {
        flagAutoElytraSwap = flag;
    }

    public static boolean getFlag() {
        return flagAutoElytraSwap;
    }

    public static class_1268 getHandStatus() {
        return handStatus;
    }

    public static boolean condition() {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer == null) {
            return false;
        }
        if (!clientPlayer.method_6128()) {
            return false;
        }
        if (!AutoElytraSwap.hasChestPlate(clientPlayer.method_6047(), clientPlayer.method_6079())) {
            return false;
        }
        if (!client.field_1690.field_1903.method_1434()) {
            return false;
        }
        if (clientPlayer.method_18798().method_10214() > 0.0) {
            return false;
        }
        class_1937 world = clientPlayer.method_73183();
        if (world == null) {
            return false;
        }
        List<class_1297> targetList = AutoElytraSwap.getTargetEntityList(client, clientPlayer, world);
        if (targetList == null) {
            return false;
        }
        class_1297 class_12972 = AutoElytraSwap.findNearestTarget(clientPlayer, targetList);
        if (!(class_12972 instanceof class_1309)) {
            return false;
        }
        class_1309 target = (class_1309)class_12972;
        MaceAttackAssistanceClient.setTargetMob((class_1297)target);
        return AutoElytraSwap.simulateLandingNormal(clientPlayer, target.method_73189(), target.method_6039());
    }

    public static List<class_1297> getTargetEntityList(class_310 client, class_746 clientPlayer, class_1937 world) {
        if (!world.method_8608()) {
            return null;
        }
        double upRange = 10.0;
        double downRange = 20.0;
        double horiRange = 30.0;
        class_243 playerEyePos = clientPlayer.method_33571();
        float playerPitch = clientPlayer.method_36455();
        float playerYaw = clientPlayer.method_36454();
        float yawFovDegrees = 40.0f;
        float pitchFovDegrees = 180.0f;
        class_238 pBox = clientPlayer.method_5829();
        class_243 start = new class_243(pBox.field_1323, playerEyePos.method_10214() + upRange, pBox.field_1321);
        class_243 end = new class_243(pBox.field_1320, playerEyePos.method_10214() - downRange, pBox.field_1324);
        class_238 searchArea = new class_238(start, end).method_1009(horiRange, 0.0, horiRange);
        List<Object> worldEntities = new ArrayList<class_1297>();
        if (client.field_1687 != null) {
            worldEntities = client.field_1687.method_8333((class_1297)clientPlayer, searchArea, entity -> entity.method_5709() && entity.method_5805() && entity.method_24828() && MaceAttackAssistanceClient.isAllowedTarget(entity) && !BeamRenderHandler.isOutsideViewCone(playerEyePos, entity.method_73189(), playerYaw, playerPitch, yawFovDegrees, pitchFovDegrees));
        }
        return worldEntities;
    }

    public static class_1297 findNearestTarget(class_746 clientPlayer, List<class_1297> nearbyEntities) {
        double closestDistance = Double.MAX_VALUE;
        class_1297 nearestEntity = null;
        for (class_1297 entity : nearbyEntities) {
            double distance = clientPlayer.method_5739(entity);
            if (!(distance < closestDistance)) continue;
            closestDistance = distance;
            nearestEntity = entity;
        }
        return nearestEntity;
    }

    private static boolean simulateLandingNormal(class_746 player, class_243 targetPos, boolean isBlocking) {
        if (player.method_36455() < 0.0f) {
            return false;
        }
        double speed = player.method_18798().method_1033();
        double distance = player.method_73189().method_1022(targetPos);
        return distance / speed <= (isBlocking ? (double)Config.AUTO_ELYTRA_TICK_AHEAD * 0.1 : (double)Config.AUTO_ELYTRA_TICK_AHEAD_NORMAL * 0.1);
    }

    public static boolean hasChestPlate(class_1799 mainStack, class_1799 offStack) {
        if (mainStack.method_31573(class_3489.field_48296)) {
            handStatus = class_1268.field_5808;
            return true;
        }
        if (offStack.method_31573(class_3489.field_48296)) {
            handStatus = class_1268.field_5810;
            return true;
        }
        handStatus = null;
        return false;
    }
}


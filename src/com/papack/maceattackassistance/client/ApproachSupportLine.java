/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_746
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.config.Config;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_746;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public class ApproachSupportLine {
    private static final double DEG_TO_RAD = Math.PI / 180;
    private static class_243 targetCenter;
    private static class_243 selfPos;

    public static void tick(class_746 clientPlayer, class_1297 target) {
        targetCenter = target.method_5829().method_1005();
        selfPos = clientPlayer.method_73189().method_1031(0.0, (double)clientPlayer.method_5751(), 0.0);
    }

    public static void drawFanLines(class_4587 matrices, class_4588 buffer) {
        double segmentLength = 10.0;
        class_310 client = class_310.method_1551();
        class_243 camPos = client.field_1773.method_19418().method_71156();
        for (int i = 0; i < 2; ++i) {
            class_243 toPlayer = selfPos.method_1020(targetCenter);
            double totalLength = toPlayer.method_1033();
            double theta = Math.toRadians(i == 0 ? 60.0 : 70.0);
            class_243 horizontalDir = new class_243(toPlayer.field_1352, 0.0, toPlayer.field_1350).method_1029();
            double y = totalLength * Math.sin(theta);
            double horizontalLength = totalLength * Math.cos(theta);
            class_243 baseEnd = targetCenter.method_1019(horizontalDir.method_1021(horizontalLength)).method_1031(0.0, y, 0.0);
            class_243 line1Dir = ApproachSupportLine.rotateAroundAxis(baseEnd.method_1020(targetCenter).method_1029(), new class_243(0.0, 1.0, 0.0), 0.08726646259971647);
            class_243 line2Dir = ApproachSupportLine.rotateAroundAxis(baseEnd.method_1020(targetCenter).method_1029(), new class_243(0.0, 1.0, 0.0), -0.08726646259971647);
            class_243 start = targetCenter.method_1020(camPos);
            ApproachSupportLine.drawSegmentedLine(matrices, buffer, start, targetCenter.method_1019(line1Dir.method_1021(totalLength)).method_1020(camPos), segmentLength);
            ApproachSupportLine.drawSegmentedLine(matrices, buffer, start, targetCenter.method_1019(line2Dir.method_1021(totalLength)).method_1020(camPos), segmentLength);
        }
    }

    private static void drawSegmentedLine(class_4587 matrices, class_4588 buffer, class_243 start, class_243 end, double segmentLength) {
        class_243 delta = end.method_1020(start);
        double length = delta.method_1033();
        class_243 dir = delta.method_1029();
        int segments = (int)Math.ceil(length / segmentLength);
        for (int i = 0; i < segments; ++i) {
            double t0 = (double)i * segmentLength;
            double t1 = Math.min((double)(i + 1) * segmentLength, length);
            class_243 segStart = start.method_1019(dir.method_1021(t0));
            class_243 segEnd = start.method_1019(dir.method_1021(t1));
            float r = i % 3 == 0 ? 1.0f : 0.0f;
            float g = i % 3 == 1 ? 1.0f : 0.0f;
            float b = i % 3 == 2 ? 1.0f : 0.0f;
            ApproachSupportLine.drawLine(matrices, buffer, segStart, segEnd, r, g, b);
        }
    }

    private static void drawLine(class_4587 matrices, class_4588 buffer, class_243 start, class_243 end, float r, float g, float b) {
        Matrix4f matrix = matrices.method_23760().method_23761();
        class_243 dir = end.method_1020(start).method_1029();
        buffer.method_22918((Matrix4fc)matrix, (float)start.field_1352, (float)start.field_1351, (float)start.field_1350).method_22915(r, g, b, 0.4f).method_22914((float)dir.field_1352, (float)dir.field_1351, (float)dir.field_1350);
        buffer.method_22918((Matrix4fc)matrix, (float)end.field_1352, (float)end.field_1351, (float)end.field_1350).method_22915(r, g, b, 0.4f).method_22914((float)dir.field_1352, (float)dir.field_1351, (float)dir.field_1350);
    }

    private static class_243 rotateAroundAxis(class_243 v, class_243 axis, double angleRad) {
        axis = axis.method_1029();
        double cos = Math.cos(angleRad);
        double sin = Math.sin(angleRad);
        class_243 term1 = v.method_1021(cos);
        class_243 term2 = axis.method_1036(v).method_1021(sin);
        class_243 term3 = axis.method_1021(axis.method_1026(v) * (1.0 - cos));
        return term1.method_1019(term2).method_1019(term3);
    }

    public static class_1657 findNearestPlayer(class_310 client, class_746 clientPlayer, int rangeXZ, int rangeY) {
        double downDistance = Math.max(50.0, (double)Config.RADAR_DOWNWARD);
        double closestDistance = Double.MAX_VALUE;
        class_1657 nearestPlayer = null;
        class_243 playerEyePos = clientPlayer.method_33571();
        class_243 playerPos = clientPlayer.method_73189();
        class_238 pBox = clientPlayer.method_5829();
        class_243 start = new class_243(pBox.field_1323, playerEyePos.method_10214() + 3.0, pBox.field_1321);
        class_243 end = new class_243(pBox.field_1320, playerEyePos.method_10214() - downDistance, pBox.field_1324);
        class_238 searchArea = new class_238(start, end).method_1009((double)rangeXZ, (double)rangeY, (double)rangeXZ);
        if (client.field_1687 != null) {
            List nearbyEntities = client.field_1687.method_8333((class_1297)clientPlayer, searchArea, entity -> {
                class_1657 target;
                return entity instanceof class_1657 && (target = (class_1657)entity).method_24828();
            });
            for (class_1297 entity2 : nearbyEntities) {
                double distance = playerPos.method_1022(entity2.method_73189());
                if (!(entity2 instanceof class_1657)) continue;
                class_1657 playerEntity = (class_1657)entity2;
                if (!(distance < closestDistance)) continue;
                closestDistance = distance;
                nearestPlayer = playerEntity;
            }
        }
        return nearestPlayer;
    }
}


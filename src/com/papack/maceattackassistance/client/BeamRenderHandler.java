/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_12249
 *  net.minecraft.class_1296
 *  net.minecraft.class_1297
 *  net.minecraft.class_1421
 *  net.minecraft.class_1429
 *  net.minecraft.class_1439
 *  net.minecraft.class_1480
 *  net.minecraft.class_1569
 *  net.minecraft.class_1588
 *  net.minecraft.class_1646
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4587$class_4665
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_4608
 *  net.minecraft.class_7260
 *  net.minecraft.class_746
 *  net.minecraft.class_9779
 *  net.minecraft.class_9848
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_12249;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1421;
import net.minecraft.class_1429;
import net.minecraft.class_1439;
import net.minecraft.class_1480;
import net.minecraft.class_1569;
import net.minecraft.class_1588;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_7260;
import net.minecraft.class_746;
import net.minecraft.class_9779;
import net.minecraft.class_9848;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class BeamRenderHandler {
    private static final class_2960 GLOW_TEXTURE = class_2960.method_60655((String)"maceattackassistance", (String)"textures/glow_particle.png");
    private static final Map<UUID, List<Vector3f>> lastTrailPosPerEntity = new HashMap<UUID, List<Vector3f>>();
    private static final float MIN_STEP = 0.05f;
    private static final Map<UUID, List<Deque<Vector3f>>> spiralTrailsPerEntity = new HashMap<UUID, List<Deque<Vector3f>>>();
    private static final Map<UUID, Long> entityTrailLastUpdate = new HashMap<UUID, Long>();
    private static final long CLEANUP_INTERVAL_MS = 5000L;
    private static final long TRAIL_TIMEOUT_MS = 10000L;
    private static long lastCleanupTime = 0L;
    public static List<class_1297> targetList = new ArrayList<class_1297>();

    public static void clearTargetList() {
        targetList.clear();
    }

    public static void markerRenderer(class_9779 tickCounter, class_4184 camera) {
        if (targetList.isEmpty()) {
            return;
        }
        if (Config.MARKER_TYPE == Config.MarkerType.Frame) {
            return;
        }
        if (!Config.TARGET_SEARCH_MODE && !Config.DEBUG_SCREEN) {
            if (!Config.AIM_ASSIST && Config.HIDE_MARKER) {
                return;
            }
            if (!Config.TARGET_MARKER) {
                return;
            }
        }
        class_4587 matrices = new class_4587();
        class_4597.class_4598 immediate = class_310.method_1551().method_22940().method_23000();
        for (class_1297 target : targetList) {
            BeamRenderHandler.particleController(matrices, (class_4597)immediate, camera, target, tickCounter.method_60636());
        }
        immediate.method_22993();
    }

    public static List<class_1297> getWorldEntityList(class_310 client, class_746 clientPlayer, class_1937 world) {
        if (!world.method_8608()) {
            return null;
        }
        double upRange = Config.RADAR_UPWARD * 10;
        double downRange = Config.RADAR_DOWNWARD * 10;
        double horiRange = Config.RADAR_HORIZONTAL * 10;
        class_243 playerEyePos = clientPlayer.method_33571();
        float playerPitch = clientPlayer.method_36455();
        float playerYaw = clientPlayer.method_36454();
        float yawFovDegrees = (Config.ZOOM_CAMERA ? Config.FOV_HORIZONTAL_ON_ZOOM : Config.FOV_HORIZONTAL) * 10;
        float pitchFovDegrees = (Config.ZOOM_CAMERA ? Config.FOV_VERTICAL_ON_ZOOM : Config.FOV_VERTICAL) * 10;
        class_238 pBox = clientPlayer.method_5829();
        class_243 start = new class_243(pBox.field_1323, playerEyePos.method_10214() + upRange, pBox.field_1321);
        class_243 end = new class_243(pBox.field_1320, playerEyePos.method_10214() - downRange, pBox.field_1324);
        class_238 searchArea = new class_238(start, end).method_1009(horiRange, 0.0, horiRange);
        List<Object> worldEntities = new ArrayList<class_1297>();
        if (client.field_1687 != null) {
            worldEntities = client.field_1687.method_8333((class_1297)clientPlayer, searchArea, entity -> entity.method_5709() && entity.method_5805() && MaceAttackAssistanceClient.isAllowedTarget(entity) && !BeamRenderHandler.isOutsideViewCone(playerEyePos, entity.method_73189(), playerYaw, playerPitch, yawFovDegrees, pitchFovDegrees) && Utils.isSimpleVisibleFromPlayer(client, clientPlayer, entity));
        }
        return worldEntities;
    }

    public static List<class_1297> getWorldEntityListParallel(class_310 client, class_746 clientPlayer, class_1937 world) {
        if (!world.method_8608()) {
            return Collections.emptyList();
        }
        double upRange = Config.RADAR_UPWARD * 10;
        double downRange = Config.RADAR_DOWNWARD * 10;
        double horiRange = Config.RADAR_HORIZONTAL * 10;
        class_243 playerEyePos = clientPlayer.method_33571();
        float playerPitch = clientPlayer.method_36455();
        float playerYaw = clientPlayer.method_36454();
        float yawFovDegrees = (Config.ZOOM_CAMERA ? Config.FOV_HORIZONTAL_ON_ZOOM : Config.FOV_HORIZONTAL) * 10;
        float pitchFovDegrees = (Config.ZOOM_CAMERA ? Config.FOV_VERTICAL_ON_ZOOM : Config.FOV_VERTICAL) * 10;
        class_238 pBox = clientPlayer.method_5829();
        class_243 start = new class_243(pBox.field_1323, playerEyePos.method_10214() + upRange, pBox.field_1321);
        class_243 end = new class_243(pBox.field_1320, playerEyePos.method_10214() - downRange, pBox.field_1324);
        class_238 searchArea = new class_238(start, end).method_1009(horiRange, 0.0, horiRange);
        List allEntities = world.method_8333((class_1297)clientPlayer, searchArea, entity -> entity.method_5709() && entity.method_5805());
        List<class_1297> preFiltered = allEntities.parallelStream().filter(MaceAttackAssistanceClient::isAllowedTarget).filter(entity -> !BeamRenderHandler.isOutsideViewCone(playerEyePos, entity.method_73189(), playerYaw, playerPitch, yawFovDegrees, pitchFovDegrees)).toList();
        ArrayList<class_1297> visibleEntities = new ArrayList<class_1297>();
        for (class_1297 entity2 : preFiltered) {
            if (!Utils.isSimpleVisibleFromPlayer(client, clientPlayer, entity2)) continue;
            visibleEntities.add(entity2);
        }
        return visibleEntities;
    }

    private static void particleController(class_4587 matrices, class_4597 consumers, class_4184 camera, class_1297 targetEntity, float delta) {
        double yOffset = 0.5 * (double)Config.MARKER_OFFSET + (double)(Config.MARKER_TYPE == Config.MarkerType.Beam ? targetEntity.method_17682() : 0.0f);
        class_243 entityPos = Utils.getTargetPos(targetEntity, false, delta).method_1031(0.0, yOffset, 0.0);
        class_243 relPos = entityPos.method_1020(camera.method_71156());
        matrices.method_22903();
        matrices.method_22904(relPos.field_1352, relPos.field_1351, relPos.field_1350);
        switch (Config.MARKER_TYPE) {
            case Beam: {
                BeamRenderHandler.renderBeam(matrices, consumers, BeamRenderHandler.getColor(targetEntity));
                break;
            }
            case Spiral: {
                BeamRenderHandler.renderSpiralParticlesTrail(matrices, consumers, BeamRenderHandler.getColor(targetEntity), targetEntity);
            }
        }
        matrices.method_22909();
    }

    private static void renderBeam(class_4587 matrices, class_4597 consumers, int targetColor) {
        float r = (float)class_9848.method_61327((int)targetColor) / 255.0f;
        float g = (float)class_9848.method_61329((int)targetColor) / 255.0f;
        float b = (float)class_9848.method_61331((int)targetColor) / 255.0f;
        float alpha = 1.0f;
        float height = 2.5f;
        float width = 0.1f;
        class_4587.class_4665 entry = matrices.method_23760();
        Matrix4f model = entry.method_23761();
        class_4588 vc = consumers.method_73477(class_12249.method_76023());
        for (int i = 0; i < 4; ++i) {
            float angle1 = (float)(Math.PI * 2 * (double)i / 4.0);
            float angle2 = (float)(Math.PI * 2 * (double)(i + 1) / 4.0);
            float x1 = (float)Math.cos(angle1) * width;
            float z1 = (float)Math.sin(angle1) * width;
            float x2 = (float)Math.cos(angle2) * width;
            float z2 = (float)Math.sin(angle2) * width;
            vc.method_22918((Matrix4fc)model, x1, 0.0f, z1).method_22915(r, g, b, alpha).method_22914(0.0f, 1.0f, 0.0f);
            vc.method_22918((Matrix4fc)model, x2, 0.0f, z2).method_22915(r, g, b, alpha).method_22914(0.0f, 1.0f, 0.0f);
            vc.method_22918((Matrix4fc)model, x2, height, z2).method_22915(r, g, b, 0.0f).method_22914(0.0f, 1.0f, 0.0f);
            vc.method_22918((Matrix4fc)model, x1, height, z1).method_22915(r, g, b, 0.0f).method_22914(0.0f, 1.0f, 0.0f);
        }
    }

    private static void renderSpiralParticlesTrail(class_4587 matrices, class_4597 consumers, int targetColor, class_1297 targetEntity) {
        float r = (float)class_9848.method_61327((int)targetColor) / 255.0f;
        float g = (float)class_9848.method_61329((int)targetColor) / 255.0f;
        float b = (float)class_9848.method_61331((int)targetColor) / 255.0f;
        class_4588 vc = consumers.method_73477(class_12249.method_76002((class_2960)GLOW_TEXTURE));
        int spiralCount = Config.SP_SPIRAL_COUNT;
        int trailLength = Config.SP_SPIRAL_LENGTH;
        float coils = Config.SP_COILS;
        float height = (float)Config.SP_HEIGHT * 0.5f;
        float size = (float)Config.SP_SIZE * 0.1f;
        float speed = (float)Config.SP_SPEED * 0.1f;
        float baseRadius = (float)Config.SP_BASE_RADIUS * 0.1f;
        float waveSpeed = (float)Config.SP_WAVE_SPEED * 0.1f;
        float waveAmplitude = (float)Config.SP_WAVE_AMPLITUDE * 0.1f;
        double timeSeconds = (double)System.nanoTime() / 1.0E9;
        class_4184 camera = class_310.method_1551().field_1773.method_19418();
        Quaternionf rotation = camera.method_23767();
        Vector3f cameraRight = new Vector3f(1.0f, 0.0f, 0.0f).rotate((Quaternionfc)rotation);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f).rotate((Quaternionfc)rotation);
        Matrix4f modelMatrix = matrices.method_23760().method_23761();
        UUID id = targetEntity.method_5667();
        List trails = spiralTrailsPerEntity.computeIfAbsent(id, k -> {
            ArrayList list = new ArrayList();
            for (int i = 0; i < spiralCount; ++i) {
                list.add(new LinkedList());
            }
            return list;
        });
        if (trails.size() != spiralCount) {
            trails.clear();
            for (int i = 0; i < spiralCount; ++i) {
                trails.add(new LinkedList());
            }
        }
        entityTrailLastUpdate.put(id, System.currentTimeMillis());
        List lastPosList = lastTrailPosPerEntity.computeIfAbsent(id, k -> {
            ArrayList<Object> list = new ArrayList<Object>();
            for (int i = 0; i < spiralCount; ++i) {
                list.add(null);
            }
            return list;
        });
        for (int s = 0; s < spiralCount; ++s) {
            float angleOffset = (float)(Math.PI * 2 * (double)s / (double)spiralCount);
            float phaseOffset = (float)s / (float)spiralCount;
            float dynamicRadius = baseRadius + (float)Math.sin(timeSeconds * (double)waveSpeed) * waveAmplitude;
            double progress = (timeSeconds * (double)speed + (double)phaseOffset) % 1.0;
            float angle = (float)(Math.PI * 2 * (double)coils * progress) + angleOffset;
            float y = (float)(progress * (double)height);
            float x = (float)Math.cos(angle) * dynamicRadius;
            float z = (float)Math.sin(angle) * dynamicRadius;
            Vector3f newPos = new Vector3f(x, y, z);
            Deque trail = (Deque)trails.get(s);
            Vector3f lastPos = (Vector3f)lastPosList.get(s);
            if (lastPos == null || newPos.distance((Vector3fc)lastPos) >= 0.05f) {
                trail.addFirst(newPos);
                lastPosList.set(s, new Vector3f((Vector3fc)newPos));
            }
            while (trail.size() > trailLength) {
                trail.removeLast();
            }
            int i = 0;
            for (Vector3f center : trail) {
                float fade = (float)i++ / (float)trail.size();
                fade = (float)Math.pow(fade, (float)Config.SP_SPIRAL_GAMMA * 0.1f);
                float alpha = (1.0f - fade) * ((float)Config.SP_SPIRAL_ALPHA * 0.1f);
                float particleSize = size * (1.0f - fade);
                float halfSize = particleSize / 2.0f;
                Vector3f right = new Vector3f((Vector3fc)cameraRight).mul(halfSize);
                Vector3f up = new Vector3f((Vector3fc)cameraUp).mul(halfSize);
                Vector3f p1 = new Vector3f((Vector3fc)center).sub((Vector3fc)right).sub((Vector3fc)up);
                Vector3f p2 = new Vector3f((Vector3fc)center).add((Vector3fc)right).sub((Vector3fc)up);
                Vector3f p3 = new Vector3f((Vector3fc)center).add((Vector3fc)right).add((Vector3fc)up);
                Vector3f p4 = new Vector3f((Vector3fc)center).sub((Vector3fc)right).add((Vector3fc)up);
                vc.method_22918((Matrix4fc)modelMatrix, p1.x, p1.y, p1.z).method_22915(r, g, b, alpha).method_22913(0.0f, 0.0f).method_60803(0xF000F0).method_22922(class_4608.field_21444).method_22914(0.0f, 1.0f, 0.0f);
                vc.method_22918((Matrix4fc)modelMatrix, p2.x, p2.y, p2.z).method_22915(r, g, b, alpha).method_22913(1.0f, 0.0f).method_60803(0xF000F0).method_22922(class_4608.field_21444).method_22914(0.0f, 1.0f, 0.0f);
                vc.method_22918((Matrix4fc)modelMatrix, p3.x, p3.y, p3.z).method_22915(r, g, b, 0.0f).method_22913(1.0f, 1.0f).method_60803(0xF000F0).method_22922(class_4608.field_21444).method_22914(0.0f, 1.0f, 0.0f);
                vc.method_22918((Matrix4fc)modelMatrix, p4.x, p4.y, p4.z).method_22915(r, g, b, 0.0f).method_22913(0.0f, 1.0f).method_60803(0xF000F0).method_22922(class_4608.field_21444).method_22914(0.0f, 1.0f, 0.0f);
            }
        }
        long now = System.currentTimeMillis();
        if (now - lastCleanupTime > 5000L) {
            lastCleanupTime = now;
            spiralTrailsPerEntity.keySet().removeIf(uuid -> !entityTrailLastUpdate.containsKey(uuid) || now - entityTrailLastUpdate.get(uuid) > 10000L);
            entityTrailLastUpdate.keySet().removeIf(uuid -> !spiralTrailsPerEntity.containsKey(uuid));
        }
    }

    private static int getColor(class_1297 target) {
        if (target instanceof class_1646 && Config.ALLOWED_VILLAGER) {
            return Config.COLOR_VILLAGER.getValue();
        }
        if (target instanceof class_1439 && Config.ALLOWED_IRON_GOLEM) {
            return Config.COLOR_IRON_GOLEM.getValue();
        }
        if (target instanceof class_7260 && Config.ALLOWED_HOSTILE) {
            return Config.COLOR_WARDEN.getValue();
        }
        if (target instanceof class_1588 && Config.ALLOWED_HOSTILE) {
            return Config.COLOR_HOSTILE.getValue();
        }
        if (target instanceof class_1569 && Config.ALLOWED_HOSTILE) {
            return Config.COLOR_HOSTILE.getValue();
        }
        if (target instanceof class_1429 && Config.ALLOWED_PASSIVE) {
            return Config.COLOR_PASSIVE.getValue();
        }
        if (target instanceof class_1296 && Config.ALLOWED_PASSIVE) {
            return Config.COLOR_PASSIVE.getValue();
        }
        if (target instanceof class_1421 && Config.ALLOWED_AMBIENT) {
            return Config.COLOR_AMBIENT.getValue();
        }
        if (target instanceof class_1480 && Config.ALLOWED_AMBIENT) {
            return Config.COLOR_AMBIENT.getValue();
        }
        if (target instanceof class_1657 && Config.ALLOWED_PLAYER) {
            return Config.COLOR_PLAYER.getValue();
        }
        return Config.ALLOWED_AMBIENT ? Config.COLOR_AMBIENT.getValue() : 0;
    }

    public static boolean isOutsideViewCone(class_243 playerPos, class_243 targetPos, float yawDegrees, float pitchDegrees, float yawFovDegrees, float pitchFovDegrees) {
        double yawRad = Math.toRadians(-yawDegrees);
        double pitchRad = Math.toRadians(-pitchDegrees);
        class_243 lookVec = new class_243(Math.cos(pitchRad) * Math.sin(yawRad), Math.sin(pitchRad), Math.cos(pitchRad) * Math.cos(yawRad)).method_1029();
        class_243 toTarget = targetPos.method_1020(playerPos).method_1029();
        double yawDot = new class_243(lookVec.field_1352, 0.0, lookVec.field_1350).method_1029().method_1026(new class_243(toTarget.field_1352, 0.0, toTarget.field_1350).method_1029());
        double yawAngleDeg = Math.toDegrees(Math.acos(yawDot));
        double pitchDot = lookVec.method_1026(toTarget);
        double pitchAngleDeg = Math.toDegrees(Math.acos(pitchDot)) - yawAngleDeg;
        return yawAngleDeg > (double)yawFovDegrees / 2.0 || Math.abs(pitchAngleDeg) > (double)pitchFovDegrees / 2.0;
    }
}


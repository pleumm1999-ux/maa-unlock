/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10799
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_5250
 *  net.minecraft.class_5498
 *  net.minecraft.class_746
 *  net.minecraft.class_9779
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.AutoZoomInOut;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.TickScheduler;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_10799;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_5250;
import net.minecraft.class_5498;
import net.minecraft.class_746;
import net.minecraft.class_9779;

public class ZoomState {
    public static float zoomFov = 70.0f;
    public static boolean zoomMode = false;
    public static float zoomPitch = -4.0f;
    public static float zoomFovInOut = 0.0f;
    public static double lastMouseY = 0.0;
    public static boolean firstFrame = true;
    public static boolean TEMPORARY_GAZE_BOOST = false;
    public static boolean TEMPORARY_GAZE_ZOOM = false;
    public static boolean TEMPORARY_GAZE_ZOOM_RETURN = false;
    public static boolean TEMPORARY_GAZE_ZOOM_FOV = false;
    public static boolean TEMPORARY_GAZE_ZOOM_FOV_IN_OUT = false;
    public static int gazeCounter = 0;

    public static void moveCamera(class_310 client, class_746 clientPlayer, class_9779 context) {
        float tickDelta;
        if (TEMPORARY_GAZE_BOOST) {
            tickDelta = context.method_60636();
            float angle = gazeCounter > 1 ? 0.0f : (float)((double)Config.REFLECTION_ANGLE * (1.0 - 0.5 * (double)gazeCounter));
            zoomPitch = Utils.lerpPitch(zoomPitch, angle, tickDelta);
        }
        if (TEMPORARY_GAZE_ZOOM) {
            tickDelta = context.method_60636();
            zoomPitch = ZoomState.lerpOnTickTime(zoomPitch, -4.0f, tickDelta, 2);
            if (TEMPORARY_GAZE_ZOOM && ZoomState.closeValueComparison(zoomPitch, -4.0f, 0.2f)) {
                ZoomState.reset1();
            }
        }
        if (TEMPORARY_GAZE_ZOOM_FOV) {
            tickDelta = context.method_60636();
            zoomFov = ZoomState.lerpOnTickTime(zoomFov, ((Integer)client.field_1690.method_41808().method_41753()).intValue(), tickDelta, 2);
            if (TEMPORARY_GAZE_ZOOM_FOV && ZoomState.closeValueComparison(zoomFov, ((Integer)client.field_1690.method_41808().method_41753()).intValue(), 0.2f)) {
                ZoomState.reset2();
            }
        }
        if (TEMPORARY_GAZE_ZOOM_RETURN) {
            tickDelta = context.method_60636();
            zoomPitch = ZoomState.lerpOnTickTime(zoomPitch, clientPlayer.method_36455(), tickDelta, 2);
            zoomFov = ZoomState.lerpOnTickTime(zoomFov, ((Integer)client.field_1690.method_41808().method_41753()).intValue(), tickDelta, 2);
            if (TEMPORARY_GAZE_ZOOM_RETURN && ZoomState.closeValueComparison(zoomPitch, clientPlayer.method_36455(), 0.3f) && ZoomState.closeValueComparison(zoomFov, ((Integer)client.field_1690.method_41808().method_41753()).intValue(), 0.2f)) {
                ZoomState.reset3();
            }
        }
        if (TEMPORARY_GAZE_ZOOM_FOV_IN_OUT) {
            tickDelta = context.method_60636();
            zoomFov = ZoomState.lerpOnTickTime(zoomFov, zoomFovInOut, tickDelta, 2);
            if (TEMPORARY_GAZE_ZOOM_FOV_IN_OUT && ZoomState.closeValueComparison(zoomFov, zoomFovInOut, 0.2f)) {
                ZoomState.reset4();
            }
        }
        if (Config.ZOOM_CAMERA) {
            clientPlayer.method_36457(-4.0f);
            if (!zoomMode || !client.method_1569()) {
                firstFrame = true;
                return;
            }
            double mouseY = client.field_1729.method_1604();
            if (firstFrame) {
                lastMouseY = mouseY;
                firstFrame = false;
                return;
            }
            double deltaY = mouseY - lastMouseY;
            lastMouseY = mouseY;
            float sensitivity = ((Double)client.field_1690.method_42495().method_41753()).floatValue();
            float multiplier = sensitivity * 0.15f;
            if (client.field_1755 == null) {
                zoomPitch += (float)(deltaY * (double)multiplier);
            }
            zoomPitch = class_3532.method_15363((float)zoomPitch, (float)-90.0f, (float)90.0f);
        }
    }

    public static void resetZoomPitch() {
        zoomPitch = -4.0f;
        lastMouseY = 0.0;
        firstFrame = true;
    }

    public static void zoomCamera(class_310 client, class_746 clientPlayer, boolean canZoom) {
        ZoomJob job = ZoomJob.NONE;
        if (!Config.ZOOM_CAMERA && canZoom) {
            job = ZoomJob.START;
        }
        if (Config.ZOOM_CAMERA) {
            client.field_1690.method_31043(class_5498.field_26664);
            job = Config.CAMERA_RETURN_BEHAVIOR || AutoZoomInOut.isCancel() ? ZoomJob.RETURN_PITCH : ZoomJob.RETURN_CONTINUE;
        }
        switch (job.ordinal()) {
            case 0: {
                class_5498 perspective = Config.ZOOM_VIEW == Config.ReconView.FIRST_PERSON ? class_5498.field_26664 : class_5498.field_26665;
                ZoomState.setValue(client, clientPlayer);
                client.field_1690.method_31043(perspective);
                TEMPORARY_GAZE_ZOOM = true;
                TickScheduler.setDelayTask(3, ZoomState::reset1);
                break;
            }
            case 1: {
                clientPlayer.method_36457(zoomPitch);
                TEMPORARY_GAZE_ZOOM_FOV = true;
                TickScheduler.setDelayTask(3, ZoomState::reset2);
                break;
            }
            case 2: {
                TEMPORARY_GAZE_ZOOM_RETURN = true;
                TickScheduler.setDelayTask(3, ZoomState::reset3);
                break;
            }
        }
        if (job != ZoomJob.NONE) {
            Config.ZOOM_CAMERA = !Config.ZOOM_CAMERA;
            String str = Config.ZOOM_CAMERA ? "On" : "Off";
            class_5250 msg = class_2561.method_43470((String)("Recon Camera Mode: " + str));
            clientPlayer.method_7353((class_2561)msg.method_27692(class_124.field_1060).method_27692(class_124.field_1067), true);
        }
    }

    public static void init() {
        ZoomState.resetZoomPitch();
    }

    private static boolean closeValueComparison(float currentValue, float targetValue, float diff) {
        return currentValue < targetValue + diff && currentValue > targetValue - diff;
    }

    public static float lerpOnTickTime(float start, float end, float delta, int tick) {
        if (tick == 0) {
            tick = 1;
        }
        return start + delta * (end - start) / (float)tick;
    }

    private static void reset1() {
        TEMPORARY_GAZE_ZOOM = false;
    }

    private static void reset2() {
        if (TEMPORARY_GAZE_ZOOM_FOV) {
            TEMPORARY_GAZE_ZOOM_FOV = false;
            Config.ZOOM_CAMERA = false;
            ZoomState.resetZoomPitch();
        }
    }

    private static void reset3() {
        if (TEMPORARY_GAZE_ZOOM_RETURN) {
            TEMPORARY_GAZE_ZOOM_RETURN = false;
            Config.ZOOM_CAMERA = false;
            ZoomState.resetZoomPitch();
        }
    }

    public static void reset4() {
        TEMPORARY_GAZE_ZOOM_FOV_IN_OUT = false;
    }

    public static void setValue(class_310 client, class_746 clientPlayer) {
        ZoomState.resetZoomPitch();
        zoomPitch = clientPlayer.method_36455();
        zoomFov = ((Integer)client.field_1690.method_41808().method_41753()).intValue();
    }

    public static void renderCrosshair(class_332 drawContext) {
        if (Utils.shouldRenderCustomCrosshair()) {
            drawContext.method_52706(class_10799.field_56890, Config.CUSTOM_CROSSHAIR_TEXTURE, (drawContext.method_51421() - 15) / 2, (drawContext.method_51443() - 15) / 2, 15, 15);
        } else {
            drawContext.method_52706(class_10799.field_56890, Config.CROSSHAIR_TEXTURE, (drawContext.method_51421() - 15) / 2, (drawContext.method_51443() - 15) / 2, 15, 15);
        }
    }

    public static enum ZoomJob {
        START,
        RETURN_PITCH,
        RETURN_CONTINUE,
        NONE;

    }

    public static class MAAClientState {
        private static final int DEFAULT_LEVEL = 1;
        public static boolean receivedFirstMessage = false;
        public static int allowedLevels = 1;
        public static boolean antiCheat = false;

        public static void clear() {
            receivedFirstMessage = false;
            allowedLevels = 1;
            antiCheat = false;
        }
    }

    public static class KeyManager {
        public static boolean keyManager() {
            return !JobManager.JUMP_OPTION && !JobManager.jumpOption();
        }
    }
}


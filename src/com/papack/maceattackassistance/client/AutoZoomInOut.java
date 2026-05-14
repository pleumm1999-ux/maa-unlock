/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3675$class_306
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.TickScheduler;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import com.papack.maceattackassistance.mixin.KeyBindingInvoker;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_746;

public class AutoZoomInOut {
    private static boolean inCancelling = false;
    private static boolean flag_inZoom = false;

    public static boolean isCancel() {
        return inCancelling;
    }

    public static void autoZoomInOut() {
        class_310 client = class_310.method_1551();
        class_746 player = client.field_1724;
        if (player == null) {
            return;
        }
        if (Config.ZOOM_CAMERA && Config.RECON_QUICK_ATTACK && (client.field_1690.field_1832.method_1434() || client.field_1690.field_1904.method_1434()) && client.field_1690.field_1886.method_1434() && client.field_1690.field_1903.method_1434() && (!ZoomState.TEMPORARY_GAZE_ZOOM_FOV_IN_OUT || flag_inZoom) && !inCancelling) {
            inCancelling = true;
            class_304.method_1420((class_3675.class_306)((KeyBindingInvoker)Config.ZOOM_CAMERA_KEY).accessorBoundKey());
            TickScheduler.setDelayTask(3, () -> {
                inCancelling = false;
            });
        }
        if (Config.ZOOM_CAMERA && Config.RECON_QUICK_ZOOM && !flag_inZoom && client.field_1690.field_1886.method_1434() && !ZoomState.TEMPORARY_GAZE_ZOOM_FOV_IN_OUT) {
            flag_inZoom = true;
            ZoomState.zoomFovInOut = ZoomState.zoomFov;
            ZoomState.zoomFovInOut -= (float)(2 * Config.ZOOM_STEP * 10);
            ZoomState.zoomFovInOut = (float)Math.max(5.0, Math.min(140.0, (double)ZoomState.zoomFovInOut));
            ZoomState.TEMPORARY_GAZE_ZOOM_FOV_IN_OUT = true;
            TickScheduler.setDelayTask(6, ZoomState::reset4);
        }
        if (Config.ZOOM_CAMERA && Config.RECON_QUICK_ZOOM && flag_inZoom && !client.field_1690.field_1886.method_1434() && !ZoomState.TEMPORARY_GAZE_ZOOM_FOV_IN_OUT) {
            ZoomState.zoomFovInOut = ZoomState.zoomFov;
            ZoomState.zoomFovInOut += (float)(2 * Config.ZOOM_STEP * 10);
            ZoomState.zoomFovInOut = (float)Math.max(5.0, Math.min(140.0, (double)ZoomState.zoomFovInOut));
            ZoomState.TEMPORARY_GAZE_ZOOM_FOV_IN_OUT = true;
            TickScheduler.setDelayTask(6, () -> {
                flag_inZoom = false;
                ZoomState.reset4();
            });
        }
    }
}


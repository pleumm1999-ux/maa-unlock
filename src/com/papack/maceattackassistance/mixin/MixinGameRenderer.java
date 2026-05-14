/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_757
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import com.papack.maceattackassistance.mixin.CameraInvoker;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_757;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_757.class})
public class MixinGameRenderer {
    @Inject(method={"method_3196"}, at={@At(value="TAIL")}, cancellable=true)
    private void onUpdateCamera(class_4184 camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        if (class_310.method_1551().field_1724 == null) {
            return;
        }
        if (Config.ZOOM_CAMERA || ZoomState.TEMPORARY_GAZE_BOOST || ZoomState.TEMPORARY_GAZE_ZOOM_RETURN || ZoomState.TEMPORARY_GAZE_ZOOM_FOV || ZoomState.TEMPORARY_GAZE_ZOOM_FOV_IN_OUT) {
            float yaw = camera.method_19330();
            ((CameraInvoker)camera).setRotationInvoker(yaw, ZoomState.zoomPitch);
            ZoomState.zoomMode = true;
        } else {
            ZoomState.zoomMode = false;
        }
        if (ZoomState.zoomMode) {
            cir.setReturnValue((Object)Float.valueOf(ZoomState.zoomFov));
        }
    }
}


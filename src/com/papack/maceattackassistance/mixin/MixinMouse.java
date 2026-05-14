/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_312
 *  net.minecraft.class_437
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.TickScheduler;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_437;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_312.class})
public class MixinMouse {
    @Inject(method={"method_1598"}, at={@At(value="HEAD")}, cancellable=true)
    public void mixinOnMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        class_437 screen = client.field_1755;
        class_304 sprintKey = client.field_1690.field_1867;
        if (screen == null && ZoomState.zoomMode && sprintKey.method_1434() && clientPlayer != null && clientPlayer.method_6128()) {
            ZoomState.zoomFovInOut = ZoomState.zoomFov;
            ZoomState.zoomFovInOut -= (float)(vertical * (double)Config.ZOOM_STEP * 10.0);
            ZoomState.zoomFovInOut = (float)Math.max(5.0, Math.min(140.0, (double)ZoomState.zoomFovInOut));
            ZoomState.TEMPORARY_GAZE_ZOOM_FOV_IN_OUT = true;
            TickScheduler.setDelayTask(6, ZoomState::reset4);
            ci.cancel();
        }
    }
}


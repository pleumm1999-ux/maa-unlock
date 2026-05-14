/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  net.minecraft.class_10799
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.papack.maceattackassistance.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_10799;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_332.class})
public class MixinDrawContext {
    @Unique
    private static final class_2960 CROSSHAIR_TEXTURE = class_2960.method_60656((String)"hud/crosshair");

    @Inject(method={"method_52706(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIII)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void drawCustomCrosshairTexture(RenderPipeline pipeline, class_2960 sprite, int x, int y, int width, int height, CallbackInfo ci) {
        if (sprite.equals((Object)CROSSHAIR_TEXTURE) && Utils.shouldRenderCustomCrosshair()) {
            ((class_332)this).method_52706(class_10799.field_56890, Config.CUSTOM_CROSSHAIR_TEXTURE, x, y, width, height);
            ci.cancel();
        }
    }
}


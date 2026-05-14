/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1007
 *  net.minecraft.class_11659
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_4587
 *  net.minecraft.class_5498
 *  net.minecraft.class_630
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_1007;
import net.minecraft.class_11659;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_5498;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_1007.class})
public class MixinPlayerEntityRenderer {
    @Inject(method={"method_23205"}, at={@At(value="HEAD")}, cancellable=true)
    private void hidePlayerArm(class_4587 matrices, class_11659 queue, int light, class_2960 skinTexture, class_630 arm, boolean sleeveVisible, CallbackInfo ci) {
        class_310 client = class_310.method_1551();
        if (Config.ZOOM_CAMERA && client.field_1690.method_31044() == class_5498.field_26664) {
            ci.cancel();
        }
    }
}


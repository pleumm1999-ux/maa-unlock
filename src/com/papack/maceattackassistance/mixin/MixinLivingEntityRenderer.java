/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_10042
 *  net.minecraft.class_11659
 *  net.minecraft.class_12075
 *  net.minecraft.class_12249
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_3883
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4608
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_583
 *  net.minecraft.class_897
 *  net.minecraft.class_922
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_10042;
import net.minecraft.class_11659;
import net.minecraft.class_12075;
import net.minecraft.class_12249;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3883;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5617;
import net.minecraft.class_583;
import net.minecraft.class_897;
import net.minecraft.class_922;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_922.class})
public abstract class MixinLivingEntityRenderer<T extends class_1309, S extends class_10042, M extends class_583<? super S>>
extends class_897<T, S>
implements class_3883<S, M> {
    @Unique
    private static final class_2960 GLOWING_TEXTURE_RED = class_2960.method_60655((String)"maceattackassistance", (String)"textures/red_overlay.png");
    @Unique
    private static final class_2960 GLOWING_TEXTURE_GREEN = class_2960.method_60655((String)"maceattackassistance", (String)"textures/green_overlay.png");
    @Unique
    private static boolean RENDER_SWITCH;
    @Unique
    class_1297 currentEntity;

    protected MixinLivingEntityRenderer(class_5617.class_5618 context) {
        super(context);
    }

    @Inject(method={"method_62355(Lnet/minecraft/class_1309;Lnet/minecraft/class_10042;F)V"}, at={@At(value="HEAD")})
    private void getEntity(T livingEntity, S livingEntityRenderState, float f, CallbackInfo ci) {
        this.currentEntity = livingEntity;
    }

    @Inject(method={"method_4054(Lnet/minecraft/class_10042;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"}, at={@At(value="TAIL")})
    private void renderOverlay(S livingEntityRenderState, class_4587 matrixStack, class_11659 orderedRenderCommandQueue, class_12075 cameraRenderState, CallbackInfo ci) {
        class_310 client = class_310.method_1551();
        if ((!Config.HIDE_MARKER || Config.AIM_ASSIST) && Config.TARGET_MARKER && Config.MARKER_TYPE == Config.MarkerType.Frame && this.shouldRenderGlowingOverlay(this.currentEntity)) {
            RENDER_SWITCH = !RENDER_SWITCH;
            boolean inRange = Utils.isInAttackableRange(client.field_1724, this.currentEntity);
            if (inRange || RENDER_SWITCH) {
                this.drawGlowingOverlay(this.currentEntity, matrixStack, (class_4597)client.method_22940().method_23000(), inRange);
            }
        }
    }

    @Unique
    private boolean shouldRenderGlowingOverlay(class_1297 entity) {
        class_1297 target = MaceAttackAssistanceClient.getTargetMob();
        if (target != null) {
            return entity.method_5667().equals(target.method_5667());
        }
        return false;
    }

    @Unique
    private void drawGlowingOverlay(class_1297 entity, class_4587 matrices, class_4597 vertexConsumers, boolean inRange) {
        matrices.method_22903();
        matrices.method_22904(0.0, (double)entity.method_17682() + 0.5 * (double)Config.MARKER_OFFSET, 0.0);
        matrices.method_22905(1.0f, 1.0f, 1.0f);
        class_4588 vertexConsumer = vertexConsumers.method_73477(class_12249.method_76012((class_2960)(inRange ? GLOWING_TEXTURE_GREEN : GLOWING_TEXTURE_RED)));
        Matrix4f positionMatrix = matrices.method_23760().method_23761();
        this.drawVertex(vertexConsumer, positionMatrix, -0.5f, -0.5f, 0.0f, 0.0f, inRange);
        this.drawVertex(vertexConsumer, positionMatrix, -0.5f, 0.5f, 0.0f, 1.0f, inRange);
        this.drawVertex(vertexConsumer, positionMatrix, 0.5f, 0.5f, 1.0f, 1.0f, inRange);
        this.drawVertex(vertexConsumer, positionMatrix, 0.5f, -0.5f, 1.0f, 0.0f, inRange);
        matrices.method_22909();
    }

    @Unique
    private void drawVertex(class_4588 vertexConsumer, Matrix4f matrix, float x, float z, float u, float v, boolean inRange) {
        if (inRange) {
            vertexConsumer.method_22918((Matrix4fc)matrix, x, 0.0f, z).method_1336(0, 255, 0, 128).method_22913(u, v).method_22922(class_4608.field_21444).method_60803(0xF000F0).method_22914(0.0f, 1.0f, 0.0f);
        } else {
            vertexConsumer.method_22918((Matrix4fc)matrix, x, 0.0f, z).method_1336(255, 0, 0, 128).method_22913(u, v).method_22922(class_4608.field_21444).method_60803(0xF000F0).method_22914(0.0f, 1.0f, 0.0f);
        }
    }
}


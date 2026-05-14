/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1041
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2868
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3675
 *  net.minecraft.class_437
 *  net.minecraft.class_634
 *  net.minecraft.class_746
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.MacroController;
import com.papack.maceattackassistance.client.PrevSlotManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import net.minecraft.class_634;
import net.minecraft.class_746;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_310.class})
public abstract class MixinMinecraft {
    @Shadow
    @Nullable
    public abstract class_634 method_1562();

    @Redirect(method={"method_1508()V"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_304;method_1436()Z"))
    private boolean canSwingDownTheMace(class_304 instance) {
        boolean canDT;
        if (ZoomState.MAAClientState.antiCheat || ZoomState.KeyManager.keyManager()) {
            return instance.method_1436();
        }
        if (JobManager.jumpOption() && MaceAttackAssistanceClient.getTargetMob() instanceof class_1657) {
            return instance.method_1436();
        }
        boolean bl = canDT = Utils.canDoubleTap() || JobManager.checkStatus(StatusType.DOUBLE_TAP);
        if (!Config.EXTREME || canDT) {
            if (JobManager.checkOrderIsEmpty()) {
                class_310 client = (class_310)this;
                class_746 clientPlayer = client.field_1724;
                class_437 screen = client.field_1755;
                float ALLOW_DISTANCE = 4.23f;
                Config.SwingData swingData = this.getSwingData(client);
                boolean isAttackKey = instance.method_1428().equals(client.field_1690.field_1886.method_1428());
                boolean isUseKey = instance.method_1428().equals(client.field_1690.field_1904.method_1428());
                int highSpeed = 1;
                if ((isAttackKey || isUseKey) && screen == null && clientPlayer != null && client.field_1690.field_1886.method_1434() && Config.ATTACK_ASSISTANCE && (Utils.isNotUsingElytra(clientPlayer) || canDT)) {
                    class_243 playerVelocity = clientPlayer.method_18798();
                    double yV = playerVelocity.method_10214();
                    int speedLevel = Utils.speedOverThreshold(clientPlayer);
                    boolean isOverSpeed = speedLevel > 0;
                    class_1297 target = MaceAttackAssistanceClient.getTargetMob();
                    if (target == null) {
                        target = Utils.getCrosshairEntity();
                    }
                    if (target instanceof class_1309) {
                        class_1309 livingEntity = (class_1309)target;
                        double distance = clientPlayer.method_5739(target);
                        if (Utils.xzDistance(clientPlayer, target) <= (double)ALLOW_DISTANCE) {
                            boolean d;
                            boolean isOffsetRequired = livingEntity.method_6039();
                            boolean bl2 = d = distance <= 20.0;
                            if (Config.STUN_SLAMMING && d && isOffsetRequired) {
                                int currentSlot = PrevSlotManager.isEmpty() ? clientPlayer.method_31548().method_67532() : PrevSlotManager.getLastOrderSlot();
                                int axeSlot = JobManager.getAxeSlotId(clientPlayer);
                                if (axeSlot > -1 && axeSlot != currentSlot) {
                                    JobManager.setPreviousSlot(StatusType.NONE, currentSlot);
                                    clientPlayer.method_31548().method_61496(axeSlot);
                                    class_634 handler = this.method_1562();
                                    if (handler != null) {
                                        handler.method_52787((class_2596)new class_2868(axeSlot));
                                    }
                                }
                            }
                            if (yV > Config.VELOCITY_BY_DISTANCE[Config.FALL_VELOCITY[0]]) {
                                highSpeed = 0;
                            }
                            if (isOverSpeed) {
                                highSpeed = speedLevel;
                            }
                            class_243 nextPos = Utils.simulateFuturePos(clientPlayer.method_73189(), clientPlayer.method_18798(), Math.min(1, speedLevel));
                            double d0 = nextPos.method_1022(target.method_73189());
                            boolean result = d0 < (double)(highSpeed > 0 ? 3.0f : (float)Config.ATTACK_RANGE * 0.01f);
                            class_1297 entity = client.field_1692;
                            if (entity != null && (Config.AIM_ASSIST || result) || isOverSpeed && result) {
                                MacroController.macroController(clientPlayer, clientPlayer.method_73183(), livingEntity, highSpeed);
                            }
                        }
                    }
                    class_1297 en = Utils.getCrosshairEntity();
                    if ((isOverSpeed || swingData.canSwing() && (Utils.verifyGround(clientPlayer, Config.HEIGHT_THRESHOLD) || MixinMinecraft.playerCondition(clientPlayer))) && !clientPlayer.method_24828() && (en == null || en.method_24828())) {
                        MaceAttackAssistanceClient.flag_attack_canceled = true;
                        return false;
                    }
                }
                return instance.method_1436();
            }
        } else {
            class_1297 target;
            class_310 client = (class_310)this;
            class_746 clientPlayer = client.field_1724;
            class_437 screen = client.field_1755;
            boolean isAttackKey = instance.method_1428().equals(client.field_1690.field_1886.method_1428());
            if (isAttackKey && screen == null && clientPlayer != null && client.field_1690.field_1886.method_1434() && Config.ATTACK_ASSISTANCE && Utils.isNotUsingElytra(clientPlayer) && this.check(client, clientPlayer, target = Utils.getCrosshairEntity())) {
                MaceAttackAssistanceClient.flag_attack_canceled = true;
                return false;
            }
        }
        return instance.method_1436();
    }

    @Unique
    private boolean check(class_310 client, class_746 clientPlayer, class_1297 target) {
        if (target != null && !target.method_24828()) {
            return false;
        }
        if (clientPlayer.method_24828()) {
            return false;
        }
        if (!this.shouldHandSwing(client)) {
            return false;
        }
        return Utils.verifyGround(clientPlayer, Config.HEIGHT_THRESHOLD) || MixinMinecraft.playerCondition(clientPlayer);
    }

    @Unique
    private static boolean playerCondition(class_746 clientPlayer) {
        return clientPlayer.method_6128() || Utils.speedOverThreshold(clientPlayer) > 0;
    }

    @Unique
    private Config.SwingData getSwingData(class_310 client) {
        boolean isKeyPressed = class_3675.method_15987((class_1041)client.method_22683(), (int)Config.SWING_TOGGLE.getGlfwKey());
        boolean canSwing = Config.WEAPON_SWING != isKeyPressed;
        return new Config.SwingData(canSwing, isKeyPressed);
    }

    @Unique
    private boolean shouldHandSwing(class_310 client) {
        return class_3675.method_15987((class_1041)client.method_22683(), (int)Config.SWING_TOGGLE.getGlfwKey()) != Config.WEAPON_SWING;
    }
}


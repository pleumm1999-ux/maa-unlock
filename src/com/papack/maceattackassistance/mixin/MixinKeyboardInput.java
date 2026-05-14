/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_10185
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_636
 *  net.minecraft.class_743
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.ElytraBoost;
import com.papack.maceattackassistance.client.FlappingSuppression;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.JumpController;
import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.RefillManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.WallClimbing;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_10185;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_636;
import net.minecraft.class_743;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_743.class})
public class MixinKeyboardInput {
    @Unique
    WallClimbing.ClimbingStatus wallClimbingStatus;

    @Redirect(method={"method_3129"}, at=@At(value="INVOKE", target="Lnet/minecraft/class_304;method_1434()Z"))
    private boolean inGameKeyPressed(class_304 instance) {
        if (ZoomState.MAAClientState.antiCheat) {
            return instance.method_1434();
        }
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        class_636 interactionManager = client.field_1761;
        this.wallClimbingStatus = WallClimbing.resetClimbingStatus();
        if (client.field_1755 == null && clientPlayer != null && interactionManager != null) {
            if (JobManager.checkStatus(StatusType.DOUBLE_TAP)) {
                return false;
            }
            if (Config.JUMP_ASSIST && this.verifyPlayerCondition(client, clientPlayer)) {
                class_1268 hand = Utils.getHandHoldingWindCharge(client, clientPlayer);
                if (hand == null && ElytraBoost.isElytraBoostIdle()) {
                    hand = Utils.findToSetWindCharge(clientPlayer);
                }
                if (hand != null) {
                    class_1799 windChargeItem = clientPlayer.method_5998(hand);
                    if (!clientPlayer.method_7357().method_7904(windChargeItem)) {
                        float orgPitch = clientPlayer.method_5695(1.0f);
                        float orgYaw = clientPlayer.method_5705(1.0f);
                        if (this.wallClimbingStatus.canClimbing()) {
                            clientPlayer.method_36457(77.5f);
                            clientPlayer.method_36456(orgYaw + (float)(-45 + this.wallClimbingStatus.offset() * 45));
                            this.wallClimbingStatus = WallClimbing.resetClimbingStatus();
                            if (ElytraBoost.isElytraBoostIdle()) {
                                RefillManager.setRefillData(StatusType.AUTO_REFILL, Utils.getHandToSlot(clientPlayer, hand), windChargeItem.method_7909(), 0);
                                clientPlayer.method_6104(hand);
                                interactionManager.method_2919((class_1657)clientPlayer, hand);
                            }
                            clientPlayer.field_6004 = 90.0f;
                            clientPlayer.method_36457(orgPitch);
                            clientPlayer.method_36456(orgYaw);
                            MaceAttackAssistanceClient.afterJump(clientPlayer, clientPlayer.method_31548().method_67532(), -1);
                        } else if (ElytraBoost.isElytraBoostIdle() && clientPlayer.method_24828()) {
                            if (this.checkJumpMode(clientPlayer)) {
                                clientPlayer.method_36457(90.0f);
                                RefillManager.setRefillData(StatusType.AUTO_REFILL, Utils.getHandToSlot(clientPlayer, hand), windChargeItem.method_7909(), 0);
                                clientPlayer.method_6104(hand);
                                interactionManager.method_2919((class_1657)clientPlayer, hand);
                                clientPlayer.field_6004 = 90.0f;
                                clientPlayer.method_36457(orgPitch);
                                clientPlayer.method_36456(orgYaw);
                                MaceAttackAssistanceClient.afterJump(clientPlayer, clientPlayer.method_31548().method_67532(), -1);
                            } else if (clientPlayer.method_5715() || JumpController.ON_SLIME_BLOCK) {
                                MaceAttackAssistanceClient.sneakChargeJump(clientPlayer, hand);
                            } else {
                                MaceAttackAssistanceClient.requireChargeJump = true;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return instance.method_1434();
    }

    @Inject(method={"method_3129"}, at={@At(value="TAIL")})
    private void jump(CallbackInfo ci) {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer != null) {
            class_10185 clientPlayerInput;
            if (JumpController.JUMP) {
                clientPlayerInput = clientPlayer.field_3913.field_54155;
                clientPlayer.field_3913.field_54155 = new class_10185(clientPlayerInput.comp_3159(), clientPlayerInput.comp_3160(), clientPlayerInput.comp_3161(), clientPlayerInput.comp_3162(), true, clientPlayerInput.comp_3164(), clientPlayerInput.comp_3165());
            }
            if (FlappingSuppression.jumpSuppressionCounter > 0) {
                clientPlayerInput = clientPlayer.field_3913.field_54155;
                clientPlayer.field_3913.field_54155 = new class_10185(clientPlayerInput.comp_3159(), clientPlayerInput.comp_3160(), clientPlayerInput.comp_3161(), clientPlayerInput.comp_3162(), false, clientPlayerInput.comp_3164(), clientPlayerInput.comp_3165());
            }
        }
    }

    @Unique
    private boolean verifyPlayerCondition(class_310 client, class_746 clientPlayer) {
        this.wallClimbingStatus = WallClimbing.canEasyWallClimbing(client, clientPlayer);
        if (!client.field_1690.field_1867.method_1434() && !client.field_1690.field_1832.method_1434()) {
            return false;
        }
        if (!client.field_1690.field_1903.method_1434()) {
            return false;
        }
        if (clientPlayer.method_5869()) {
            return false;
        }
        if (clientPlayer.method_5681()) {
            return false;
        }
        if (clientPlayer.method_6128()) {
            return false;
        }
        JumpController.ON_SLIME_BLOCK = Utils.isOnSlimeBlock(clientPlayer);
        return !Config.JUMP_SPAM || clientPlayer.method_24828() || JumpController.ON_SLIME_BLOCK || this.wallClimbingStatus.canClimbing();
    }

    @Unique
    private boolean checkJumpMode(class_746 clientPlayer) {
        Config.JumpMode mode;
        Config.JumpMode jumpMode = mode = Utils.isUsingElytra(clientPlayer) ? Config.IN_USE_ELYTRA_JUMP_MODE : Config.NOT_IN_USE_ELYTRA_JUMP_MODE;
        if (mode == Config.JumpMode.Toggle) {
            return Config.TOGGLE_JUMP_MODE;
        }
        return mode == Config.JumpMode.Low;
    }
}


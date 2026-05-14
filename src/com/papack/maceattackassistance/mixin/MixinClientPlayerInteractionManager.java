/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_1041
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1588
 *  net.minecraft.class_1657
 *  net.minecraft.class_1661
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_310
 *  net.minecraft.class_3489
 *  net.minecraft.class_3675
 *  net.minecraft.class_636
 *  net.minecraft.class_6862
 *  net.minecraft.class_746
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.ApproachSupportLine;
import com.papack.maceattackassistance.client.AutoElytraSwap;
import com.papack.maceattackassistance.client.ElytraBoost;
import com.papack.maceattackassistance.client.FriendManager;
import com.papack.maceattackassistance.client.HotSwap;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.MacroController;
import com.papack.maceattackassistance.client.PrevSlotManager;
import com.papack.maceattackassistance.client.RefillManager;
import com.papack.maceattackassistance.client.RocketBlitz;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.StunSlam;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1588;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_3675;
import net.minecraft.class_636;
import net.minecraft.class_6862;
import net.minecraft.class_746;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_636.class})
public abstract class MixinClientPlayerInteractionManager {
    @Shadow
    @Final
    private class_310 field_3712;

    @Shadow
    public abstract class_1269 method_2919(class_1657 var1, class_1268 var2);

    @Inject(method={"method_2919"}, at={@At(value="HEAD")}, cancellable=true)
    private void rocketBlitz(class_1657 player, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
        block17: {
            block19: {
                class_746 clientPlayer;
                block18: {
                    class_1657 en;
                    if (ZoomState.MAAClientState.antiCheat) {
                        return;
                    }
                    if (Config.DEBUG_SCREEN && (en = ApproachSupportLine.findNearestPlayer(this.field_3712, (class_746)player, 20, 20)) != null) {
                        MaceAttackAssistanceClient.LOGGER.info("[Use] {} : r {} : d {} : s {} : xz {} : p {}", (Object)player.method_5998(hand).method_7909().method_7876(), (Object)String.format("%.3f", (double)player.method_5739((class_1297)en) / player.method_18798().method_1033()), (Object)String.format("%.3f", Float.valueOf(player.method_5739((class_1297)en))), (Object)String.format("%.3f", player.method_18798().method_1033()), (Object)String.format("%.3f", player.method_18798().method_37267()), (Object)String.format("%.3f", Float.valueOf(player.method_36455())));
                    }
                    if (AutoElytraSwap.getFlag()) {
                        AutoElytraSwap.setFlag(false);
                        return;
                    }
                    if (!Utils.checkPlayerUUID((class_1297)player)) {
                        cir.cancel();
                    }
                    if (!(clientPlayer = (class_746)player).method_73183().method_8608() || clientPlayer.method_7325() || clientPlayer.method_68878()) break block17;
                    if (!Config.ROCKET_BLITZ || !ElytraBoost.isElytraBoostIdle() || !JobManager.checkOrderIsEmpty() || MaceAttackAssistanceClient.requireChargeJump) break block18;
                    class_1661 playerInventory = clientPlayer.method_31548();
                    class_1799 offHandStack = clientPlayer.method_6079();
                    class_1041 windowHandle = this.field_3712.method_22683();
                    boolean isTriggerPressed = class_3675.method_15987((class_1041)windowHandle, (int)Config.ROCKET_TRIGGER.getGlfwKey());
                    int rocketSlot = RocketBlitz.getRocketSlotId(clientPlayer);
                    int process = 0;
                    if (Utils.isUsingElytra(clientPlayer) && !clientPlayer.method_24828() && isTriggerPressed) {
                        if (rocketSlot > -1) {
                            process = 1;
                        } else if (offHandStack.method_31574(class_1802.field_8639) && (Config.PRIORITIZE_ROCKET || ElytraBoost.isNotUsableItems(this.field_3712, clientPlayer.method_6047()))) {
                            process = 2;
                        }
                    }
                    int beforeSlot = -1;
                    switch (process) {
                        case 1: {
                            int currentSlot;
                            int slot = PrevSlotManager.getLastOrderSlot();
                            int n = currentSlot = slot > -1 ? slot : clientPlayer.method_31548().method_67532();
                            if (rocketSlot != currentSlot) {
                                playerInventory.method_61496(rocketSlot);
                            }
                            if (hand == class_1268.field_5810) {
                                beforeSlot = currentSlot;
                                this.method_2919(player, class_1268.field_5808);
                                cir.setReturnValue((Object)class_1269.field_5814);
                            }
                            if (clientPlayer.method_5998(hand).method_31574(class_1802.field_8639)) {
                                JobManager.setOrder(StatusType.ROCKET, beforeSlot > -1 ? beforeSlot : currentSlot);
                                MixinClientPlayerInteractionManager.setAutoRefill(clientPlayer, rocketSlot, hand);
                                break;
                            }
                            break block19;
                        }
                        case 2: {
                            if (clientPlayer.method_5998(hand).method_31574(class_1802.field_8639)) {
                                JobManager.setOrder(StatusType.ROCKET, 9);
                                MixinClientPlayerInteractionManager.setAutoRefill(clientPlayer, 9, hand);
                            }
                            if (hand == class_1268.field_5808) {
                                cir.setReturnValue((Object)class_1269.field_5814);
                                break;
                            }
                            break block19;
                        }
                        default: {
                            MixinClientPlayerInteractionManager.setAutoRefill(clientPlayer, hand == class_1268.field_5808 ? clientPlayer.method_31548().method_67532() : 9, hand);
                        }
                    }
                    break block19;
                }
                MixinClientPlayerInteractionManager.setAutoRefill(clientPlayer, hand == class_1268.field_5808 ? clientPlayer.method_31548().method_67532() : 9, hand);
            }
            ElytraBoost.flag_elytra_boost = false;
        }
    }

    @Unique
    private static void setAutoRefill(class_746 clientPlayer, int slot, class_1268 hand) {
        class_1799 itemStack;
        if (Config.AUTO_REFILL && (itemStack = clientPlayer.method_5998(hand)).method_7946()) {
            RefillManager.setRefillData(StatusType.AUTO_REFILL, slot, itemStack.method_7909(), 2);
        }
    }

    @Inject(method={"method_2918"}, at={@At(value="HEAD")}, cancellable=true)
    private void extremeAttackMode(class_1657 clientPlayer, class_1297 target, CallbackInfo ci) {
        if (ZoomState.MAAClientState.antiCheat) {
            return;
        }
        if (ZoomState.KeyManager.keyManager()) {
            return;
        }
        if (JobManager.jumpOption() && target instanceof class_1657) {
            return;
        }
        StunSlam.flagPreAxeCalled = false;
        boolean chkUuid = Utils.checkPlayerUUID((class_1297)clientPlayer);
        boolean t = target == null || !target.method_5805() || Config.FRIEND_PROTECTION && FriendManager.isFriend(target.method_5667());
        boolean f0 = t || !chkUuid;
        int d = clientPlayer.field_6012;
        boolean f1 = StunSlam.CHK_PLAYER_AGE == d;
        boolean flag0 = f0 || f1;
        StunSlam.CHK_PLAYER_AGE = d;
        if (!flag0 && !MaceAttackAssistanceClient.should_attack_interval) {
            if (!Config.EXTREME) {
                return;
            }
            if (target instanceof class_1309 && !JobManager.checkAttackStatus() && !Utils.canDoubleTap() && !MaceAttackAssistanceClient.getTempExtremeFlag()) {
                class_1661 playerInventory = clientPlayer.method_31548();
                boolean isOverThreshold = this.canAttackAssist((class_746)clientPlayer);
                boolean onGroundOrJumpAxe = false;
                int maceSlot = -1;
                if (!MaceAttackAssistanceClient.shouldShieldBreak) {
                    onGroundOrJumpAxe = StunSlam.preSelectAxe(this.field_3712, (class_746)clientPlayer, target);
                }
                if (MaceAttackAssistanceClient.shouldShieldBreak) {
                    MaceAttackAssistanceClient.shouldShieldBreak = false;
                    MaceAttackAssistanceClient.flagPreAxe = false;
                }
                boolean flagBreach = false;
                boolean isWearing = Utils.isWearingArmor(target);
                if (Config.HOT_SWAP) {
                    if (Config.SNAPBACK && MacroController.canSnapback((class_746)clientPlayer, (class_1309)target)) {
                        maceSlot = HotSwap.getBreachMaceSlotId((class_746)clientPlayer);
                    } else if (isOverThreshold) {
                        maceSlot = HotSwap.getPrimaryMaceSlotId((class_746)clientPlayer);
                    } else if (!(onGroundOrJumpAxe || !Config.BREACH_SWAP || clientPlayer.method_24828() && !Config.BREACH_ON_GROUND || Config.BREACH_LIMITED && !(target instanceof class_1588) && !(target instanceof class_1657))) {
                        int mode = isWearing ? this.getBehavior(Config.SWORD_SWAP_OR_BREACH_SWAP) : this.getBehavior(Config.BEHAVIOR_NOT_WEARING_ARMOR);
                        maceSlot = switch (mode) {
                            case 1 -> Utils.findItemInHotbarByTags((class_6862<class_1792>)(Config.SWORD_OR_AXE ? class_3489.field_42611 : class_3489.field_42612));
                            case 2 -> HotSwap.getBreachMaceSlotId((class_746)clientPlayer);
                            default -> -1;
                        };
                        flagBreach = true;
                    }
                }
                if (maceSlot > -1) {
                    PrevSlotManager.setPrevSlot(flagBreach ? StatusType.BREACH : StatusType.NONE, playerInventory.method_67532(), 0);
                    playerInventory.method_61496(maceSlot);
                    MaceAttackAssistanceClient.setAttackInterval();
                }
            }
            return;
        }
        ci.cancel();
    }

    @Unique
    private int getBehavior(Config.Behavior behaviour) {
        return switch (behaviour) {
            default -> throw new MatchException(null, null);
            case Config.Behavior.Off -> 0;
            case Config.Behavior.SwordSwap -> 1;
            case Config.Behavior.BreachSwap -> 2;
        };
    }

    @Unique
    private boolean canAttackAssist(@NotNull class_746 clientPlayer) {
        return clientPlayer.method_18798().method_10214() <= -0.447;
    }

    @Inject(method={"method_2918"}, at={@At(value="TAIL")})
    private void attackLog(class_1657 player, class_1297 target, CallbackInfo ci) {
        StunSlam.lastSlot = player.method_31548().method_67532();
        if (Config.DEBUG_SCREEN) {
            MaceAttackAssistanceClient.LOGGER.info("[Attack] ex: {}, d: {}, age: {} , w: {} , v: {}", (Object)Config.EXTREME, (Object)Float.valueOf(player.method_5739(target)), (Object)player.field_6012, (Object)player.method_6047().method_7909().method_7876(), (Object)JobManager.checkValue(StatusType.STUN_SLAM));
        }
    }
}


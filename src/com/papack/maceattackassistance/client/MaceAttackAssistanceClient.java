/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
 *  net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents
 *  net.fabricmc.fabric.api.event.player.AttackBlockCallback
 *  net.fabricmc.fabric.api.event.player.UseBlockCallback
 *  net.fabricmc.fabric.api.event.player.UseEntityCallback
 *  net.fabricmc.fabric.api.event.player.UseItemCallback
 *  net.minecraft.class_10185
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1296
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1309
 *  net.minecraft.class_1421
 *  net.minecraft.class_1429
 *  net.minecraft.class_1439
 *  net.minecraft.class_1480
 *  net.minecraft.class_1569
 *  net.minecraft.class_1588
 *  net.minecraft.class_1646
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_2960
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3489
 *  net.minecraft.class_3726
 *  net.minecraft.class_4538
 *  net.minecraft.class_5498
 *  net.minecraft.class_636
 *  net.minecraft.class_746
 *  net.minecraft.class_9334
 *  net.minecraft.class_9362
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.AimCondition;
import com.papack.maceattackassistance.client.AutoElytraSwap;
import com.papack.maceattackassistance.client.AutoZoomInOut;
import com.papack.maceattackassistance.client.BeamRenderHandler;
import com.papack.maceattackassistance.client.ColorData;
import com.papack.maceattackassistance.client.Debug;
import com.papack.maceattackassistance.client.DebugScreen;
import com.papack.maceattackassistance.client.ElytraBoost;
import com.papack.maceattackassistance.client.EnderPearlManager;
import com.papack.maceattackassistance.client.FlappingSuppression;
import com.papack.maceattackassistance.client.FriendKeyHandler;
import com.papack.maceattackassistance.client.FriendManager;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.JumpController;
import com.papack.maceattackassistance.client.KeyInput;
import com.papack.maceattackassistance.client.MaceParticle;
import com.papack.maceattackassistance.client.PrevSlotManager;
import com.papack.maceattackassistance.client.RefillManager;
import com.papack.maceattackassistance.client.SpearAttacks;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.TickScheduler;
import com.papack.maceattackassistance.client.ToggleElytra;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import com.papack.maceattackassistance.client.config.ConfigOperation;
import com.papack.maceattackassistance.mixin.KeyBindingInvoker;
import com.papack.maceattackassistance.mixin.MinecraftClientInvoker;
import com.papack.maceattackassistance.network.MaaPayload;
import java.util.List;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.class_10185;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1421;
import net.minecraft.class_1429;
import net.minecraft.class_1439;
import net.minecraft.class_1480;
import net.minecraft.class_1569;
import net.minecraft.class_1588;
import net.minecraft.class_1646;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3489;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_5498;
import net.minecraft.class_636;
import net.minecraft.class_746;
import net.minecraft.class_9334;
import net.minecraft.class_9362;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(value=EnvType.CLIENT)
public class MaceAttackAssistanceClient
implements ClientModInitializer {
    private static boolean MAA_ALLOWED = false;
    private static boolean ALLOW_SERVER = false;
    private static boolean ALLOW_AIM = false;
    public static useEntityEventRefillStatus useEventRefillStatus;
    public static final String MOD_ID = "maceattackassistance";
    public static Logger LOGGER;
    public static boolean flagPreAxe;
    public static boolean shouldShieldBreak;
    public static boolean flag_attack_canceled;
    public static boolean flag_charging_particle;
    public static boolean should_attack_interval;
    public static int waiting_tick_counter;
    public static int jumpCooldown;
    private static boolean shouldPressJump;
    private static class_1297 targetMob;
    private static float targetYaw;
    private static float targetPitch;
    private static int target_life_counter;
    private static final int TARGET_LIFE_COUNT = 20;
    private static int useEntityEventRefillCounter;
    private static boolean instant_attack;
    private static boolean wasEating;
    public static boolean requireChargeJump;
    private static int counterAfterChargeJump;
    private static int worldCheckCounter;
    private static boolean tempExtremeFlag;

    public static boolean getTempExtremeFlag() {
        return tempExtremeFlag;
    }

    public static class_1297 getTargetMob() {
        return targetMob;
    }

    public static void setTargetMob(class_1297 entity) {
        targetMob = entity;
    }

    public static boolean stopRightNow_ImDisappointedInYou() {
        return !MAA_ALLOWED;
    }

    public void onInitializeClient() {
        Config.initialize();
        JobManager.init();
        TickScheduler.init();
        ZoomState.init();
        ColorData.initialize();
        FriendManager.init();
        try {
            ConfigOperation.existFile();
            ConfigOperation.loadFile();
            ConfigOperation.saveFile();
            LOGGER.info("load config : completed");
        }
        catch (Exception e) {
            LOGGER.error("load config : failed");
        }
        ClientPlayConnectionEvents.INIT.register((handler, client) -> {
            JobManager.JUMP_MODE = false;
            ZoomState.MAAClientState.clear();
        });
        ClientPlayNetworking.registerGlobalReceiver(MaaPayload.ID, (payload, context) -> TickScheduler.setConditionTask(() -> class_310.method_1551().field_1724 != null, () -> {
            class_746 patt0$temp = context.client().field_1724;
            if (patt0$temp instanceof class_746) {
                class_746 clientPlayer = patt0$temp;
                if (payload.uuid().equals(clientPlayer.method_5667())) {
                    JobManager.JUMP_MODE = true;
                    LOGGER.info("packet received");
                }
            }
        }));
        HudElementRegistry.addLast((class_2960)class_2960.method_60654((String)MOD_ID), (context, tickCounter) -> {
            class_310 client = class_310.method_1551();
            if (client.field_1755 == null) {
                if (Config.DEBUG_SCREEN) {
                    DebugScreen.debugOverlay(context);
                }
                if (Config.ZOOM_CAMERA && Config.PERSPECTIVE_BACK_CROSSHAIR && client.field_1690.method_31044() == class_5498.field_26665) {
                    ZoomState.renderCrosshair(context);
                }
            }
        });
        WorldRenderEvents.BEFORE_ENTITIES.register(context -> {
            class_310 client = class_310.method_1551();
            class_746 clientPlayer = client.field_1724;
            if (clientPlayer != null) {
                try {
                    ZoomState.moveCamera(client, clientPlayer, client.method_61966());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        WorldRenderEvents.AFTER_ENTITIES.register(context -> MaceAttackAssistanceClient.onRenderTick(class_310.method_1551()));
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (world.method_8608() && !player.method_68878() && Utils.checkPlayerUUID((class_1297)player) && flag_attack_canceled) {
                return class_1269.field_5814;
            }
            return class_1269.field_5811;
        });
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.method_8608() && !player.method_68878() && Utils.checkPlayerUUID((class_1297)player)) {
                boolean canPlace;
                class_1799 stack = player.method_5998(hand);
                class_1792 item = stack.method_7909();
                if (Config.PRIORITIZE_WIND_CHARGE && !ElytraBoost.isElytraBoostIdle() && !stack.method_31574(class_1802.field_49098)) {
                    class_310 client = class_310.method_1551();
                    class_636 interactionManager = client.field_1761;
                    class_1268 windChargeHand = Utils.getHandHoldingWindCharge(class_310.method_1551(), (class_746)player);
                    if (interactionManager != null && windChargeHand != null) {
                        player.method_6104(windChargeHand);
                        interactionManager.method_2919(player, windChargeHand);
                    }
                    return class_1269.field_5814;
                }
                if (item instanceof class_1747) {
                    class_1747 blockItem = (class_1747)item;
                    class_2248 block = blockItem.method_7711();
                    class_2338 placedPos = hitResult.method_17777().method_10093(hitResult.method_17780());
                    class_2680 state = block.method_9564();
                    canPlace = state.method_26184((class_4538)world, placedPos) && world.method_8628(state, placedPos, class_3726.method_16194());
                } else {
                    canPlace = stack.method_7946();
                }
                if (canPlace && player instanceof class_746) {
                    class_746 clientPlayer = (class_746)player;
                    MaceAttackAssistanceClient.autoRefillStackableItems(hand, clientPlayer, false);
                }
            }
            return class_1269.field_5811;
        });
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (world.method_8608() && !player.method_68878() && Config.AUTO_REFILL && Utils.checkPlayerUUID((class_1297)player)) {
                int refillSlot;
                class_746 clientPlayer = (class_746)player;
                class_1799 activeStack = clientPlayer.method_5998(hand);
                int n = refillSlot = hand == class_1268.field_5808 ? clientPlayer.method_31548().method_67532() : 9;
                if (activeStack.method_7946()) {
                    RefillManager.setRefillData(StatusType.AUTO_REFILL, refillSlot, activeStack.method_7909(), 0);
                }
            }
            return class_1269.field_5811;
        });
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (Utils.checkPlayerUUID((class_1297)player)) {
                class_1268 windChargeHand;
                if (Config.PRIORITIZE_WIND_CHARGE && world.method_8608() && !ElytraBoost.isElytraBoostIdle()) {
                    class_1799 itemStack = player.method_5998(hand);
                    if (!itemStack.method_31574(class_1802.field_49098)) {
                        return class_1269.field_5814;
                    }
                } else if (Config.AUTO_WIND_CHARGE_SELECT && Config.JUMP_ASSIST && ElytraBoost.isElytraBoostIdle() && (class_310.method_1551().field_1690.field_1867.method_1434() || class_310.method_1551().field_1690.field_1832.method_1434()) && !player.method_6128() && !player.method_24828() && player.method_36455() > 70.0f && !player.method_5998(hand).method_31574(class_1802.field_49098) && (windChargeHand = Utils.findToSetWindCharge((class_746)player)) != null) {
                    MaceAttackAssistanceClient.afterJump((class_746)player, player.method_31548().method_67532(), 3);
                }
            }
            return class_1269.field_5811;
        });
        ClientEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            class_1297 cameraEntity = class_310.method_1551().method_1560();
            if (world.method_8608() && cameraEntity != null && entity.method_5667().equals(cameraEntity.method_5667())) {
                class_304.method_1437();
            }
        });
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            class_746 clientPlayer = client.field_1724;
            if (client.field_1755 == null && clientPlayer != null) {
                SpearAttacks.spearAssist(client, clientPlayer);
                if (instant_attack && client.field_1690.field_1886.method_1434()) {
                    class_1297 patt0$temp = Utils.getCrosshairEntity();
                    if (patt0$temp instanceof class_1309) {
                        class_1309 target = (class_1309)patt0$temp;
                        if (!(JobManager.checkAttackStatus() || Config.EXTREME && target.method_6039())) {
                            clientPlayer.method_6104(clientPlayer.method_6058());
                            if (Config.DEBUG_SCREEN) {
                                LOGGER.info("instant attack : {} , age : {}", (Object)clientPlayer.method_6047().method_7909().method_7876(), (Object)clientPlayer.field_6012);
                            }
                            ((MinecraftClientInvoker)client).doAttackInvoker();
                        }
                    }
                    instant_attack = false;
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            class_636 interactionManager = client.field_1761;
            class_746 clientPlayer = client.field_1724;
            boolean flag0 = client.method_1576() != null || JobManager.JUMP_MODE;
            boolean flag1 = ZoomState.MAAClientState.allowedLevels > 1;
            JobManager.QUICK_JUMP = flag0 || ZoomState.MAAClientState.allowedLevels == 3;
            boolean bl = JobManager.JUMP_OPTION = flag0 || flag1;
            if (!ZoomState.MAAClientState.antiCheat && client.field_1755 == null && clientPlayer != null && interactionManager != null) {
                boolean vSpeed;
                if (Config.AUTO_ELYTRA && AutoElytraSwap.condition()) {
                    AutoElytraSwap.setFlag(true);
                    class_1268 hand = AutoElytraSwap.getHandStatus();
                    if (hand != null) {
                        clientPlayer.method_6104(hand);
                        interactionManager.method_2919((class_1657)clientPlayer, hand);
                    }
                }
                AimCondition.tick(clientPlayer);
                EnderPearlManager.tick();
                RefillManager.tick(client, clientPlayer);
                FriendKeyHandler.tick();
                PrevSlotManager.tick(client);
                AutoZoomInOut.autoZoomInOut();
                boolean bl2 = vSpeed = Utils.speedOverThreshold(clientPlayer) > 0;
                if ((Config.DOUBLE_TAP && Utils.canDoubleTap() || Config.STUN_SLAMMING && Utils.canStunSlam(targetMob) || vSpeed) && Config.EXTREME && (client.field_1690.field_1886.method_1434() || vSpeed)) {
                    tempExtremeFlag = true;
                    Config.EXTREME = false;
                }
                if (!(JobManager.checkAttackStatus() || client.field_1690.field_1886.method_1434() || vSpeed || !tempExtremeFlag && !clientPlayer.method_24828())) {
                    tempExtremeFlag = false;
                    Config.EXTREME = true;
                }
                if (counterAfterChargeJump > 0) {
                    --counterAfterChargeJump;
                }
                if (Config.FLAP_SUPPRESSION) {
                    FlappingSuppression.tick(clientPlayer);
                    if (FlappingSuppression.isRecentlyFluttering() && FlappingSuppression.jumpSuppressionCounter == 0) {
                        FlappingSuppression.jumpSuppressionCounter = Config.FLAP_SUPPRESSION_TICK;
                    }
                }
                if (worldCheckCounter > 0) {
                    --worldCheckCounter;
                }
                if (client.field_1687 != null && worldCheckCounter == 0) {
                    boolean allow;
                    BeamRenderHandler.clearTargetList();
                    boolean bl3 = allow = Config.DEBUG_SCREEN || Config.TARGET_SEARCH_MODE;
                    if (allow || clientPlayer.method_6128()) {
                        BeamRenderHandler.targetList = Config.PARALLEL_SEARCH ? BeamRenderHandler.getWorldEntityListParallel(client, clientPlayer, (class_1937)client.field_1687) : BeamRenderHandler.getWorldEntityList(client, clientPlayer, (class_1937)client.field_1687);
                    }
                    if (targetMob != null && BeamRenderHandler.targetList != null) {
                        BeamRenderHandler.targetList.add(targetMob);
                    }
                    worldCheckCounter = Config.RADAR_UPDATE_INTERVAL;
                }
                if (Config.EXTREME && !client.field_1690.field_1886.method_1434() && JobManager.checkOrderIsEmpty()) {
                    if (should_attack_interval) {
                        if (clientPlayer.method_24828()) {
                            targetMob = null;
                        }
                        clientPlayer.method_38785();
                        if (clientPlayer.method_24828()) {
                            ((KeyBindingInvoker)client.field_1690.field_1886).invokerReset();
                        }
                    }
                    waiting_tick_counter = -1;
                    should_attack_interval = false;
                    flagPreAxe = false;
                }
                if (requireChargeJump) {
                    MaceAttackAssistanceClient.chargeJump(clientPlayer, interactionManager);
                    requireChargeJump = false;
                }
                JumpController.tick();
                ElytraBoost.elytraBoost(client, clientPlayer);
                JobManager.tick(client, clientPlayer);
                if (useEntityEventRefillCounter > 0 && useEventRefillStatus != null && --useEntityEventRefillCounter == 0 && MaceAttackAssistanceClient.useEventRefillStatus.beforeCount > clientPlayer.method_5998(MaceAttackAssistanceClient.useEventRefillStatus.hand).method_7947()) {
                    MaceAttackAssistanceClient.autoRefillStackableItems(MaceAttackAssistanceClient.useEventRefillStatus.hand, clientPlayer, true);
                    useEventRefillStatus = null;
                }
                if (Config.AUTO_REFILL) {
                    class_1799 itemStack = clientPlayer.method_6047();
                    if (itemStack.method_58694(class_9334.field_50075) != null) {
                        if (clientPlayer.method_6115()) {
                            if (!wasEating) {
                                wasEating = true;
                            }
                        } else if (wasEating) {
                            int slot = clientPlayer.method_31548().method_67532();
                            RefillManager.setRefillData(StatusType.AUTO_REFILL, slot, itemStack.method_7909(), 0);
                            wasEating = false;
                        }
                    } else {
                        wasEating = false;
                    }
                }
                this.jumpSpam(client, clientPlayer);
                if (targetMob != null && JobManager.checkOrderIsEmpty()) {
                    if (target_life_counter > 0) {
                        --target_life_counter;
                    } else {
                        targetMob = null;
                    }
                    should_attack_interval = false;
                }
                if (waiting_tick_counter > 0) {
                    --waiting_tick_counter;
                }
                this.onClientTick(client, clientPlayer);
                boolean bl4 = flag_charging_particle = !flag_charging_particle;
                if (flag_charging_particle && Config.ATTACK_ASSISTANCE && MaceAttackAssistanceClient.playerCheck(client, clientPlayer) && clientPlayer.method_18798().method_10214() < Config.VELOCITY_BY_DISTANCE[Config.HEIGHT_THRESHOLD] && MaceAttackAssistanceClient.isAllowedItem(clientPlayer.method_6047()) && Config.MACE_PARTICLE != Config.WeaponParticle.off && !client.field_1690.field_1842) {
                    MaceParticle.maceParticleHandler(client, clientPlayer, clientPlayer.method_18798().method_10214() <= Config.VELOCITY_BY_DISTANCE[Config.PARTICLE_TRANSITION_THRESHOLD]);
                }
                client.execute(() -> {
                    if (flag_attack_canceled && !client.field_1690.field_1886.method_1434()) {
                        ((KeyBindingInvoker)client.field_1690.field_1886).invokerReset();
                        flag_attack_canceled = false;
                    }
                });
                KeyInput.keyInputInGame(client, clientPlayer, interactionManager);
            }
        });
    }

    public static void autoRefillStackableItems(class_1268 hand, class_746 clientPlayer, boolean isEntityInteract) {
        class_1799 handStack;
        class_1799 class_17992 = handStack = hand == class_1268.field_5808 ? clientPlayer.method_6047() : clientPlayer.method_6079();
        if (Config.AUTO_REFILL && handStack.method_7946() && (handStack.method_58694(class_9334.field_50075) == null || isEntityInteract) && !clientPlayer.method_7357().method_7904(handStack)) {
            int refillSlot = hand == class_1268.field_5808 ? clientPlayer.method_31548().method_67532() : 9;
            RefillManager.setRefillData(StatusType.AUTO_REFILL, refillSlot, handStack.method_7909(), 0);
        }
    }

    private static boolean playerCheck(class_310 client, class_746 clientPlayer) {
        if (!client.field_1690.field_1886.method_1434()) {
            return false;
        }
        if (clientPlayer.method_24828()) {
            return false;
        }
        if (clientPlayer.method_5869()) {
            return false;
        }
        if (clientPlayer.method_5681()) {
            return false;
        }
        return !clientPlayer.method_7325();
    }

    private void onClientTick(class_310 client, class_746 clientPlayer) {
        if (JobManager.checkOrderIsEmpty() && (!Config.ATTACK_ASSISTANCE || clientPlayer.method_18798().method_10214() > Config.VELOCITY_BY_DISTANCE[Config.HEIGHT_THRESHOLD] || clientPlayer.method_18798().method_10214() > 0.0) && !JobManager.checkAttackStatus()) {
            targetMob = null;
            return;
        }
        targetMob = Config.AIM_RAYCAST ? Utils.findCrosshairClosestTarget((class_1657)clientPlayer, Config.AIM_RAYCAST_RANGE, Config.AIM_RAYCAST_RADIUS) : this.findNearestMob(client, clientPlayer);
        if (targetMob != null) {
            target_life_counter = 20;
            MaceAttackAssistanceClient.calculateTargetYawPitch(clientPlayer, targetMob);
        }
    }

    public class_1297 findNearestMob(class_310 client, class_746 clientPlayer) {
        double downDistance = Math.max(50.0, (double)Config.RADAR_DOWNWARD);
        double closestDistance = Double.MAX_VALUE;
        class_1297 nearestMob = null;
        class_243 playerEyePos = clientPlayer.method_33571();
        class_243 playerPos = clientPlayer.method_73189();
        class_238 pBox = clientPlayer.method_5829();
        class_243 start = new class_243(pBox.field_1323, playerEyePos.method_10214() + 3.0, pBox.field_1321);
        class_243 end = new class_243(pBox.field_1320, playerEyePos.method_10214() - downDistance, pBox.field_1324);
        class_238 searchArea = new class_238(start, end).method_1009(5.0, 0.0, 5.0);
        if (client.field_1687 != null) {
            List nearbyEntities = client.field_1687.method_8333((class_1297)clientPlayer, searchArea, entity -> (!Config.FRIEND_PROTECTION || !FriendManager.isFriend(entity.method_5667())) && entity.method_5709() && entity.method_5805() && MaceAttackAssistanceClient.isAllowedTarget(entity) && Utils.isVisibleFromPlayer(client, clientPlayer, entity));
            for (class_1297 entity2 : nearbyEntities) {
                double distance = playerPos.method_1022(entity2.method_73189());
                if (Config.AIM_ASSIST && (Config.AIM_MODE == Config.AimMode.Track || Config.AIM_MODE == Config.AimMode.Auto && nearbyEntities.size() < Config.AUTO_MODE_THRESHOLD) && targetMob != null && targetMob.method_5667().equals(entity2.method_5667()) && Utils.isInAttackableRange(clientPlayer, targetMob)) {
                    return entity2;
                }
                if (!(distance < closestDistance)) continue;
                closestDistance = distance;
                nearestMob = entity2;
            }
        }
        return nearestMob;
    }

    private static void calculateTargetYawPitch(class_746 clientPlayer, class_1297 target) {
        class_243 playerPos = clientPlayer.method_33571();
        class_243 targetPos = Utils.getTargetPos(target, true, 1.0f);
        double dx = targetPos.field_1352 - playerPos.field_1352;
        double dy = targetPos.field_1351 - playerPos.field_1351;
        double dz = targetPos.field_1350 - playerPos.field_1350;
        double distance = Math.sqrt(dx * dx + dz * dz);
        double angle = (Math.toDegrees(Math.atan2(dz, dx)) + 180.0) % 360.0 - 270.0;
        double pitchCorrectionFactor = 0.9 * Math.min(clientPlayer.method_18798().method_10214() / 2.0, 1.0);
        targetYaw = Utils.normalizeAngle((float)angle);
        targetPitch = (float)(-Math.toDegrees(Math.atan2(dy + pitchCorrectionFactor, distance)));
    }

    public static void onRenderTick(class_310 client) {
        boolean flag;
        class_746 clientPlayer = client.field_1724;
        if (targetMob == null || clientPlayer == null || !client.field_1690.field_1886.method_1434() || !JobManager.QUICK_JUMP && targetMob instanceof class_1657) {
            return;
        }
        if (waiting_tick_counter > 0) {
            return;
        }
        float currentYaw = clientPlayer.method_36454();
        float currentPitch = clientPlayer.method_36455();
        float angleDiffYaw = Math.abs(targetYaw - currentYaw);
        float angleDiffPitch = Math.abs(targetPitch - currentPitch);
        boolean bl = Config.AIM_FALL_THRESHOLD == 2 ? clientPlayer.method_18798().method_10214() < Config.VELOCITY_BY_DISTANCE[1] : (flag = AimCondition.canAim());
        if (flag || JobManager.checkAttackStatus()) {
            boolean d;
            float setValueYaw = MaceAttackAssistanceClient.getMovingDelta(client, clientPlayer, angleDiffYaw, true);
            float setValuePitch = MaceAttackAssistanceClient.getMovingDelta(client, clientPlayer, angleDiffPitch, false);
            float range = Utils.rangeByWeapons(clientPlayer);
            boolean bl2 = d = targetMob != null && clientPlayer.method_5739(targetMob) <= range;
            if (Config.AIM_ASSIST && (!clientPlayer.method_6128() || Utils.canDoubleTap() && d) && (!Config.LEGACY_AIM_MODE || d)) {
                clientPlayer.method_36456(Utils.lerpYaw(currentYaw, targetYaw, setValueYaw));
                clientPlayer.method_36457(Utils.lerpPitch(currentPitch, targetPitch, setValuePitch));
            }
        }
        if (targetMob != null && client.field_1692 != null && client.field_1692.method_5667().equals(targetMob.method_5667()) && (clientPlayer.method_24828() || clientPlayer.method_18798().method_10214() > Config.VELOCITY_BY_DISTANCE[1]) && JobManager.checkOrderIsEmpty()) {
            targetMob = null;
        }
    }

    private static float getMovingDelta(class_310 client, class_746 clientPlayer, float angleDiff, boolean isYaw) {
        boolean aimFlag;
        float deltaTime = client.method_61966().method_60636();
        double fallSpeed = Math.abs(clientPlayer.method_18798().method_10214());
        double distanceToTarget = clientPlayer.method_5739(targetMob);
        double distanceThreshold = Utils.rangeByWeapons(clientPlayer);
        boolean bl = aimFlag = (Config.LEGACY_AIM_MODE || Config.AIM_FORCED_ADJUSTMENT) && !Config.AIM_RAYCAST;
        if (waiting_tick_counter <= 0 && distanceToTarget <= distanceThreshold) {
            instant_attack = true;
            if (aimFlag) {
                return 1.0f;
            }
        }
        if (aimFlag && waiting_tick_counter <= 0 && distanceToTarget <= distanceThreshold && clientPlayer.method_18798().method_10214() < Config.VELOCITY_BY_DISTANCE[10]) {
            return 1.0f;
        }
        double normalizedFallSpeed = Math.abs(Math.min(fallSpeed / 1.5, 1.0));
        double visionMoveSpeed = MaceAttackAssistanceClient.getVisionMoveSpeed(angleDiff, isYaw, normalizedFallSpeed);
        return (float)visionMoveSpeed * deltaTime;
    }

    private static double getVisionMoveSpeed(float angleDiff, boolean isYaw, double normalizedFallSpeed) {
        double visionMoveSpeed;
        double normalizedAngleDiff = Math.min((double)(angleDiff / (float)(isYaw ? 180 : 90)), 1.0);
        if (isYaw) {
            double minSpeed = 0.03 + (double)Config.MAX_SPEED_YAW * 0.04;
            double maxSpeed = 0.07 + (double)Config.MAX_SPEED_YAW * 0.04;
            double sineFactor = 0.5 + Math.sin(Math.PI * normalizedAngleDiff) * 0.5;
            visionMoveSpeed = minSpeed + (maxSpeed - minSpeed) * sineFactor;
        } else {
            double minSpeed = 0.35;
            double maxSpeed = 0.8;
            double factor = Math.pow(normalizedFallSpeed, 0.5);
            visionMoveSpeed = minSpeed + (maxSpeed - minSpeed) * factor;
        }
        return visionMoveSpeed;
    }

    public static boolean isAllowedItem(class_1799 itemStack) {
        if (itemStack != null) {
            if (itemStack.method_31573(class_3489.field_42611)) {
                return true;
            }
            if (itemStack.method_7909() instanceof class_9362) {
                return true;
            }
            return itemStack.method_7909() instanceof class_1743;
        }
        return false;
    }

    public static boolean isAllowedTarget(class_1297 entity) {
        if (!Config.AIM_ASSIST) {
            return true;
        }
        if (entity instanceof class_1646 && Config.ALLOWED_VILLAGER) {
            return true;
        }
        if (entity instanceof class_1439 && Config.ALLOWED_IRON_GOLEM) {
            return true;
        }
        if (entity instanceof class_1588 && Config.ALLOWED_HOSTILE) {
            return true;
        }
        if (entity instanceof class_1569 && Config.ALLOWED_HOSTILE) {
            return true;
        }
        if (entity instanceof class_1429 && Config.ALLOWED_PASSIVE) {
            return true;
        }
        if (entity instanceof class_1296 && Config.ALLOWED_PASSIVE) {
            return true;
        }
        if (entity instanceof class_1421 && Config.ALLOWED_AMBIENT) {
            return true;
        }
        if (entity instanceof class_1480 && Config.ALLOWED_AMBIENT) {
            return true;
        }
        if (entity instanceof class_1657 && Config.ALLOWED_PLAYER) {
            return !Config.FRIEND_PROTECTION || !FriendManager.isFriend(entity.method_5667());
        }
        return Config.ALLOWED_AMBIENT;
    }

    private void jumpSpam(class_310 client, class_746 clientPlayer) {
        double vy = clientPlayer.method_18798().method_10214();
        if (!Config.JUMP_SPAM) {
            return;
        }
        if (!ElytraBoost.isElytraBoostIdle()) {
            return;
        }
        if (vy > 0.0 && vy < (double)Config.JUMP_SPAM_THRESHOLD * 0.1) {
            return;
        }
        if (shouldPressJump) {
            class_10185 clientPlayerInput = clientPlayer.field_3913.field_54155;
            clientPlayer.field_3913.field_54155 = new class_10185(clientPlayerInput.comp_3159(), clientPlayerInput.comp_3160(), clientPlayerInput.comp_3161(), clientPlayerInput.comp_3162(), false, clientPlayerInput.comp_3164(), clientPlayerInput.comp_3165());
            shouldPressJump = false;
        }
        if (this.jumpSpamCondition(client, clientPlayer)) {
            JumpController.setChargeJumpCounter(1);
            shouldPressJump = true;
            jumpCooldown = Config.JUMP_SPAM_TICK;
        }
        if (jumpCooldown > 0) {
            --jumpCooldown;
        }
    }

    private boolean jumpSpamCondition(class_310 client, class_746 clientPlayer) {
        if (!this.spamKeysIsPressed(client, clientPlayer)) {
            return false;
        }
        if (!this.verifyPlayerCondition(clientPlayer)) {
            return false;
        }
        if (!Utils.verifyGround(clientPlayer, 2) && counterAfterChargeJump <= 0) {
            return false;
        }
        return jumpCooldown == 0;
    }

    private boolean spamKeysIsPressed(class_310 client, class_746 clientPlayer) {
        boolean wearingElytra = ToggleElytra.isElytra(clientPlayer.method_6118(class_1304.field_6174));
        boolean isFalling = clientPlayer.method_18798().method_10214() < (Utils.verifyGround(clientPlayer, 2) ? 0.0 : Config.VELOCITY_BY_DISTANCE[2]);
        boolean isSpacePressed = client.field_1690.field_1903.method_1434();
        boolean isCtrlPressed = client.field_1690.field_1867.method_1434();
        boolean isShiftPressed = client.field_1690.field_1832.method_1434();
        return isSpacePressed && (isCtrlPressed || isShiftPressed || wearingElytra && isFalling);
    }

    private boolean verifyPlayerCondition(class_746 clientPlayer) {
        if (clientPlayer != null) {
            if (clientPlayer.method_6128()) {
                return false;
            }
            if (clientPlayer.method_68878()) {
                return false;
            }
            if (clientPlayer.method_7325()) {
                return false;
            }
            if (clientPlayer.method_18798().method_10214() <= 0.0 && clientPlayer.method_6128()) {
                return false;
            }
            return clientPlayer.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
        }
        return false;
    }

    public static void chargeJump(class_746 clientPlayer, class_636 interactionManager) {
        class_1268 hand;
        if (clientPlayer != null && interactionManager != null && (hand = Utils.getHandHoldingWindCharge(class_310.method_1551(), clientPlayer)) != null) {
            int refillSlot = hand == class_1268.field_5808 ? clientPlayer.method_31548().method_67532() : 9;
            RefillManager.setRefillData(StatusType.AUTO_REFILL, refillSlot, clientPlayer.method_5998(hand).method_7909(), 3);
            JumpController.setChargeJumpCounter(0);
            float prevPitch = clientPlayer.method_36455();
            clientPlayer.method_36457(90.0f);
            clientPlayer.method_6104(hand);
            interactionManager.method_2919((class_1657)clientPlayer, hand);
            clientPlayer.method_36457(prevPitch);
            counterAfterChargeJump = 20;
            MaceAttackAssistanceClient.afterJump(clientPlayer, clientPlayer.method_31548().method_67532(), -1);
            DebugScreen.lastY = clientPlayer.method_23318();
            Debug.previous_y = -64.0;
        }
        requireChargeJump = false;
    }

    public static void sneakChargeJump(class_746 clientPlayer, class_1268 hand) {
        class_310 client = class_310.method_1551();
        class_636 interactionManager = client.field_1761;
        if (clientPlayer != null && hand != null && interactionManager != null) {
            int refillSlot = hand == class_1268.field_5808 ? clientPlayer.method_31548().method_67532() : 9;
            RefillManager.setRefillData(StatusType.AUTO_REFILL, refillSlot, clientPlayer.method_5998(hand).method_7909(), 3);
            float prevPitch = clientPlayer.method_36455();
            clientPlayer.method_36457(90.0f);
            clientPlayer.method_6104(hand);
            interactionManager.method_2919((class_1657)clientPlayer, hand);
            clientPlayer.method_36457(prevPitch);
            counterAfterChargeJump = 20;
            MaceAttackAssistanceClient.afterJump(clientPlayer, clientPlayer.method_31548().method_67532(), -1);
            DebugScreen.lastY = clientPlayer.method_23318();
            Debug.previous_y = -64.0;
        }
    }

    public static void afterJump(class_746 clientPlayer, int selectedSlot, int wait) {
        if (wait < 1) {
            wait = Utils.isUsingElytra(clientPlayer) ? 5 : 1;
        }
        PrevSlotManager.setPrevSlot(StatusType.NONE, selectedSlot, wait);
    }

    public static void setAttackInterval() {
        should_attack_interval = true;
        waiting_tick_counter = 10;
    }

    static {
        LOGGER = LogManager.getLogger((String)MOD_ID);
        flagPreAxe = false;
        shouldShieldBreak = false;
        jumpCooldown = 0;
        shouldPressJump = false;
        targetMob = null;
        useEntityEventRefillCounter = 0;
        requireChargeJump = false;
        counterAfterChargeJump = 0;
        worldCheckCounter = 0;
        tempExtremeFlag = false;
    }

    public record useEntityEventRefillStatus(class_1268 hand, int beforeCount) {
    }
}


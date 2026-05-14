/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.Debug;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.PrevSlotManager;
import com.papack.maceattackassistance.client.RefillManager;
import com.papack.maceattackassistance.client.TickScheduler;
import com.papack.maceattackassistance.client.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_746;

@Environment(value=EnvType.CLIENT)
public class DebugScreen {
    public static double lastY = 0.0;

    public static void debugOverlay(class_332 drawContext) {
        class_310 client = class_310.method_1551();
        class_437 screen = client.field_1755;
        class_746 player = client.field_1724;
        class_327 textRenderer = client.field_1772;
        if (player != null && textRenderer != null && screen == null) {
            double vx = player.method_18798().method_10216();
            double vz = player.method_18798().method_10215();
            double v = Math.sqrt(vx * vx + vz * vz);
            double current_y = player.method_23318();
            if (current_y < Debug.previous_y) {
                Debug.result = Debug.previous_y - lastY;
            } else {
                Debug.previous_y = current_y;
                if (Debug.counter > 0) {
                    --Debug.counter;
                }
            }
            int y = 0;
            drawContext.method_51433(textRenderer, "pvp: " + !JobManager.jumpOption(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "onGround: " + player.method_24828(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "pitch: " + player.method_36455(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "yaw: " + player.method_36454(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "v-Y: " + player.method_18798().method_10214(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "v-X,Z: " + v, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "velocity length: " + player.method_18798().method_1033(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Y Diff: " + (player.method_23318() - lastY), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Result: " + Debug.result, 0, ++y * 10, -1, false);
            String jmp = "#".repeat(MaceAttackAssistanceClient.jumpCooldown);
            drawContext.method_51433(textRenderer, "Jump Spam: " + jmp, 0, ++y * 10, -1, false);
            ++y;
            drawContext.method_51433(textRenderer, "Aim Assist: " + Config.AIM_ASSIST, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Attack Assist: " + Config.ATTACK_ASSISTANCE, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Breach Swap: " + Config.BREACH_SWAP, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Double Tap: " + Config.DOUBLE_TAP, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Input Method: " + Config.EXTREME, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Jump Assist: " + Config.JUMP_ASSIST, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Jump Spam: " + Config.JUMP_SPAM, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Prioritize Firework Rocket: " + Config.PRIORITIZE_ROCKET, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Prioritize Wind Charge: " + Config.PRIORITIZE_WIND_CHARGE, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Return PrevSlot: " + Config.RETURN_TO_PREV_SLOT, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Return PrevSlot (B): " + Config.RETURN_TO_PREV_SLOT_BREACH, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Rocket Blitz: " + Config.ROCKET_BLITZ, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Snapback: " + Config.SNAPBACK, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Stun Slam: " + Config.STUN_SLAMMING, 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "Wall Climbing: " + Config.WALL_CLIMBING, 0, ++y * 10, -1, false);
            ++y;
            drawContext.method_51433(textRenderer, "running: " + !JobManager.checkOrderIsEmpty(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "hasDelayTask: " + TickScheduler.hasPendingOrReadyDelayedTasks(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "hasConditionTask: " + TickScheduler.hasPendingOrReadyConditionTasks(), 0, ++y * 10, -1, false);
            ++y;
            drawContext.method_51433(textRenderer, "hasPrevSlotTask: " + !PrevSlotManager.isEmpty(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "hasRefillTask: " + !RefillManager.isEmpty(), 0, ++y * 10, -1, false);
            drawContext.method_51433(textRenderer, "fallDistance: " + player.field_6017, 0, ++y * 10, -1, false);
        }
    }
}


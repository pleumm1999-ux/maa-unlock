/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1937
 *  net.minecraft.class_2189
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_243
 *  net.minecraft.class_2680
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_1937;
import net.minecraft.class_2189;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_746;

public class PredictedLandingPosition {
    public static void tick(class_4587 matrixStack, class_4184 camera) {
        class_746 player = class_310.method_1551().field_1724;
        if (player == null) {
            return;
        }
        if (player.method_24828()) {
            return;
        }
        if (player.method_18798().method_10214() > 0.0) {
            return;
        }
        class_243 landing = PredictedLandingPosition.simulateLanding(player, player.method_73189(), player.method_18798(), Config.AUTO_ELYTRA_TICK_AHEAD);
        PredictedLandingPosition.drawLandingMarker(matrixStack, camera, landing);
    }

    private static class_243 simulateLanding(class_746 player, class_243 startPos, class_243 velocity, int maxTicks) {
        class_1937 world = player.method_73183();
        class_243 pos = startPos;
        class_243 vel = velocity;
        double gravity = 0.08;
        double drag = 0.91;
        for (int i = 0; i < maxTicks; ++i) {
            vel = vel.method_1031(0.0, -gravity, 0.0);
            class_2680 state = world.method_8320(class_2338.method_49638((class_2374)(pos = pos.method_1019(vel = vel.method_18805(drag, drag, drag)))));
            if (!(state.method_26204() instanceof class_2189)) break;
        }
        return pos;
    }

    private static void drawLandingMarker(class_4587 matrices, class_4184 camera, class_243 landing) {
    }
}


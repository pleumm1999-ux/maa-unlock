/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1304
 *  net.minecraft.class_1802
 *  net.minecraft.class_1922
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2680
 *  net.minecraft.class_310
 *  net.minecraft.class_746
 *  org.jetbrains.annotations.NotNull
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_1304;
import net.minecraft.class_1802;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_746;
import org.jetbrains.annotations.NotNull;

public class WallClimbing {
    public static ClimbingStatus canEasyWallClimbing(class_310 client, class_746 clientPlayer) {
        if (!Config.WALL_CLIMBING || clientPlayer.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833) || !client.field_1690.field_1894.method_1434() || !client.field_1690.field_1867.method_1434() || client.field_1690.field_1832.method_1434() || clientPlayer.method_6101() || clientPlayer.method_24828()) {
            return new ClimbingStatus(false, -1);
        }
        return WallClimbing.getClimbingStatus(clientPlayer, 0);
    }

    @NotNull
    public static ClimbingStatus getClimbingStatus(class_746 clientPlayer, int offsetY) {
        boolean[] existBlock = new boolean[3];
        float yaw = clientPlayer.method_36454();
        class_1937 world = clientPlayer.method_73183();
        for (int i = 0; i < 3; ++i) {
            double rad = Math.toRadians(yaw + (float)(-45 + i * 45));
            double offsetX = -Math.sin(rad);
            double offsetZ = Math.cos(rad);
            class_2338 blockPos = new class_2338((int)Math.floor(clientPlayer.method_23317() + offsetX), (int)Math.floor(clientPlayer.method_23318() + (double)offsetY), (int)Math.floor(clientPlayer.method_23321() + offsetZ));
            class_2680 blockState = world.method_8320(blockPos);
            existBlock[i] = !blockState.method_26215() && (blockState.method_26212((class_1922)world, blockPos) || blockState.method_26206((class_1922)world, blockPos, class_2350.field_11036) || !blockState.method_26220((class_1922)world, blockPos).method_1110());
        }
        if (existBlock[1]) {
            return new ClimbingStatus(true, 1);
        }
        if (existBlock[0]) {
            return new ClimbingStatus(true, 0);
        }
        if (existBlock[2]) {
            return new ClimbingStatus(true, 2);
        }
        return new ClimbingStatus(false, -1);
    }

    public static ClimbingStatus resetClimbingStatus() {
        return new ClimbingStatus(false, -1);
    }

    public record ClimbingStatus(boolean canClimbing, int offset) {
    }
}


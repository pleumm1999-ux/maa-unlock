/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_2394
 *  net.minecraft.class_2398
 *  net.minecraft.class_2400
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_5498
 *  net.minecraft.class_702
 *  net.minecraft.class_703
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.config.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_2400;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_5498;
import net.minecraft.class_702;
import net.minecraft.class_703;
import net.minecraft.class_746;

@Environment(value=EnvType.CLIENT)
public class MaceParticle {
    private static final List<class_2400> particleList = new ArrayList<class_2400>(Arrays.asList(class_2398.field_50247, class_2398.field_47493));

    public static void maceParticleHandler(class_310 client, class_746 player, boolean overThreshold) {
        class_2400 particleType = Config.MACE_PARTICLE == Config.WeaponParticle.Transition ? (Config.PARTICLE_ORDER == Config.TransitionOrder.Blue_Red ? (overThreshold ? particleList.get(1) : particleList.get(0)) : (overThreshold ? particleList.get(0) : particleList.get(1))) : particleList.get(Config.MACE_PARTICLE == Config.WeaponParticle.Blue ? 0 : 1);
        MaceParticle.weaponEmitsParticles(client, player, 0.2, 3, particleType);
    }

    public static void weaponEmitsParticles(class_310 client, class_746 player, double radius, int count, class_2400 particleType) {
        class_702 particleManager = client.field_1713;
        class_243 weaponPosition = MaceParticle.getWeaponPosition(client, player);
        for (int i = 0; i < count; ++i) {
            double x = weaponPosition.field_1352 + (ThreadLocalRandom.current().nextDouble() * 2.0 - 1.0) * radius;
            double y = weaponPosition.field_1351 + (ThreadLocalRandom.current().nextDouble() * 2.0 - 1.0) * radius;
            double z = weaponPosition.field_1350 + (ThreadLocalRandom.current().nextDouble() * 2.0 - 1.0) * radius;
            class_243 startPosition = new class_243(x, y, z);
            class_243 direction = new class_243(weaponPosition.field_1352, weaponPosition.field_1351, weaponPosition.field_1350).method_1020(startPosition).method_1029();
            double particleSpeed = 0.1;
            class_703 particle = particleManager.method_3056((class_2394)particleType, startPosition.field_1352, startPosition.field_1351, startPosition.field_1350, direction.field_1352 * particleSpeed, direction.field_1351 * particleSpeed, direction.field_1350 * particleSpeed);
            if (particle == null) continue;
            client.execute(() -> particleManager.method_3058(particle));
        }
    }

    private static class_243 getWeaponPosition(class_310 client, class_746 player) {
        double distance;
        double yaw;
        double pitch;
        class_243 playerPos;
        if (client.field_1690.method_31044().equals((Object)class_5498.field_26664)) {
            playerPos = player.method_33571();
            pitch = player.method_36455() * -1.0f;
            yaw = player.method_36454() + 90.0f + 30.0f;
            distance = 0.8;
        } else {
            playerPos = player.method_5829().method_1005();
            pitch = 0.0;
            yaw = player.method_73188() + 90.0f + 30.0f;
            distance = 0.6;
        }
        return playerPos.method_1019(MaceParticle.getOffset(pitch, yaw, distance));
    }

    private static class_243 getOffset(double pitch, double yaw, double distance) {
        pitch = Math.toRadians(pitch);
        yaw = Math.toRadians(yaw);
        double offsetX = Math.cos(pitch) * Math.cos(yaw);
        double offsetY = Math.sin(pitch);
        double offsetZ = Math.cos(pitch) * Math.sin(yaw);
        return new class_243(offsetX * distance, offsetY * distance, offsetZ * distance);
    }
}


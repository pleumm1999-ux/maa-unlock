/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ModInitializer
 *  net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.class_3222
 *  net.minecraft.class_8710
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.papack.maceattackassistance;

import com.papack.maceattackassistance.network.MaaPayload;
import java.util.UUID;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaceAttackAssistanceFabric
implements ModInitializer {
    public static final String MOD_ID = "maceattackassistance";
    public static Logger LOGGER = LogManager.getLogger((String)"maceattackassistance");

    public void onInitialize() {
        PayloadTypeRegistry.playS2C().register(MaaPayload.ID, MaaPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(MaaPayload.ID, MaaPayload.CODEC);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            LOGGER.info("send packet");
            class_3222 patt0$temp = handler.field_14140;
            if (patt0$temp instanceof class_3222) {
                class_3222 serverPlayer = patt0$temp;
                UUID uuid = serverPlayer.method_5667();
                ServerPlayNetworking.send((class_3222)serverPlayer, (class_8710)new MaaPayload(uuid));
            }
        });
    }
}


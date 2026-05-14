/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_5819
 *  net.minecraft.class_634
 *  net.minecraft.class_7439
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.MaceAttackAssistanceClient;
import com.papack.maceattackassistance.client.TickScheduler;
import com.papack.maceattackassistance.client.ZoomState;
import net.minecraft.class_310;
import net.minecraft.class_5819;
import net.minecraft.class_634;
import net.minecraft.class_7439;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_634.class})
public abstract class MixinClientPlayNetworkHandler {
    @Inject(method={"method_43596"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGameMessage(class_7439 packet, CallbackInfo ci) {
        String content = packet.comp_763().getString();
        String REGULATION = "405";
        class_310 client = class_310.method_1551();
        class_746 player = client.field_1724;
        if (player != null) {
            String uuid = player.method_5667().toString();
            if (content.contains("[MAA] Handshake init...")) {
                try {
                    int delay = class_5819.method_43047().method_39332(0, 40);
                    TickScheduler.setDelayTask(delay, () -> {
                        player.field_3944.method_45729("[MAA_CLIENT]:" + uuid + ":" + REGULATION);
                        ZoomState.MAAClientState.receivedFirstMessage = true;
                    });
                }
                catch (Exception e) {
                    MaceAttackAssistanceClient.LOGGER.warn("Invalid message");
                }
                ci.cancel();
            } else if (content.contains("[MAA] Allowed Behavior Level:")) {
                String[] data = content.split(":");
                if (data.length == 4) {
                    String id = data[1];
                    String regulation = data[2];
                    String level = data[3];
                    if (id.equals(uuid) && regulation.equals(REGULATION)) {
                        try {
                            if (ZoomState.MAAClientState.receivedFirstMessage) {
                                int allowedLevel = Integer.parseInt(level);
                                ZoomState.MAAClientState.allowedLevels = Math.max(0, Math.min(3, allowedLevel));
                                ZoomState.MAAClientState.antiCheat = ZoomState.MAAClientState.allowedLevels == 0;
                                ZoomState.MAAClientState.receivedFirstMessage = false;
                            }
                        }
                        catch (Exception e) {
                            MaceAttackAssistanceClient.LOGGER.warn("This message is not allowed.");
                        }
                    }
                }
                ci.cancel();
            }
        }
    }
}


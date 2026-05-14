/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_742
 *  net.minecraft.class_746
 *  net.minecraft.class_9239
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import net.minecraft.class_310;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_9239;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_742.class})
public class MixinAbstractClientPlayerEntity {
    @Inject(method={"method_3118"}, at={@At(value="HEAD")}, cancellable=true)
    private void shouldFOV(CallbackInfoReturnable<Float> cir) {
        if (MixinAbstractClientPlayerEntity.verifyPlayerCondition()) {
            cir.setReturnValue((Object)Float.valueOf(1.0f));
        }
    }

    @Unique
    private static boolean verifyPlayerCondition() {
        class_310 client = class_310.method_1551();
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer == null) {
            return false;
        }
        if (clientPlayer.method_6048() > 0) {
            return false;
        }
        if (!Config.FOV_SUPPRESSION || !clientPlayer.method_5624() || clientPlayer.method_24828() && !Utils.isOnSlimeBlock(clientPlayer)) {
            return false;
        }
        return Utils.getHandHoldingWindCharge(client, clientPlayer) != null && Config.AUTO_WIND_CHARGE_SELECT && Utils.isSuccessFoundItItemInHotbar(class_9239.class, true);
    }
}


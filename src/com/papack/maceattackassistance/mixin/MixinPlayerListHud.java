/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10799
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_355
 *  net.minecraft.class_640
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.papack.maceattackassistance.mixin;

import com.papack.maceattackassistance.client.FriendManager;
import com.papack.maceattackassistance.client.config.Config;
import java.util.UUID;
import net.minecraft.class_10799;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_355;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_355.class})
public abstract class MixinPlayerListHud {
    @Unique
    private static final class_2960 ICONS_TEXTURE = class_2960.method_60655((String)"maceattackassistance", (String)"hud/friend");

    @Inject(method={"method_1923"}, at={@At(value="TAIL")})
    private void addHeartIcon(class_332 context, int width, int x, int y, class_640 entry, CallbackInfo ci) {
        if (Config.FRIEND_MARK) {
            UUID uuid = entry.method_2966().id();
            if (!FriendManager.isFriend(uuid)) {
                return;
            }
            context.method_51448().pushMatrix();
            context.method_52706(class_10799.field_56883, ICONS_TEXTURE, x + width, y, 9, 9);
            context.method_51448().popMatrix();
        }
    }
}


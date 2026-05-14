/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_4844
 *  net.minecraft.class_8710
 *  net.minecraft.class_8710$class_9154
 *  net.minecraft.class_9129
 *  net.minecraft.class_9139
 */
package com.papack.maceattackassistance.network;

import com.papack.maceattackassistance.network.MaaNetWorkConstants;
import java.util.UUID;
import net.minecraft.class_4844;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;

public record MaaPayload(UUID uuid) implements class_8710
{
    public static final class_8710.class_9154<MaaPayload> ID = new class_8710.class_9154(MaaNetWorkConstants.MAA_PACKET_ID);
    public static final class_9139<class_9129, MaaPayload> CODEC = class_9139.method_56434((class_9139)class_4844.field_48453, MaaPayload::uuid, MaaPayload::new);

    public class_8710.class_9154<? extends class_8710> method_56479() {
        return ID;
    }
}


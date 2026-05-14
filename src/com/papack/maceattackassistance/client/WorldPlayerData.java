/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_268
 *  net.minecraft.class_8685
 */
package com.papack.maceattackassistance.client;

import java.util.UUID;
import net.minecraft.class_268;
import net.minecraft.class_8685;

public record WorldPlayerData(String name, UUID uuid, class_8685 skin, class_268 team, boolean friend) {
}


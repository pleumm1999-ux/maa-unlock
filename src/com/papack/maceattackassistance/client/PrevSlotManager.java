/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.Utils;
import com.papack.maceattackassistance.client.config.Config;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_310;
import net.minecraft.class_746;

public class PrevSlotManager {
    private static final List<PrevSlotData> prevSlotDataList = new ArrayList<PrevSlotData>();
    private static int wait = 0;

    public static void tick(class_310 client) {
        PrevSlotData prevSlotData;
        if (PrevSlotManager.isEmpty()) {
            return;
        }
        if (PrevSlotManager.condition(client) && (prevSlotData = PrevSlotManager.getLast()) != null) {
            int slot = prevSlotData.slot;
            if (Utils.isHotBar(slot)) {
                PrevSlotManager.setSelectedSlot(client, slot);
            }
            PrevSlotManager.removeLast();
        }
        if (wait > 0) {
            --wait;
        }
    }

    public static boolean isEmpty() {
        return prevSlotDataList.isEmpty();
    }

    public static void setPrevSlot(StatusType type, int slot, int wait) {
        PrevSlotManager.wait = wait;
        if (type != null && Utils.isHotBar(slot) && PrevSlotManager.isEmpty()) {
            if (type == StatusType.BREACH && !Config.RETURN_TO_PREV_SLOT_BREACH) {
                return;
            }
            if (type != StatusType.BREACH && !Config.RETURN_TO_PREV_SLOT) {
                return;
            }
            if (Utils.isHotBar(Config.RETURN_TO_PREV_SLOT_MODE)) {
                slot = Config.RETURN_TO_PREV_SLOT_MODE;
            }
            prevSlotDataList.add(new PrevSlotData(type, slot));
        }
    }

    public static int getFirstOrderSlot() {
        return PrevSlotManager.isEmpty() ? -1 : prevSlotDataList.getFirst().slot();
    }

    public static int getLastOrderSlot() {
        return PrevSlotManager.isEmpty() ? -1 : prevSlotDataList.getLast().slot();
    }

    private static PrevSlotData getLast() {
        return PrevSlotManager.isEmpty() ? null : prevSlotDataList.getLast();
    }

    private static void removeLast() {
        if (!PrevSlotManager.isEmpty()) {
            prevSlotDataList.removeLast();
        }
    }

    private static boolean condition(class_310 client) {
        if (wait > 0) {
            return false;
        }
        if (!JobManager.checkOrderIsEmpty()) {
            return false;
        }
        return JobManager.keyReleased(client);
    }

    private static void setSelectedSlot(class_310 client, int slot) {
        class_746 clientPlayer = client.field_1724;
        if (clientPlayer != null) {
            if (clientPlayer.method_31548().method_67532() == slot) {
                return;
            }
            clientPlayer.method_31548().method_61496(slot);
        }
    }

    public record PrevSlotData(StatusType type, int slot) {
    }
}


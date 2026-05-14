/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_636
 *  net.minecraft.class_746
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.AutoRefill;
import com.papack.maceattackassistance.client.FriendKeyHandler;
import com.papack.maceattackassistance.client.JobManager;
import com.papack.maceattackassistance.client.PrevSlotManager;
import com.papack.maceattackassistance.client.SpearAttacks;
import com.papack.maceattackassistance.client.StatusType;
import com.papack.maceattackassistance.client.SwitchAssist;
import com.papack.maceattackassistance.client.ToggleElytra;
import com.papack.maceattackassistance.client.ZoomState;
import com.papack.maceattackassistance.client.config.Config;
import com.papack.maceattackassistance.client.config.ModConfigScreen;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_636;
import net.minecraft.class_746;

public class KeyInput {
    public static void keyInputInGame(class_310 client, class_746 clientPlayer, class_636 interactionManager) {
        int slot;
        boolean canZoom;
        while (Config.CONFIG_SCREEN_KEY.method_1436()) {
            if (client.field_1690.field_1832.method_1434()) {
                Config.DEBUG_SCREEN = !Config.DEBUG_SCREEN;
                continue;
            }
            if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
                client.method_1507(ModConfigScreen.getConfigScreen(null));
                continue;
            }
            clientPlayer.method_7353((class_2561)class_2561.method_43470((String)"MAA requires \"Cloth Config API\".\nIt is not installed."), false);
        }
        while (Config.BULK_REFILL_KEY.method_1436()) {
            if (!JobManager.checkOrderIsEmpty() || !JobManager.keyReleased(client)) continue;
            for (int i = 0; i < 10; ++i) {
                if (!AutoRefill.checkStockQuantity(clientPlayer, i)) continue;
                AutoRefill.autoRefill(interactionManager, clientPlayer, 36 + i);
            }
        }
        boolean bl = canZoom = !clientPlayer.method_24828() && clientPlayer.method_6128();
        while (Config.ZOOM_CAMERA_KEY.method_1436()) {
            ZoomState.zoomCamera(client, clientPlayer, canZoom);
        }
        if (Config.ZOOM_CAMERA && !canZoom) {
            ZoomState.zoomCamera(client, clientPlayer, false);
        }
        if (ZoomState.gazeCounter > 0) {
            --ZoomState.gazeCounter;
        }
        while (Config.ENDER_PEARL_KEY.method_1436()) {
            if (JobManager.checkStatus(StatusType.ENDER_PEARL)) continue;
            slot = clientPlayer.method_31548().method_67532();
            JobManager.setOrder(StatusType.ENDER_PEARL, slot);
        }
        while (Config.TOGGLE_ELYTRA_KEY.method_1436()) {
            if (Config.ELYTRA_MANUAL_MODE) {
                slot = PrevSlotManager.isEmpty() ? clientPlayer.method_31548().method_67532() : PrevSlotManager.getLastOrderSlot();
                JobManager.setOrder(StatusType.ELYTRA_MANUAL_SWITCH_MODE, slot);
                continue;
            }
            ToggleElytra.toggleElytra();
        }
        while (Config.SPEAR_LUNGE.method_1436()) {
            SpearAttacks.manualLungeAttack(client, clientPlayer);
        }
        while (Config.ATTACK_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.ATTACK);
        }
        while (Config.AIM_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.AIM);
        }
        while (Config.JUMP_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.JUMP);
        }
        while (Config.SEARCH_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.TARGET_SEARCH);
        }
        while (Config.RETURN_TO_PREV_SLOT_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.RETURN_TO_PREV_SLOT);
        }
        while (Config.BREACH_SWAP_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.BREACH_SWAP);
        }
        while (Config.DOUBLE_TAP_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.DOUBLE_TAP);
        }
        while (Config.TOGGLE_JUMP_MODE_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.TOGGLE_JUMP_MODE);
        }
        while (Config.REWEAR_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.REWEAR);
        }
        while (Config.ELYTRA_BOOST_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.ELYTRA_BOOST);
        }
        while (Config.RETURN_TO_PREV_SLOT_BREACH_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.RETURN_TO_BREACH);
        }
        while (Config.AUTO_WIND_CHARGE_SELECT_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.AUTO_WIND_CHARGE);
        }
        while (Config.SHIELD_DRAINING_SETTING_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.SHIELD_DRAINING);
        }
        while (Config.TOGGLE_BREACH_SWAP_MODE_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.TOGGLE_BREACH_SWAP_MODE);
        }
        while (Config.TOGGLE_SWORD_OR_AXE_KEY.method_1436()) {
            SwitchAssist.changeSettings(SwitchAssist.SwitchKey.TOGGLE_SWORD_OR_AXE);
        }
        while (Config.FRIEND_PROTECTION_SETTING_KEY.method_1436()) {
            FriendKeyHandler.friendKeyHandler(client);
        }
    }
}


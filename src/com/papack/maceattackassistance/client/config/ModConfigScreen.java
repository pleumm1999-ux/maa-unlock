/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.shedaniel.clothconfig2.api.AbstractConfigListEntry
 *  me.shedaniel.clothconfig2.api.ConfigBuilder
 *  me.shedaniel.clothconfig2.api.ConfigCategory
 *  me.shedaniel.clothconfig2.api.ConfigEntryBuilder
 *  me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder
 *  me.shedaniel.clothconfig2.impl.builders.IntFieldBuilder
 *  me.shedaniel.clothconfig2.impl.builders.IntSliderBuilder
 *  me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_437
 */
package com.papack.maceattackassistance.client.config;

import com.papack.maceattackassistance.client.ColorData;
import com.papack.maceattackassistance.client.config.Config;
import com.papack.maceattackassistance.client.config.ConfigOperation;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntSliderBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_437;

@Environment(value=EnvType.CLIENT)
public class ModConfigScreen {
    public static class_437 getConfigScreen(class_437 parent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.title"));
        builder.setGlobalized(Config.DEFAULT_EXPANDED_GLOBAL);
        builder.setGlobalizedExpanded(true);
        ConfigCategory attack = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Attack").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory aim = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Aim").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory jump = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Jump").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory elytra = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Elytra").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory item = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Item").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory slot = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Slot").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory recon = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Recon").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory friend = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Friend").method_27694(s -> s.method_10977(class_124.field_1060)));
        ConfigCategory effects = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Effects,Marker").method_27694(s -> s.method_10977(class_124.field_1075)));
        ConfigCategory spiral = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"SpiralMaker").method_27694(s -> s.method_10977(class_124.field_1075)));
        ConfigCategory color = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Spiral,Beam Color").method_27694(s -> s.method_10977(class_124.field_1075)));
        ConfigCategory fov = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Search Range,Angle").method_27694(s -> s.method_10977(class_124.field_1075)));
        ConfigCategory others = builder.getOrCreateCategory((class_2561)class_2561.method_43470((String)"Others").method_27694(s -> s.method_10977(class_124.field_1080)));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        SubCategoryBuilder attackAssist = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Attack Assist")).setExpanded(Config.DEFAULT_EXPANDED);
        attackAssist.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.attack_assistance"), Config.ATTACK_ASSISTANCE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ATTACK_ASSISTANCE)))).setSaveConsumer(newValue -> {
            Config.ATTACK_ASSISTANCE = newValue;
        }).build());
        attackAssist.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.height_threshold"), Config.HEIGHT_THRESHOLD, 2, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_HEIGHT_THRESHOLD)))).setSaveConsumer(newValue -> {
            Config.HEIGHT_THRESHOLD = newValue;
        }).build());
        attackAssist.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.weapon_swing"), Config.WEAPON_SWING).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_WEAPON_SWING)))).setSaveConsumer(newValue -> {
            Config.WEAPON_SWING = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.weapon_swing")}).build());
        attackAssist.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.swing_toggle"), Config.SwingToggle.class, (Enum)Config.SWING_TOGGLE).setDefaultValue((Enum)Config.SwingToggle.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SWING_TOGGLE))).setSaveConsumer(newValue -> {
            Config.SWING_TOGGLE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.swing_toggle")}).build());
        SubCategoryBuilder doubleTap = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Double Tap")).setExpanded(Config.DEFAULT_EXPANDED);
        doubleTap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.double_tap"), Config.DOUBLE_TAP).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_DOUBLE_TAP)))).setSaveConsumer(newValue -> {
            Config.DOUBLE_TAP = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.double_tap")}).build());
        doubleTap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.double_tap_rewear"), Config.DOUBLE_TAP_REWEAR).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_DOUBLE_TAP_REWEAR)))).setSaveConsumer(newValue -> {
            Config.DOUBLE_TAP_REWEAR = newValue;
        }).build());
        SubCategoryBuilder hotSwap = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Hot Swap")).setExpanded(Config.DEFAULT_EXPANDED);
        hotSwap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.hot_swap"), Config.HOT_SWAP).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_HOT_SWAP)))).setSaveConsumer(newValue -> {
            Config.HOT_SWAP = newValue;
        }).build());
        hotSwap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.spear_assist"), Config.SPEAR_ASSIST).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SPEAR_ASSIST)))).setSaveConsumer(newValue -> {
            Config.SPEAR_ASSIST = newValue;
        }).build());
        hotSwap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.spear_swap"), Config.SPEAR_SWAP).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SPEAR_SWAP)))).setSaveConsumer(newValue -> {
            Config.SPEAR_SWAP = newValue;
        }).build());
        SubCategoryBuilder stunSlam = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Stun Slam")).setExpanded(Config.DEFAULT_EXPANDED);
        stunSlam.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.stun_slamming"), Config.STUN_SLAMMING).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_STUN_SLAMMING)))).setSaveConsumer(newValue -> {
            Config.STUN_SLAMMING = newValue;
        }).build());
        SubCategoryBuilder breachSwap = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Breach Swap, Sword Swap")).setExpanded(Config.DEFAULT_EXPANDED);
        breachSwap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.breach_swap"), Config.BREACH_SWAP).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_BREACH_SWAP)))).setSaveConsumer(newValue -> {
            Config.BREACH_SWAP = newValue;
        }).build());
        breachSwap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.breach_limited"), Config.BREACH_LIMITED).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_BREACH_LIMITED)))).setSaveConsumer(newValue -> {
            Config.BREACH_LIMITED = newValue;
        }).build());
        breachSwap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.breach_on_ground"), Config.BREACH_ON_GROUND).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_BREACH_ON_GROUND)))).setSaveConsumer(newValue -> {
            Config.BREACH_ON_GROUND = newValue;
        }).build());
        breachSwap.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43470((String)"Armored Target Behavior"), Config.Behavior.class, (Enum)Config.SWORD_SWAP_OR_BREACH_SWAP).setDefaultValue((Enum)Config.Behavior.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SWORD_SWAP_OR_BREACH_SWAP))).setSaveConsumer(newValue -> {
            Config.SWORD_SWAP_OR_BREACH_SWAP = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43470((String)"It does not affect snapback.\nBreach Swap: any to Breach Mace\nSword Swap: any to Sword(or Axe)")}).build());
        breachSwap.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43470((String)"UnArmored Target Behavior"), Config.Behavior.class, (Enum)Config.BEHAVIOR_NOT_WEARING_ARMOR).setDefaultValue((Enum)Config.Behavior.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_BEHAVIOR_NOT_WEARING_ARMOR))).setSaveConsumer(newValue -> {
            Config.BEHAVIOR_NOT_WEARING_ARMOR = newValue;
        }).build());
        breachSwap.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Sword Swap Mode Option"), Config.SWORD_OR_AXE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SWORD_OR_AXE)))).setSaveConsumer(newValue -> {
            Config.SWORD_OR_AXE = newValue;
        }).setYesNoTextSupplier(value -> value != false ? class_2561.method_43470((String)"Sword") : class_2561.method_43470((String)"Axe")).build());
        SubCategoryBuilder snapback = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Snapback")).setExpanded(Config.DEFAULT_EXPANDED);
        snapback.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.snapback"), Config.SNAPBACK).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SNAPBACK)))).setSaveConsumer(newValue -> {
            Config.SNAPBACK = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.snapback")}).build());
        snapback.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.snapback_threshold"), Config.SNAPBACK_THRESHOLD, 4, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SNAPBACK_THRESHOLD)))).setSaveConsumer(newValue -> {
            Config.SNAPBACK_THRESHOLD = newValue;
        }).build());
        snapback.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Snapback - Tolerance"), Config.SNAPBACK_TOLERANCE, 0, 500).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SNAPBACK_TOLERANCE)))).setSaveConsumer(newValue -> {
            Config.SNAPBACK_TOLERANCE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.snapback_tolerance")}).build());
        SubCategoryBuilder shieldDraining = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Shield Draining")).setExpanded(Config.DEFAULT_EXPANDED);
        shieldDraining.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Shield Draining"), Config.SHIELD_DRAINING).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SHIELD_DRAINING)))).setSaveConsumer(newValue -> {
            Config.SHIELD_DRAINING = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.shield_draining")}).build());
        SubCategoryBuilder weaponSlotSetting = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Weapon Slot Setting")).setExpanded(Config.DEFAULT_EXPANDED);
        weaponSlotSetting.add((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.axe_slot"), Config.AXE_SLOT + 1, 0, 9).setDefaultValue((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AXE_SLOT) + 1).setSaveConsumer(newValue -> {
            Config.AXE_SLOT = newValue - 1;
        }).build());
        weaponSlotSetting.add((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.mace_slot_primary"), Config.MACE_PRIMARY + 1, 0, 9).setDefaultValue((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_MACE_PRIMARY) + 1).setSaveConsumer(newValue -> {
            Config.MACE_PRIMARY = newValue - 1;
        }).build());
        weaponSlotSetting.add((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.mace_slot_breach"), Config.MACE_BREACH + 1, 0, 9).setDefaultValue((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_MACE_BREACH) + 1).setSaveConsumer(newValue -> {
            Config.MACE_BREACH = newValue - 1;
        }).build());
        SubCategoryBuilder returnToPrevSlot = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Return To PrevSlot")).setExpanded(Config.DEFAULT_EXPANDED);
        returnToPrevSlot.add((AbstractConfigListEntry)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Return Mode [0:Auto], [1-9:Specify]"), Config.RETURN_TO_PREV_SLOT_MODE + 1, 0, 9).setDefaultValue((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RETURN_TO_PREV_SLOT_MODE) + 1).setSaveConsumer(newValue -> {
            Config.RETURN_TO_PREV_SLOT_MODE = newValue - 1;
        }).build());
        returnToPrevSlot.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.return_to_prev_slot"), Config.RETURN_TO_PREV_SLOT).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RETURN_TO_PREV_SLOT)))).setSaveConsumer(newValue -> {
            Config.RETURN_TO_PREV_SLOT = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.return_to_prev_slot")}).build());
        returnToPrevSlot.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.return_to_prev_slot_breach"), Config.RETURN_TO_PREV_SLOT_BREACH).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RETURN_TO_PREV_SLOT_BREACH)))).setSaveConsumer(newValue -> {
            Config.RETURN_TO_PREV_SLOT_BREACH = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.return_to_prev_slot")}).build());
        SubCategoryBuilder aimAssist = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Aim Assist")).setExpanded(Config.DEFAULT_EXPANDED);
        aimAssist.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.aim_assist"), Config.AIM_ASSIST).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_ASSIST)))).setSaveConsumer(newValue -> {
            Config.AIM_ASSIST = newValue;
        }).build());
        aimAssist.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.legacy_aim_mode"), Config.LEGACY_AIM_MODE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_LEGACY_AIM_MODE)))).setSaveConsumer(newValue -> {
            Config.LEGACY_AIM_MODE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.legacy_aim_mode")}).build());
        SubCategoryBuilder aimTarget = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Target")).setExpanded(Config.DEFAULT_EXPANDED);
        aimTarget.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_villager"), Config.ALLOWED_VILLAGER).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ALLOWED_VILLAGER)))).setSaveConsumer(newValue -> {
            Config.ALLOWED_VILLAGER = newValue;
        }).build());
        aimTarget.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_iron_golem"), Config.ALLOWED_IRON_GOLEM).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ALLOWED_IRON_GOLEM)))).setSaveConsumer(newValue -> {
            Config.ALLOWED_IRON_GOLEM = newValue;
        }).build());
        aimTarget.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_hostile"), Config.ALLOWED_HOSTILE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ALLOWED_HOSTILE)))).setSaveConsumer(newValue -> {
            Config.ALLOWED_HOSTILE = newValue;
        }).build());
        aimTarget.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_passive"), Config.ALLOWED_PASSIVE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ALLOWED_PASSIVE)))).setSaveConsumer(newValue -> {
            Config.ALLOWED_PASSIVE = newValue;
        }).build());
        aimTarget.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_ambient"), Config.ALLOWED_AMBIENT).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ALLOWED_AMBIENT)))).setSaveConsumer(newValue -> {
            Config.ALLOWED_AMBIENT = newValue;
        }).build());
        aimTarget.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_player"), Config.ALLOWED_PLAYER).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ALLOWED_PLAYER)))).setSaveConsumer(newValue -> {
            Config.ALLOWED_PLAYER = newValue;
        }).build());
        SubCategoryBuilder aimOption = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Aim Option")).setExpanded(Config.DEFAULT_EXPANDED);
        aimOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.forced_adjustment"), Config.AIM_FORCED_ADJUSTMENT).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_FORCED_ADJUSTMENT)))).setSaveConsumer(newValue -> {
            Config.AIM_FORCED_ADJUSTMENT = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.forced_adjustment")}).build());
        aimOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.max_speed_yaw"), Config.MAX_SPEED_YAW, 0, 15).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_MAX_SPEED_YAW)))).setSaveConsumer(newValue -> {
            Config.MAX_SPEED_YAW = newValue;
        }).build());
        aimOption.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.aim_mode"), Config.AimMode.class, (Enum)Config.AIM_MODE).setDefaultValue((Enum)Config.AimMode.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_MODE))).setSaveConsumer(newValue -> {
            Config.AIM_MODE = newValue;
        }).build());
        aimOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.auto_mode_threshold"), Config.AUTO_MODE_THRESHOLD, 10, 30).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AUTO_MODE_THRESHOLD)))).setSaveConsumer(newValue -> {
            Config.AUTO_MODE_THRESHOLD = newValue;
        }).build());
        aimOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Starting Threshold"), Config.AIM_FALL_THRESHOLD, 2, 8).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_FALL_THRESHOLD)))).setSaveConsumer(newValue -> {
            Config.AIM_FALL_THRESHOLD = newValue;
        }).build());
        aimOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"(Experimental) Raycast Search Mode"), Config.AIM_RAYCAST).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_RAYCAST)))).setSaveConsumer(newValue -> {
            Config.AIM_RAYCAST = newValue;
        }).build());
        aimOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"(Experimental) Raycast : Range"), Config.AIM_RAYCAST_RANGE, 5, 30).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_RAYCAST_RANGE)))).setSaveConsumer(newValue -> {
            Config.AIM_RAYCAST_RANGE = newValue;
        }).build());
        aimOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"(Experimental) Raycast : Radius"), Config.AIM_RAYCAST_RADIUS, 1, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_RAYCAST_RADIUS)))).setSaveConsumer(newValue -> {
            Config.AIM_RAYCAST_RADIUS = newValue;
        }).build());
        SubCategoryBuilder jumpAssist = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Jump Assist")).setExpanded(Config.DEFAULT_EXPANDED);
        jumpAssist.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.jump_assist"), Config.JUMP_ASSIST).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_JUMP_ASSIST)))).setSaveConsumer(newValue -> {
            Config.JUMP_ASSIST = newValue;
        }).build());
        SubCategoryBuilder wallClimbing = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"wallClimbing")).setExpanded(Config.DEFAULT_EXPANDED);
        wallClimbing.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.wall_climbing"), Config.WALL_CLIMBING).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_WALL_CLIMBING)))).setSaveConsumer(newValue -> {
            Config.WALL_CLIMBING = newValue;
        }).build());
        SubCategoryBuilder autoWindChargeSelector = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Auto WindCharge Selector")).setExpanded(Config.DEFAULT_EXPANDED);
        autoWindChargeSelector.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Auto WindCharge Selector"), Config.AUTO_WIND_CHARGE_SELECT).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AUTO_WIND_CHARGE_SELECT)))).setSaveConsumer(newValue -> {
            Config.AUTO_WIND_CHARGE_SELECT = newValue;
        }).build());
        autoWindChargeSelector.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"\"Quick double-press\" to throw a Wind Charge"), Config.THROW_WIND_CHARGE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_THROW_WIND_CHARGE)))).setSaveConsumer(newValue -> {
            Config.THROW_WIND_CHARGE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43470((String)"Press the \"throw ender pearl\" key to throw an ender pearl,\n then press it again while it's on cooldown to throw a wind charge.")}).build());
        SubCategoryBuilder toggleElytraOption = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Toggle Elytra Option")).setExpanded(Config.DEFAULT_EXPANDED);
        toggleElytraOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Toggle Elytra Input Method"), Config.ELYTRA_MANUAL_MODE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ELYTRA_MANUAL_MODE)))).setSaveConsumer(newValue -> {
            Config.ELYTRA_MANUAL_MODE = newValue;
        }).setYesNoTextSupplier(value -> value != false ? class_2561.method_43470((String)"Simulated") : class_2561.method_43470((String)"Direct")).build());
        toggleElytraOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Toggle Elytra - Equipment Search Range"), Config.ALSO_SEARCH_INVENTORY).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ALSO_SEARCH_INVENTORY)))).setSaveConsumer(newValue -> {
            Config.ALSO_SEARCH_INVENTORY = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43470((String)"(Only Input Method:Direct) The inventory will also be searched.")}).setYesNoTextSupplier(value -> value != false ? class_2561.method_43470((String)"Hotbar + Inventory") : class_2561.method_43470((String)"Hotbar")).build());
        toggleElytraOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.toggle_elytra"), Config.TOGGLE_SLOT, 0, 9).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_TOGGLE_SLOT)))).setSaveConsumer(newValue -> {
            Config.TOGGLE_SLOT = newValue;
        }).build());
        SubCategoryBuilder rocketBlitz = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Rocket Blitz")).setExpanded(Config.DEFAULT_EXPANDED);
        rocketBlitz.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.rocket_blitz"), Config.ROCKET_BLITZ).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ROCKET_BLITZ)))).setSaveConsumer(newValue -> {
            Config.ROCKET_BLITZ = newValue;
        }).build());
        rocketBlitz.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.rocket_blitz_slot"), Config.ROCKET_BLITZ_SLOT, 0, 9).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ROCKET_BLITZ_SLOT)))).setSaveConsumer(newValue -> {
            Config.ROCKET_BLITZ_SLOT = newValue;
        }).build());
        rocketBlitz.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.rocket_trigger"), Config.RocketTrigger.class, (Enum)Config.ROCKET_TRIGGER).setDefaultValue((Enum)Config.RocketTrigger.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ROCKET_TRIGGER))).setSaveConsumer(newValue -> {
            Config.ROCKET_TRIGGER = newValue;
        }).build());
        SubCategoryBuilder jumpSpam = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Jump Spam")).setExpanded(Config.DEFAULT_EXPANDED);
        jumpSpam.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.jump_spam"), Config.JUMP_SPAM).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_JUMP_SPAM)))).setSaveConsumer(newValue -> {
            Config.JUMP_SPAM = newValue;
        }).build());
        jumpSpam.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.jump_spam_tick"), Config.JUMP_SPAM_TICK, 2, 8).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_JUMP_SPAM_TICK)))).setSaveConsumer(newValue -> {
            Config.JUMP_SPAM_TICK = newValue;
        }).build());
        jumpSpam.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Jump Spam Threshold"), Config.JUMP_SPAM_THRESHOLD, 0, 5).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_JUMP_SPAM_THRESHOLD)))).setSaveConsumer(newValue -> {
            Config.JUMP_SPAM_THRESHOLD = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.jump_spam_threshold")}).build());
        SubCategoryBuilder elytraBoost = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Elytra Boost")).setExpanded(Config.DEFAULT_EXPANDED);
        elytraBoost.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Elytra Boost"), Config.ELYTRA_BOOST).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ELYTRA_BOOST)))).setSaveConsumer(newValue -> {
            Config.ELYTRA_BOOST = newValue;
        }).build());
        elytraBoost.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Elytra Boost - Reflection Angle"), Config.REFLECTION_ANGLE, -60, -10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_REFLECTION_ANGLE)))).setSaveConsumer(newValue -> {
            Config.REFLECTION_ANGLE = newValue;
        }).build());
        SubCategoryBuilder prioritizeUse = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Prioritize Use")).setExpanded(Config.DEFAULT_EXPANDED);
        prioritizeUse.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Jump Assist / Elytra Boost - Prioritize Use Wind Charge"), Config.PRIORITIZE_WIND_CHARGE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_PRIORITIZE_WIND_CHARGE)))).setSaveConsumer(newValue -> {
            Config.PRIORITIZE_WIND_CHARGE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.prioritize")}).build());
        prioritizeUse.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Rocket Blitz - Prioritize Use Fireworks Rocket"), Config.PRIORITIZE_ROCKET).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_PRIORITIZE_ROCKET)))).setSaveConsumer(newValue -> {
            Config.PRIORITIZE_ROCKET = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.prioritize")}).build());
        SubCategoryBuilder jumpMode = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Jump Mode")).setExpanded(Config.DEFAULT_EXPANDED);
        jumpMode.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.in_use_elytra_jump_mode"), Config.JumpMode.class, (Enum)Config.IN_USE_ELYTRA_JUMP_MODE).setDefaultValue((Enum)Config.JumpMode.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_IN_USE_ELYTRA_JUMP_MODE))).setSaveConsumer(newValue -> {
            Config.IN_USE_ELYTRA_JUMP_MODE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.in_use_elytra_jump_mode")}).build());
        jumpMode.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.not_in_use_elytra_jump_mode"), Config.JumpMode.class, (Enum)Config.NOT_IN_USE_ELYTRA_JUMP_MODE).setDefaultValue((Enum)Config.JumpMode.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_NOT_USE_ELYTRA_JUMP_MODE))).setSaveConsumer(newValue -> {
            Config.NOT_IN_USE_ELYTRA_JUMP_MODE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.not_in_use_elytra_jump_mode")}).build());
        SubCategoryBuilder autoRefill = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Auto Refill")).setExpanded(Config.DEFAULT_EXPANDED);
        autoRefill.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.auto_refill"), Config.AUTO_REFILL).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AUTO_REFILL)))).setSaveConsumer(newValue -> {
            Config.AUTO_REFILL = newValue;
        }).build());
        SubCategoryBuilder markerType = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Marker Type")).setExpanded(Config.DEFAULT_EXPANDED);
        markerType.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43470((String)"Target Marker Type"), Config.MarkerType.class, (Enum)Config.MARKER_TYPE).setDefaultValue((Enum)Config.MarkerType.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_MARKER_TYPE))).setSaveConsumer(newValue -> {
            Config.MARKER_TYPE = newValue;
        }).build());
        SubCategoryBuilder targetSearchMode = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Target Search Mode")).setExpanded(Config.DEFAULT_EXPANDED);
        targetSearchMode.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.target_search_mode"), Config.TARGET_SEARCH_MODE).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_TARGET_SEARCH_MODE)))).setSaveConsumer(newValue -> {
            Config.TARGET_SEARCH_MODE = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.target_search_mode")}).build());
        SubCategoryBuilder displayMarkerOnTarget = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Display Marker")).setExpanded(Config.DEFAULT_EXPANDED);
        displayMarkerOnTarget.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.on_target"), Config.TARGET_MARKER).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_TARGET_MARKER)))).setSaveConsumer(newValue -> {
            Config.TARGET_MARKER = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.on_target")}).build());
        SubCategoryBuilder hideMarkerWhenAimOff = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Hide Marker")).setExpanded(Config.DEFAULT_EXPANDED);
        hideMarkerWhenAimOff.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.hide_marker"), Config.HIDE_MARKER).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_HIDE_MARKER)))).setSaveConsumer(newValue -> {
            Config.HIDE_MARKER = newValue;
        }).build());
        SubCategoryBuilder markerOffset = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Marker Offset")).setExpanded(Config.DEFAULT_EXPANDED);
        markerOffset.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.marker_offset"), Config.MARKER_OFFSET, 0, 5).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_MARKER_OFFSET)))).setSaveConsumer(newValue -> {
            Config.MARKER_OFFSET = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.marker_offset")}).build());
        SubCategoryBuilder attackParticle = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Falling Attack Particles")).setExpanded(Config.DEFAULT_EXPANDED);
        attackParticle.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.on_weapon"), Config.WeaponParticle.class, (Enum)Config.MACE_PARTICLE).setDefaultValue((Enum)Config.WeaponParticle.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_MACE_PARTICLE))).setSaveConsumer(newValue -> {
            Config.MACE_PARTICLE = newValue;
        }).build());
        attackParticle.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.transition_threshold"), Config.PARTICLE_TRANSITION_THRESHOLD, 10, 20).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_PARTICLE_TRANSITION_THRESHOLD)))).setSaveConsumer(newValue -> {
            Config.PARTICLE_TRANSITION_THRESHOLD = newValue;
        }).build());
        attackParticle.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.particle_order"), Config.TransitionOrder.class, (Enum)Config.PARTICLE_ORDER).setDefaultValue((Enum)Config.TransitionOrder.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_PARTICLE_ORDER))).setSaveConsumer(newValue -> {
            Config.PARTICLE_ORDER = newValue;
        }).build());
        SubCategoryBuilder reconOption = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Recon Option")).setExpanded(Config.DEFAULT_EXPANDED);
        reconOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Quick Zoom"), Config.RECON_QUICK_ZOOM).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RECON_QUICK_ZOOM)))).setSaveConsumer(newValue -> {
            Config.RECON_QUICK_ZOOM = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.quick_zoom")}).build());
        reconOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Quick Attack"), Config.RECON_QUICK_ATTACK).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RECON_QUICK_ATTACK)))).setSaveConsumer(newValue -> {
            Config.RECON_QUICK_ATTACK = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.quick_attack")}).build());
        reconOption.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43470((String)"Recon View Perspective"), Config.ReconView.class, (Enum)Config.ZOOM_VIEW).setDefaultValue((Enum)Config.ReconView.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ZOOM_VIEW))).setSaveConsumer(newValue -> {
            Config.ZOOM_VIEW = newValue;
        }).build());
        reconOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Zoom In/Out Step"), Config.ZOOM_STEP, 3, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ZOOM_STEP)))).setSaveConsumer(newValue -> {
            Config.ZOOM_STEP = newValue;
        }).build());
        reconOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Display Crosshairs When [THIRD_PERSON_BACK]"), Config.PERSPECTIVE_BACK_CROSSHAIR).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_PERSPECTIVE_BACK_CROSSHAIR)))).setSaveConsumer(newValue -> {
            Config.PERSPECTIVE_BACK_CROSSHAIR = newValue;
        }).build());
        reconOption.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Set the current viewing direction when returning to normal state"), Config.CAMERA_RETURN_BEHAVIOR).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_CAMERA_RETURN_BEHAVIOR)))).setSaveConsumer(newValue -> {
            Config.CAMERA_RETURN_BEHAVIOR = newValue;
        }).build());
        SubCategoryBuilder friendProtection = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Friend Protection")).setExpanded(Config.DEFAULT_EXPANDED);
        friendProtection.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.friend_protection"), Config.FRIEND_PROTECTION).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FRIEND_PROTECTION)))).setSaveConsumer(newValue -> {
            Config.FRIEND_PROTECTION = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.friend_protection")}).build());
        friendProtection.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Displays a mark in the Player List"), Config.FRIEND_MARK).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FRIEND_MARK)))).setSaveConsumer(newValue -> {
            Config.FRIEND_MARK = newValue;
        }).build());
        friendProtection.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Make a list that includes all your friends"), Config.FRIEND_NOT_FOUND).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FRIEND_NOT_FOUND)))).setSaveConsumer(newValue -> {
            Config.FRIEND_NOT_FOUND = newValue;
        }).build());
        SubCategoryBuilder customCrosshair = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Custom Crosshair")).setExpanded(Config.DEFAULT_EXPANDED);
        customCrosshair.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43470((String)"Use Custom Crosshairs"), Config.CrosshairMode.class, (Enum)Config.CROSSHAIR_MODE).setDefaultValue((Enum)Config.CrosshairMode.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_CROSSHAIR_MODE))).setSaveConsumer(newValue -> {
            Config.CROSSHAIR_MODE = newValue;
        }).build());
        SubCategoryBuilder attackOption = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"For Development - Attack")).setExpanded(Config.DEFAULT_EXPANDED);
        attackOption.add((AbstractConfigListEntry)((IntFieldBuilder)entryBuilder.startIntField((class_2561)class_2561.method_43470((String)"Attack Range"), Config.ATTACK_RANGE).setMax(400).setMin(210).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_ATTACK_RANGE)))).setSaveConsumer(newValue -> {
            Config.ATTACK_RANGE = newValue;
        }).build());
        attackOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"High Speed Input Threshold"), Config.FALL_VELOCITY[0], 1, 56).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FALL_VELOCITY[0])))).setSaveConsumer(newValue -> {
            Config.FALL_VELOCITY[0] = newValue;
        }).build());
        attackOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"High : Mace Attack Timing"), Config.STUN_HIGH, 3, 5).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_STUN_HIGH)))).setSaveConsumer(newValue -> {
            Config.STUN_HIGH = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43470((String)"for Stun Slam : Mace Attack")}).build());
        attackOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Low : Mace Attack Timing"), Config.STUN_LOW, 3, 5).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_STUN_LOW)))).setSaveConsumer(newValue -> {
            Config.STUN_LOW = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43470((String)"for Stun Slam : Mace Attack")}).build());
        attackOption.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Aim Offset"), Config.AIM_OFFSET, 0, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AIM_OFFSET)))).setSaveConsumer(newValue -> {
            Config.AIM_OFFSET = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43470((String)"Entity Center Add (height * 0.1 * VALUE")}).build());
        SubCategoryBuilder autoRewearDelay = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Auto Rewear Option")).setExpanded(Config.DEFAULT_EXPANDED);
        autoRewearDelay.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.double_tap_rewear_delay"), Config.DOUBLE_TAP_REWEAR_DELAY, 0, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_DOUBLE_TAP_REWEAR_DELAY)))).setSaveConsumer(newValue -> {
            Config.DOUBLE_TAP_REWEAR_DELAY = newValue;
        }).build());
        SubCategoryBuilder suppression = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"For Development - Suppression")).setExpanded(Config.DEFAULT_EXPANDED);
        suppression.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.fov_suppression"), Config.FOV_SUPPRESSION).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FOV_SUPPRESSION)))).setSaveConsumer(newValue -> {
            Config.FOV_SUPPRESSION = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.fov_suppression")}).build());
        suppression.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.flap_suppression"), Config.FLAP_SUPPRESSION).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FLAP_SUPPRESSION)))).setSaveConsumer(newValue -> {
            Config.FLAP_SUPPRESSION = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.flap_suppression")}).build());
        suppression.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.flap_suppression_threshold"), Config.FLAP_SUPPRESSION_THRESHOLD, 5, 20).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FLAP_SUPPRESSION_THRESHOLD)))).setSaveConsumer(newValue -> {
            Config.FLAP_SUPPRESSION_THRESHOLD = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.flap_suppression")}).build());
        suppression.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.flap_suppression_tick"), Config.FLAP_SUPPRESSION_TICK, 5, 20).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FLAP_SUPPRESSION_TICK)))).setSaveConsumer(newValue -> {
            Config.FLAP_SUPPRESSION_TICK = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.flap_suppression")}).build());
        SubCategoryBuilder displayActionBar = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Notification")).setExpanded(Config.DEFAULT_EXPANDED);
        displayActionBar.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.display_action_bar"), Config.DisplayAt.class, (Enum)Config.DISPLAY_ACTION_BAR).setDefaultValue((Enum)Config.DisplayAt.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_DISPLAY_ACTION_BAR))).setSaveConsumer(newValue -> {
            Config.DISPLAY_ACTION_BAR = newValue;
        }).build());
        SubCategoryBuilder debugScreen = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Debug Screen")).setExpanded(Config.DEFAULT_EXPANDED);
        debugScreen.add((AbstractConfigListEntry)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.debug_screen"), Config.DEBUG_SCREEN).setDefaultValue(false).setSaveConsumer(newValue -> {
            Config.DEBUG_SCREEN = newValue;
        }).build());
        SubCategoryBuilder autoElytra = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)" (Experimental) Elytra - Auto Swap")).setExpanded(Config.DEFAULT_EXPANDED);
        autoElytra.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Elytra - Auto Swap"), Config.AUTO_ELYTRA).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AUTO_ELYTRA)))).setSaveConsumer(newValue -> {
            Config.AUTO_ELYTRA = newValue;
        }).build());
        autoElytra.add((AbstractConfigListEntry)((IntFieldBuilder)entryBuilder.startIntField((class_2561)class_2561.method_43470((String)"Distance/Speed Ratio : Stun Slam (10 - 100) * 0.1 "), Config.AUTO_ELYTRA_TICK_AHEAD).setMax(100).setMin(10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AUTO_ELYTRA_TICK_AHEAD)))).setSaveConsumer(newValue -> {
            Config.AUTO_ELYTRA_TICK_AHEAD = newValue;
        }).build());
        autoElytra.add((AbstractConfigListEntry)((IntFieldBuilder)entryBuilder.startIntField((class_2561)class_2561.method_43470((String)"Distance/Speed Ratio : Hot Swap (10 - 100) * 0.1"), Config.AUTO_ELYTRA_TICK_AHEAD_NORMAL).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_AUTO_ELYTRA_TICK_AHEAD_NORMAL)))).setMax(100).setMin(10).setSaveConsumer(newValue -> {
            Config.AUTO_ELYTRA_TICK_AHEAD_NORMAL = newValue;
        }).build());
        SubCategoryBuilder spiralSetting = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Spiral")).setExpanded(Config.DEFAULT_EXPANDED);
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Spiral Count"), Config.SP_SPIRAL_COUNT, 1, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SPIRAL_COUNT)))).setSaveConsumer(newValue -> {
            Config.SP_SPIRAL_COUNT = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Spiral Length"), Config.SP_SPIRAL_LENGTH, 1, 40).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SPIRAL_LENGTH)))).setSaveConsumer(newValue -> {
            Config.SP_SPIRAL_LENGTH = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Spiral Alpha"), Config.SP_SPIRAL_ALPHA, 1, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SPIRAL_ALPHA)))).setSaveConsumer(newValue -> {
            Config.SP_SPIRAL_ALPHA = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Spiral Gamma"), Config.SP_SPIRAL_GAMMA, 10, 50).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SPIRAL_GAMMA)))).setSaveConsumer(newValue -> {
            Config.SP_SPIRAL_GAMMA = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Coils"), Config.SP_COILS, 1, 8).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COILS)))).setSaveConsumer(newValue -> {
            Config.SP_COILS = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Height"), Config.SP_HEIGHT, 1, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_HEIGHT)))).setSaveConsumer(newValue -> {
            Config.SP_HEIGHT = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Size"), Config.SP_SIZE, 1, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SIZE)))).setSaveConsumer(newValue -> {
            Config.SP_SIZE = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Speed"), Config.SP_SPEED, 1, 50).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_SPEED)))).setSaveConsumer(newValue -> {
            Config.SP_SPEED = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Base Radius"), Config.SP_BASE_RADIUS, 1, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_BASE_RADIUS)))).setSaveConsumer(newValue -> {
            Config.SP_BASE_RADIUS = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Wave Speed"), Config.SP_WAVE_SPEED, 1, 80).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_WAVE_SPEED)))).setSaveConsumer(newValue -> {
            Config.SP_WAVE_SPEED = newValue;
        }).build());
        spiralSetting.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Wave Amplitude"), Config.SP_WAVE_AMPLITUDE, 1, 8).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_WAVE_AMPLITUDE)))).setSaveConsumer(newValue -> {
            Config.SP_WAVE_AMPLITUDE = newValue;
        }).build());
        SubCategoryBuilder markerColor = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Marker Color")).setExpanded(Config.DEFAULT_EXPANDED);
        markerColor.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_villager"), ColorData.Color.class, (Enum)Config.COLOR_VILLAGER).setDefaultValue((Enum)ColorData.Color.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COLOR_VILLAGER))).setSaveConsumer(newValue -> {
            Config.COLOR_VILLAGER = newValue;
        }).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_hostile"), ColorData.Color.class, (Enum)Config.COLOR_HOSTILE).setDefaultValue((Enum)ColorData.Color.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COLOR_HOSTILE))).setSaveConsumer(newValue -> {
            Config.COLOR_HOSTILE = newValue;
        }).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_warden"), ColorData.Color.class, (Enum)Config.COLOR_WARDEN).setDefaultValue((Enum)ColorData.Color.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COLOR_WARDEN))).setSaveConsumer(newValue -> {
            Config.COLOR_WARDEN = newValue;
        }).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_iron_golem"), ColorData.Color.class, (Enum)Config.COLOR_IRON_GOLEM).setDefaultValue((Enum)ColorData.Color.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COLOR_IRON_GOLEM))).setSaveConsumer(newValue -> {
            Config.COLOR_IRON_GOLEM = newValue;
        }).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_passive"), ColorData.Color.class, (Enum)Config.COLOR_PASSIVE).setDefaultValue((Enum)ColorData.Color.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COLOR_PASSIVE))).setSaveConsumer(newValue -> {
            Config.COLOR_PASSIVE = newValue;
        }).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_ambient"), ColorData.Color.class, (Enum)Config.COLOR_AMBIENT).setDefaultValue((Enum)ColorData.Color.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COLOR_AMBIENT))).setSaveConsumer(newValue -> {
            Config.COLOR_AMBIENT = newValue;
        }).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startEnumSelector((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.allowed_player"), ColorData.Color.class, (Enum)Config.COLOR_PLAYER).setDefaultValue((Enum)ColorData.Color.valueOf((String)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_COLOR_PLAYER))).setSaveConsumer(newValue -> {
            Config.COLOR_PLAYER = newValue;
        }).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.dark_blue).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.dark_green).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.dark_aqua).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.dark_red).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.dark_purple).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.gold).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.gray).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.dark_gray).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.blue).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.green).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.aqua).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.red).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.light_purple).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.yellow).build());
        markerColor.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)ColorData.white).build());
        SubCategoryBuilder radarBoxFov = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Radar")).setExpanded(Config.DEFAULT_EXPANDED);
        radarBoxFov.add((AbstractConfigListEntry)entryBuilder.startTextDescription((class_2561)class_2561.method_43471((String)"config.mace_attack_assistance.option.desc_fov").method_27692(class_124.field_1067).method_27692(class_124.field_1060)).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"FOV X (Horizontal) * 10 degree [Gliding , Target Search: On]"), Config.FOV_HORIZONTAL, 1, 36).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FOV_HORIZONTAL)))).setSaveConsumer(newValue -> {
            Config.FOV_HORIZONTAL = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.fov_horizontal")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"FOV Y (Vertical) * 10 degree [Gliding , Target Search: On]"), Config.FOV_VERTICAL, 1, 18).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FOV_VERTICAL)))).setSaveConsumer(newValue -> {
            Config.FOV_VERTICAL = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.fov_vertical")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"FOV X (Horizontal) * 10 degree [Recon Camera Mode]"), Config.FOV_HORIZONTAL_ON_ZOOM, 1, 36).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FOV_HORIZONTAL_ON_ZOOM)))).setSaveConsumer(newValue -> {
            Config.FOV_HORIZONTAL_ON_ZOOM = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.fov_horizontal")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"FOV Y (Vertical) * 10 degree [Recon Camera Mode]"), Config.FOV_VERTICAL_ON_ZOOM, 1, 18).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_FOV_VERTICAL_ON_ZOOM)))).setSaveConsumer(newValue -> {
            Config.FOV_VERTICAL_ON_ZOOM = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.fov_vertical")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Rectangle Horizontal * 10 blocks"), Config.RADAR_HORIZONTAL, 1, 5).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RADAR_HORIZONTAL)))).setSaveConsumer(newValue -> {
            Config.RADAR_HORIZONTAL = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.radar_horizontal")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Rectangle Upward * 10 blocks"), Config.RADAR_UPWARD, 1, 3).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RADAR_UPWARD)))).setSaveConsumer(newValue -> {
            Config.RADAR_UPWARD = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.radar_upward")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Rectangle Downward * 10 blocks"), Config.RADAR_DOWNWARD, 1, 8).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RADAR_DOWNWARD)))).setSaveConsumer(newValue -> {
            Config.RADAR_DOWNWARD = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.radar_downward")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((IntSliderBuilder)entryBuilder.startIntSlider((class_2561)class_2561.method_43470((String)"Rectangle Update Interval"), Config.RADAR_UPDATE_INTERVAL, 1, 10).setDefaultValue((Object)((Integer)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_RADAR_UPDATE_INTERVAL)))).setSaveConsumer(newValue -> {
            Config.RADAR_UPDATE_INTERVAL = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43471((String)"config.mace_attack_assistance.option.tooltip.radar_update_interval")}).build());
        radarBoxFov.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Extraction Processing Thread"), Config.PARALLEL_SEARCH).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_PARALLEL_SEARCH)))).setSaveConsumer(newValue -> {
            Config.PARALLEL_SEARCH = newValue;
        }).setTooltip(new class_2561[]{class_2561.method_43470((String)"(Parallel:Experimental)")}).setYesNoTextSupplier(value -> value != false ? class_2561.method_43470((String)"Parallel") : class_2561.method_43470((String)"Single")).build());
        SubCategoryBuilder defaultExpanded = entryBuilder.startSubCategory((class_2561)class_2561.method_43470((String)"Expand Categories")).setExpanded(Config.DEFAULT_EXPANDED);
        defaultExpanded.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Global"), Config.DEFAULT_EXPANDED_GLOBAL).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_DEFAULT_EXPANDED_GLOBAL)))).setSaveConsumer(newValue -> {
            Config.DEFAULT_EXPANDED_GLOBAL = newValue;
        }).build());
        defaultExpanded.add((AbstractConfigListEntry)((BooleanToggleBuilder)entryBuilder.startBooleanToggle((class_2561)class_2561.method_43470((String)"Sub"), Config.DEFAULT_EXPANDED).setDefaultValue((Object)((Boolean)ModConfigScreen.getDefaultValue(ConfigOperation.PROP_DEFAULT_EXPANDED)))).setSaveConsumer(newValue -> {
            Config.DEFAULT_EXPANDED = newValue;
        }).build());
        attack.addEntry((AbstractConfigListEntry)attackAssist.build());
        attack.addEntry((AbstractConfigListEntry)hotSwap.build());
        attack.addEntry((AbstractConfigListEntry)breachSwap.build());
        attack.addEntry((AbstractConfigListEntry)stunSlam.build());
        attack.addEntry((AbstractConfigListEntry)doubleTap.build());
        attack.addEntry((AbstractConfigListEntry)snapback.build());
        attack.addEntry((AbstractConfigListEntry)shieldDraining.build());
        aim.addEntry((AbstractConfigListEntry)aimAssist.build());
        aim.addEntry((AbstractConfigListEntry)aimTarget.build());
        aim.addEntry((AbstractConfigListEntry)aimOption.build());
        jump.addEntry((AbstractConfigListEntry)jumpAssist.build());
        jump.addEntry((AbstractConfigListEntry)wallClimbing.build());
        jump.addEntry((AbstractConfigListEntry)jumpSpam.build());
        jump.addEntry((AbstractConfigListEntry)jumpMode.build());
        elytra.addEntry((AbstractConfigListEntry)toggleElytraOption.build());
        elytra.addEntry((AbstractConfigListEntry)elytraBoost.build());
        elytra.addEntry((AbstractConfigListEntry)customCrosshair.build());
        item.addEntry((AbstractConfigListEntry)autoRefill.build());
        item.addEntry((AbstractConfigListEntry)autoWindChargeSelector.build());
        item.addEntry((AbstractConfigListEntry)rocketBlitz.build());
        item.addEntry((AbstractConfigListEntry)prioritizeUse.build());
        slot.addEntry((AbstractConfigListEntry)weaponSlotSetting.build());
        slot.addEntry((AbstractConfigListEntry)returnToPrevSlot.build());
        recon.addEntry((AbstractConfigListEntry)reconOption.build());
        friend.addEntry((AbstractConfigListEntry)friendProtection.build());
        effects.addEntry((AbstractConfigListEntry)markerType.build());
        effects.addEntry((AbstractConfigListEntry)targetSearchMode.build());
        effects.addEntry((AbstractConfigListEntry)displayMarkerOnTarget.build());
        effects.addEntry((AbstractConfigListEntry)hideMarkerWhenAimOff.build());
        effects.addEntry((AbstractConfigListEntry)markerOffset.build());
        effects.addEntry((AbstractConfigListEntry)attackParticle.build());
        spiral.addEntry((AbstractConfigListEntry)spiralSetting.build());
        color.addEntry((AbstractConfigListEntry)markerColor.build());
        fov.addEntry((AbstractConfigListEntry)radarBoxFov.build());
        others.addEntry((AbstractConfigListEntry)defaultExpanded.build());
        others.addEntry((AbstractConfigListEntry)autoRewearDelay.build());
        others.addEntry((AbstractConfigListEntry)displayActionBar.build());
        others.addEntry((AbstractConfigListEntry)attackOption.build());
        others.addEntry((AbstractConfigListEntry)suppression.build());
        others.addEntry((AbstractConfigListEntry)debugScreen.build());
        others.addEntry((AbstractConfigListEntry)autoElytra.build());
        builder.setSavingRunnable(ConfigOperation::saveFile);
        return builder.build();
    }

    public static Object getDefaultValue(ConfigOperation.ConfigData configData) {
        switch (configData.type()) {
            case I: {
                return Integer.parseInt(configData.defaultValue());
            }
            case B: {
                return Boolean.parseBoolean(configData.defaultValue());
            }
        }
        return configData.defaultValue();
    }
}


/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_5250
 */
package com.papack.maceattackassistance.client;

import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class ColorData {
    public static final class_5250 dark_blue = class_2561.method_43470((String)(Color.DARK_BLUE.getLabel() + " : " + String.format("%06X", Color.DARK_BLUE.getValue()))).method_27692(class_124.field_1058).method_27692(class_124.field_1067);
    public static final class_5250 dark_green = class_2561.method_43470((String)(Color.DARK_GREEN.getLabel() + " : " + String.format("%06X", Color.DARK_GREEN.getValue()))).method_27692(class_124.field_1077).method_27692(class_124.field_1067);
    public static final class_5250 dark_aqua = class_2561.method_43470((String)(Color.DARK_AQUA.getLabel() + " : " + String.format("%06X", Color.DARK_AQUA.getValue()))).method_27692(class_124.field_1062).method_27692(class_124.field_1067);
    public static final class_5250 dark_red = class_2561.method_43470((String)(Color.DARK_RED.getLabel() + " : " + String.format("%06X", Color.DARK_RED.getValue()))).method_27692(class_124.field_1079).method_27692(class_124.field_1067);
    public static final class_5250 dark_purple = class_2561.method_43470((String)(Color.DARK_PURPLE.getLabel() + " : " + String.format("%06X", Color.DARK_PURPLE.getValue()))).method_27692(class_124.field_1064).method_27692(class_124.field_1067);
    public static final class_5250 gold = class_2561.method_43470((String)(Color.GOLD.getLabel() + " : " + String.format("%06X", Color.GOLD.getValue()))).method_27692(class_124.field_1065).method_27692(class_124.field_1067);
    public static final class_5250 gray = class_2561.method_43470((String)(Color.GRAY.getLabel() + " : " + String.format("%06X", Color.GRAY.getValue()))).method_27692(class_124.field_1080).method_27692(class_124.field_1067);
    public static final class_5250 dark_gray = class_2561.method_43470((String)(Color.DARK_GRAY.getLabel() + " : " + String.format("%06X", Color.DARK_GRAY.getValue()))).method_27692(class_124.field_1063).method_27692(class_124.field_1067);
    public static final class_5250 blue = class_2561.method_43470((String)(Color.BLUE.getLabel() + " : " + String.format("%06X", Color.BLUE.getValue()))).method_27692(class_124.field_1078).method_27692(class_124.field_1067);
    public static final class_5250 green = class_2561.method_43470((String)(Color.GREEN.getLabel() + " : " + String.format("%06X", Color.GREEN.getValue()))).method_27692(class_124.field_1060).method_27692(class_124.field_1067);
    public static final class_5250 aqua = class_2561.method_43470((String)(Color.AQUA.getLabel() + " : " + String.format("%06X", Color.AQUA.getValue()))).method_27692(class_124.field_1075).method_27692(class_124.field_1067);
    public static final class_5250 red = class_2561.method_43470((String)(Color.RED.getLabel() + " : " + String.format("%06X", Color.RED.getValue()))).method_27692(class_124.field_1061).method_27692(class_124.field_1067);
    public static final class_5250 light_purple = class_2561.method_43470((String)(Color.LIGHT_PURPLE.getLabel() + " : " + String.format("%06X", Color.LIGHT_PURPLE.getValue()))).method_27692(class_124.field_1076).method_27692(class_124.field_1067);
    public static final class_5250 yellow = class_2561.method_43470((String)(Color.YELLOW.getLabel() + " : " + String.format("%06X", Color.YELLOW.getValue()))).method_27692(class_124.field_1054).method_27692(class_124.field_1067);
    public static final class_5250 white = class_2561.method_43470((String)(Color.WHITE.getLabel() + " : " + String.format("%06X", Color.WHITE.getValue()))).method_27692(class_124.field_1068).method_27692(class_124.field_1067);

    public static void initialize() {
    }

    public static enum Color {
        DARK_BLUE("DARK BLUE", 170),
        DARK_GREEN("DARK GREEN", 43520),
        DARK_AQUA("DARK AQUA", 43690),
        DARK_RED("DARK RED", 0xAA0000),
        DARK_PURPLE("DARK PURPLE", 0xAA00AA),
        GOLD("GOLD", 0xFFAA00),
        GRAY("GRAY", 0xAAAAAA),
        DARK_GRAY("DARK GRAY", 0x555555),
        BLUE("BLUE", 0x5555FF),
        GREEN("GREEN", 0x55FF55),
        AQUA("AQUA", 0x55FFFF),
        RED("RED", 0xFF5555),
        LIGHT_PURPLE("LIGHT PURPLE", 0xFF55FF),
        YELLOW("YELLOW", 0xFFFF55),
        WHITE("WHITE", 0xFFFFFF);

        private final int value;
        private final String label;

        private Color(String label, int value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return this.label;
        }

        public int getValue() {
            return this.value;
        }
    }
}


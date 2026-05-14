/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.FriendCheckBoxData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;

public class FriendCheckBoxManager {
    private static final List<FriendCheckBoxData> checkBoxDataList = new ArrayList<FriendCheckBoxData>();

    public static void clear() {
        checkBoxDataList.clear();
    }

    public static void addCheckBoxData(String realName, class_2561 displayName) {
        checkBoxDataList.add(new FriendCheckBoxData(realName, displayName));
    }

    public static String getRealName(class_2561 displayName) {
        for (FriendCheckBoxData checkBoxData : checkBoxDataList) {
            if (!checkBoxData.displayName().equals((Object)displayName)) continue;
            return checkBoxData.realName();
        }
        return null;
    }
}


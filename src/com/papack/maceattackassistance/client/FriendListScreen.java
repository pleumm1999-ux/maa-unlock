/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_11905
 *  net.minecraft.class_11908
 *  net.minecraft.class_11909
 *  net.minecraft.class_2561
 *  net.minecraft.class_268
 *  net.minecraft.class_270
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_4185
 *  net.minecraft.class_4286
 *  net.minecraft.class_437
 *  net.minecraft.class_5250
 *  net.minecraft.class_7529
 *  net.minecraft.class_7532
 *  net.minecraft.class_8685
 */
package com.papack.maceattackassistance.client;

import com.papack.maceattackassistance.client.FriendCheckBoxManager;
import com.papack.maceattackassistance.client.FriendManager;
import com.papack.maceattackassistance.client.WorldPlayerData;
import com.papack.maceattackassistance.client.WorldPlayerManager;
import com.papack.maceattackassistance.client.config.Config;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_11905;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_268;
import net.minecraft.class_270;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_4286;
import net.minecraft.class_437;
import net.minecraft.class_5250;
import net.minecraft.class_7529;
import net.minecraft.class_7532;
import net.minecraft.class_8685;

public class FriendListScreen
extends class_437 {
    private final class_437 parent;
    private final List<class_4286> checkboxes = new ArrayList<class_4286>();
    private final List<WorldPlayerData> allPlayers = new ArrayList<WorldPlayerData>();
    private final List<WorldPlayerData> changedPlayers = new ArrayList<WorldPlayerData>();
    private final int LIST_TOP = 54;
    private class_7529 searchBox;
    private int scrollOffset = 0;
    private final int ENTRY_HEIGHT = 20;
    private boolean isDraggingScrollbar = false;
    private int dragStartY = 0;
    private int scrollStartOffset = 0;

    public FriendListScreen(class_437 parent) {
        super((class_2561)class_2561.method_43470((String)"Friend List Manager"));
        this.parent = parent;
    }

    protected void method_25426() {
        super.method_25426();
        this.checkboxes.clear();
        this.searchBox = class_7529.method_71507().method_71508(this.field_22789 / 2 - 100).method_71512(30).method_71510((class_2561)class_2561.method_43470((String)"Search...")).method_71514(0xFFFFFF).method_71511(true).method_71516(0xFFFFFF).method_71513(true).method_71515(false).method_71509(this.field_22793, 200, 20, (class_2561)class_2561.method_43470((String)""));
        this.searchBox.method_44401(this::updateFilteredList);
        this.method_37063((class_364)this.searchBox);
        HashSet<? extends String> savedFriends = new HashSet<String>(FriendManager.getFriendNameList());
        if (Config.FRIEND_NOT_FOUND) {
            for (String string : savedFriends) {
                if (WorldPlayerManager.isContainsName(string)) continue;
                WorldPlayerManager.addPlayer(string, FriendManager.getUUID(string), null, null, true);
            }
        }
        this.allPlayers.clear();
        this.allPlayers.addAll(WorldPlayerManager.getSortedList());
        this.buildCheckboxes(this.allPlayers);
        this.method_37063((class_364)class_4185.method_46430((class_2561)class_2561.method_43470((String)"Done"), b -> {
            this.saveChanges();
            if (this.field_22787 != null) {
                this.field_22787.method_1507(this.parent);
            }
        }).method_46434(this.field_22789 / 2 - 50, this.field_22790 - 30, 100, 20).method_46431());
        this.updateCheckboxPositions();
    }

    private void saveChanges() {
        for (WorldPlayerData data : this.changedPlayers) {
            if (data.name() == null || data.uuid() == null) continue;
            if (data.friend()) {
                FriendManager.addFriend(data.name(), data.uuid());
                continue;
            }
            FriendManager.removeFriend(data.uuid());
        }
    }

    private void updateCheckboxPositions() {
        int startY = 54 - this.scrollOffset;
        for (int i = 0; i < this.checkboxes.size(); ++i) {
            class_4286 cb = this.checkboxes.get(i);
            cb.method_46419(startY + i * 20);
            cb.method_46421(this.field_22789 / 2 - 80);
        }
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        context.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 15, -1);
        super.method_25394(context, mouseX, mouseY, delta);
        int listLeft = this.field_22789 / 2 - 100;
        int listTop = 54;
        int listHeight = this.field_22790 - 80;
        int startY = listTop - this.scrollOffset;
        for (int i = 0; i < this.checkboxes.size(); ++i) {
            class_4286 cb = this.checkboxes.get(i);
            int iconY = startY + i * 20;
            String realName = FriendCheckBoxManager.getRealName(cb.method_25369());
            WorldPlayerData playerData = WorldPlayerManager.getPlayerByName(realName);
            if (playerData != null && playerData.skin() != null) {
                class_7532.method_52722((class_332)context, (class_8685)playerData.skin(), (int)listLeft, (int)iconY, (int)16);
            }
            int checkboxX = listLeft + 20;
            cb.method_46421(checkboxX);
            cb.method_46419(iconY);
        }
        int SCROLLBAR_WIDTH = 8;
        int scrollbarX = this.field_22789 - SCROLLBAR_WIDTH - 5;
        context.method_25294(scrollbarX, listTop, scrollbarX + SCROLLBAR_WIDTH, listTop + listHeight, -13421773);
        int contentHeight = this.checkboxes.size() * 20;
        if (contentHeight > listHeight) {
            int thumbHeight = Math.max(listHeight * listHeight / contentHeight, 20);
            int maxScroll = contentHeight - listHeight;
            int thumbY = listTop + (int)((float)this.scrollOffset / (float)maxScroll * (float)(listHeight - thumbHeight));
            context.method_25294(scrollbarX, thumbY, scrollbarX + SCROLLBAR_WIDTH, thumbY + thumbHeight, -5592406);
        }
    }

    public boolean method_25401(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int listHeight = this.field_22790 - 80;
        int contentHeight = this.checkboxes.size() * 20;
        int maxScroll = Math.max(0, contentHeight - listHeight);
        this.scrollOffset -= (int)(verticalAmount * 10.0);
        if (this.scrollOffset < 0) {
            this.scrollOffset = 0;
        }
        if (this.scrollOffset > maxScroll) {
            this.scrollOffset = maxScroll;
        }
        this.updateCheckboxPositions();
        return true;
    }

    public boolean method_25402(class_11909 click, boolean doubled) {
        double mouseX = click.comp_4798();
        double mouseY = click.comp_4799();
        int screenWidth = this.field_22789;
        int listLeft = screenWidth / 2 - 100;
        int listTop = 40;
        int listHeight = this.field_22790 - 80;
        int listRight = listLeft + 200;
        int contentHeight = this.checkboxes.size() * 20;
        if (contentHeight > listHeight) {
            int thumbHeight = Math.max(listHeight * listHeight / contentHeight, 20);
            int maxScroll = contentHeight - listHeight;
            int thumbY = listTop + (int)((float)this.scrollOffset / (float)maxScroll * (float)(listHeight - thumbHeight));
            if ((mouseX >= (double)listRight && mouseX <= (double)screenWidth || mouseX >= 0.0 && mouseX <= (double)listLeft) && mouseY >= (double)thumbY && mouseY <= (double)(thumbY + thumbHeight)) {
                this.isDraggingScrollbar = true;
                this.dragStartY = (int)mouseY;
                this.scrollStartOffset = this.scrollOffset;
                return true;
            }
        }
        return super.method_25402(click, doubled);
    }

    public boolean method_25406(class_11909 click) {
        if (this.isDraggingScrollbar) {
            this.isDraggingScrollbar = false;
            return true;
        }
        return super.method_25406(click);
    }

    public boolean method_25403(class_11909 click, double offsetX, double offsetY) {
        if (this.isDraggingScrollbar) {
            int listHeight = this.field_22790 - 80;
            int contentHeight = this.checkboxes.size() * 20;
            int maxScroll = contentHeight - listHeight;
            int thumbHeight = Math.max(listHeight * listHeight / contentHeight, 20);
            int deltaYMouse = (int)click.comp_4799() - this.dragStartY;
            float scrollRatio = (float)maxScroll / (float)(listHeight - thumbHeight);
            this.scrollOffset = this.scrollStartOffset + (int)((float)deltaYMouse * scrollRatio);
            if (this.scrollOffset < 0) {
                this.scrollOffset = 0;
            }
            if (this.scrollOffset > maxScroll) {
                this.scrollOffset = maxScroll;
            }
            this.updateCheckboxPositions();
            return true;
        }
        return super.method_25403(click, offsetX, offsetY);
    }

    private class_5250 getColoredDisplayNameWithTeam(String playerName, class_268 team) {
        if (team != null) {
            class_5250 mutableText = class_268.method_1142((class_270)team, (class_2561)class_2561.method_43470((String)playerName));
            mutableText.method_27693(" ").method_10852((class_2561)team.method_1148());
            return mutableText;
        }
        return class_2561.method_43470((String)playerName);
    }

    private void buildCheckboxes(List<WorldPlayerData> playerList) {
        for (class_4286 cb : this.checkboxes) {
            this.method_37066((class_364)cb);
        }
        this.checkboxes.clear();
        int y = 54;
        FriendCheckBoxManager.clear();
        for (WorldPlayerData playerData : playerList) {
            String playerName = playerData.name();
            boolean isChanged = this.isChangedPlayerListContainsName(playerName);
            boolean changedValue = isChanged && this.getChangedData(playerName);
            boolean checked = isChanged ? changedValue : playerData.friend();
            class_268 team = WorldPlayerManager.getTeam(playerData.uuid());
            class_5250 displayName = this.getColoredDisplayNameWithTeam(playerName, team);
            FriendCheckBoxManager.addCheckBoxData(playerName, (class_2561)displayName);
            class_4286 cb = class_4286.method_54787((class_2561)displayName, (class_327)this.field_22793).method_54789(this.field_22789 / 2 - 80, y).method_54794(checked).method_54791(this::changeCheckBoxCallback).method_54788();
            this.checkboxes.add(cb);
            this.method_37063((class_364)cb);
            y += 20;
        }
        this.method_37063((class_364)class_4185.method_46430((class_2561)class_2561.method_43470((String)"Done"), b -> {
            this.saveChanges();
            if (this.field_22787 != null) {
                this.field_22787.method_1507(this.parent);
            }
        }).method_46434(this.field_22789 / 2 - 50, this.field_22790 - 30, 100, 20).method_46431());
        this.updateCheckboxPositions();
    }

    private void updateFilteredList(String query) {
        if (query == null || query.isEmpty()) {
            this.buildCheckboxes(this.allPlayers);
        } else {
            List<WorldPlayerData> filtered = this.allPlayers.stream().filter(p -> p.name().toLowerCase().contains(query.toLowerCase()) || p.team() != null && p.team().method_1197().toLowerCase().contains(query.toLowerCase())).toList();
            this.buildCheckboxes(filtered);
        }
    }

    public boolean method_25404(class_11908 input) {
        if (this.searchBox.method_25404(input)) {
            return true;
        }
        return super.method_25404(input);
    }

    public boolean method_25400(class_11905 inputs) {
        if (this.searchBox.method_25400(inputs)) {
            return true;
        }
        return super.method_25400(inputs);
    }

    private void changeCheckBoxCallback(class_4286 widget, boolean checked) {
        String realName = FriendCheckBoxManager.getRealName(widget.method_25369());
        WorldPlayerData playerData = WorldPlayerManager.getPlayerByName(realName);
        boolean isContain = this.isChangedPlayerListContainsName(realName);
        if (playerData != null) {
            if (isContain) {
                this.removePlayerByName(realName);
            }
            this.changedPlayers.add(new WorldPlayerData(playerData.name(), playerData.uuid(), playerData.skin(), playerData.team(), checked));
        }
    }

    private boolean isChangedPlayerListContainsName(String realName) {
        return this.changedPlayers.stream().anyMatch(f -> f.name().equals(realName));
    }

    private boolean getChangedData(String realName) {
        for (WorldPlayerData data : this.changedPlayers) {
            if (!Objects.equals(realName, data.name())) continue;
            return data.friend();
        }
        return false;
    }

    public void removePlayerByName(String realName) {
        this.changedPlayers.removeIf(p -> p.name().equals(realName));
    }
}


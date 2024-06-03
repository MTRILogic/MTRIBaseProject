package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

public abstract class Modelable implements Parcelable {
    private static final String ITEM_ID = "itemId", VIEW_TYPE = "viewType", ENABLED = "enabled";

    private boolean enabled;
    private int viewType;
    private long itemId;

    public Modelable(long itemId, int viewType, boolean enabled) {
        this.viewType = viewType;
        this.enabled = enabled;
        this.itemId = itemId;
    }

    public Modelable() {}

    protected Modelable(Bundle data) {
        if (data != null) {
            onRestoreFromData(data);
        }
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final int getViewType() {
        return viewType;
    }

    public final long getItemId() {
        return itemId;
    }

    protected void onRestoreFromData(@NotNull Bundle data) {
        enabled = data.getBoolean(ENABLED);
        viewType = data.getInt(VIEW_TYPE);
        itemId = data.getLong(ITEM_ID);
    }

    protected void onSaveToData(@NotNull Bundle data) {
        data.putBoolean(ENABLED, enabled);
        data.putInt(VIEW_TYPE, viewType);
        data.putLong(ITEM_ID, itemId);
    }

    @Override
    public final int describeContents() {
        return 0;
    }

    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        Bundle data = new Bundle();
        onSaveToData(data);
        dest.writeBundle(data);
    }
}

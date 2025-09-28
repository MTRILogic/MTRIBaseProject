package com.mtrilogic.logic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public abstract class Modelable implements Parcelable {

    /*==============================================================================================
    CONSTANTS
    ==============================================================================================*/

    private static final String
            VIEW_TYPE = "viewType",
            ENABLED = "enabled",
            ITEM_ID = "itemId";

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    private boolean enabled;
    private int viewType;
    private long itemId;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Modelable(long itemId, int viewType, boolean enabled) {
        this.viewType = viewType;
        this.enabled = enabled;
        this.itemId = itemId;
    }

    public Modelable() {}

    /*==============================================================================================
    PROTECTED CONSTRUCTORS
    ==============================================================================================*/

    protected Modelable(Bundle data) {
        if (data != null) {
            onRestoreFromData(data);
        }
    }

    /*==============================================================================================
    OVERRIDE PUBLIC FINAL METHODS
    ==============================================================================================*/

    @Override
    public final int describeContents() {
        return 0;
    }

    @Override
    public final void writeToParcel(@NonNull Parcel dest, int flags) {
        Bundle data = new Bundle();
        onSaveToData(data);
        dest.writeBundle(data);
    }

    /*==============================================================================================
    PUBLIC FINAL METHODS
    ==============================================================================================*/

    public final boolean isEnabled() {
        return enabled;
    }

    public final int getViewType() {
        return viewType;
    }

    public final long getItemId() {
        return itemId;
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected void onRestoreFromData(@NonNull Bundle data) {
        enabled = data.getBoolean(ENABLED);
        viewType = data.getInt(VIEW_TYPE);
        itemId = data.getLong(ITEM_ID);
    }

    protected void onSaveToData(@NonNull Bundle data) {
        data.putBoolean(ENABLED, enabled);
        data.putInt(VIEW_TYPE, viewType);
        data.putLong(ITEM_ID, itemId);
    }
}

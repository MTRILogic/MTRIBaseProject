package com.mtrilogic.logic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public abstract class Paginable extends Modelable {

    /*==============================================================================================
    CONSTANTS
    ==============================================================================================*/

    private static final String PAGE_TITLE = "pageTitle", TAG_NAME = "tagName";

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    private CharSequence pageTitle;
    private String tagName;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Paginable(long itemId, int viewType, CharSequence pageTitle, String tagName) {
        super(itemId, viewType, true);
        this.pageTitle = pageTitle;
        this.tagName = tagName;
    }

    public Paginable() {
        super();
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTORS
    ==============================================================================================*/

    protected Paginable(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC FINAL METHODS
    ==============================================================================================*/

    public final CharSequence getPageTitle() {
        return pageTitle;
    }

    public final void setPageTitle(CharSequence pageTitle) {
        this.pageTitle = pageTitle;
    }

    public final String getTagName() {
        return tagName;
    }

    public final void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @Override
    public void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        pageTitle = data.getCharSequence(PAGE_TITLE);
        tagName = data.getString(TAG_NAME);
    }

    @Override
    public void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putCharSequence(PAGE_TITLE, pageTitle);
        data.putString(TAG_NAME, tagName);
    }
}
package com.mtrilogic.logic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.classes.Listable;

@SuppressWarnings("unused")
public abstract class ListablePaginable<M extends Modelable> extends Paginable {

    /*==============================================================================================
    CONSTACTS
    ==============================================================================================*/

    public static final String LISTABLE = "listable";

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    private Listable<M> listable;
    private Class<M> clazz;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public ListablePaginable(long itemId, int viewType, CharSequence pageTitle, String tagName) {
        super(itemId, viewType, pageTitle, tagName);
        listable = new Listable<>();
    }

    // Solo para versione igual o posterior a Tiramisu (33)
    protected ListablePaginable(long itemId, int viewType, CharSequence pageTitle, String tagName, Class<M> clazz) {
        this(itemId, viewType, pageTitle, tagName);
        this.clazz = clazz;
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTORS
    ==============================================================================================*/

    protected ListablePaginable(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public Listable<M> getListable() {
        return listable;
    }

    /*==============================================================================================
    OVERRIDE PROTECTED METHODS
    ==============================================================================================*/

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        if (clazz != null) {
            // Solo para versiones de Android igual o posteriores a Tiramisu (33)
            listable = new Listable<>(data, LISTABLE, clazz);
        } else {
            listable = new Listable<>(data, LISTABLE);
        }
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        listable.saveToData(data, LISTABLE);
    }
}

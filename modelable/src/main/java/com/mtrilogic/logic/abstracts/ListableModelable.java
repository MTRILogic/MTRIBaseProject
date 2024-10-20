package com.mtrilogic.logic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.classes.Listable;

@SuppressWarnings("unused")
public abstract class ListableModelable<M extends Modelable> extends Modelable{

    /*==============================================================================================
    CONSTANTS
    ==============================================================================================*/

    private static final String LISTABLE = "listable";

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    private Listable<M> listable;
    private Class<M> clazz;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public ListableModelable(long itemId, int viewType, boolean enabled) {
        super(itemId, viewType, enabled);
        listable = new Listable<>();
    }

    // Solo para versione igual o posterior a Tiramisu (33)
    public ListableModelable(long itemId, int viewType, boolean enabled, Class<M> clazz) {
        this(itemId, viewType, enabled);
        this.clazz = clazz;
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTORS
    ==============================================================================================*/

    protected ListableModelable(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public final Listable<M> getListable() {
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

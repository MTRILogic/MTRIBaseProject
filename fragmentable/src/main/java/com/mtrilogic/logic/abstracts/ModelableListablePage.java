package com.mtrilogic.logic.abstracts;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.classes.Listable;

@SuppressWarnings("unused")
public abstract class ModelableListablePage extends Paginable {

    /*==============================================================================================
    CONSTANTS
    ==============================================================================================*/

    public static final String LISTABLE = "listable";

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    private final Listable<Modelable> modelableListable = new Listable<>();

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public ModelableListablePage(long itemId, int viewType, CharSequence pageTitle, String tagName) {
        super(itemId, viewType, pageTitle, tagName);
    }

    /*==============================================================================================
    PROTECTED CONSTRUCTORS
    ==============================================================================================*/

    protected ModelableListablePage(Bundle data) {
        super(data);
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public Listable<Modelable> getModelableListable() {
        return modelableListable;
    }

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @Override
    public void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        modelableListable.restoreFromData(data, LISTABLE, Modelable.class);
    }

    @Override
    public void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        modelableListable.saveToData(data, LISTABLE);
    }
}

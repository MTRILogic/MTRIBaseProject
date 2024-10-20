package com.mtrilogic.ui.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.ui.interfaces.Bindable;
import com.mtrilogic.ui.listeners.InflatableListener;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public abstract class Inflatable<M extends Modelable> implements Bindable {

    /*==============================================================================================
    PROTECTED INMUTABLES
    ==============================================================================================*/

    protected final InflatableListener listener;
    protected final Class<M> clazz;

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    protected View itemView;
    protected int position;
    protected M model;

    /*==============================================================================================
    PROTECTED ABSTRACT METHODS
    ==============================================================================================*/

    protected abstract int getLayoutResource();

    protected abstract void onBindItemView();

    protected abstract void onBindModel();

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Inflatable(@NotNull Class<M> clazz, @NotNull InflatableListener listener) {
        this.listener = listener;
        this.clazz = clazz;
    }

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @Override
    public View getItemView(@NotNull LayoutInflater inflater, @NotNull ViewGroup parent) {
        int layoutResource = getLayoutResource();
        itemView = inflater.inflate(layoutResource, parent, false);
        onBindItemView();
        return itemView;
    }

    @Override
    public void bindModelable(@NotNull Modelable modelable, int position) {
        model = clazz.cast(modelable);
        this.position = position;
        onBindModel();
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected final void autoDelete() {
        if (listener.getModelableListable().getList().remove(model)) {
            listener.getInflatableAdapter().notifyDataSetChanged();
        }
    }
}

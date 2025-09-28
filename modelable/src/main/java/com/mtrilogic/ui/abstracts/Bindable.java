package com.mtrilogic.ui.abstracts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.logic.listeners.OnMakeToastListener;

import java.util.List;

@SuppressWarnings("unused")
public abstract class Bindable<M extends Modelable> {
    protected final ActionListener<M> actionListener;
    protected final Listener listener;
    private final Class<M> clazz;

    protected View itemView;
    protected int position;
    protected M model;

    /*==============================================================================================
    PROTECTED ABSTRACT METHODS
    ==============================================================================================*/

    protected abstract View onBindItemView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindModel();

    public Bindable(@NonNull Class<M> clazz, @NonNull Listener listener, ActionListener<M> actionListener) {
        this.actionListener = actionListener;
        this.listener = listener;
        this.clazz = clazz;
    }

    public View getItemView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        itemView = onBindItemView(inflater, parent);
        return itemView;
    }

    public void bindModelable(@NonNull Modelable modelable, int position) {
        model = clazz.cast(modelable);
        this.position = position;
        onBindModel();
    }

    /*==============================================================================================
    PROTECTED FINAL METHODS
    ==============================================================================================*/

    protected final boolean removeModel() {
        return listener.getModelableListable().getList().remove(model);
    }

    protected final void makeToast(String line, boolean background) {
        listener.onMakeToast(line, background);
    }

    /*==============================================================================================
    PUBLIC STATIC CLASS
    ==============================================================================================*/

    public static abstract class ActionAdapter<M extends Modelable> implements ActionListener<M> {
        @Override
        public boolean onItemLongClick(@NonNull View itemView, @NonNull M model, int position) {
            return false;
        }

        @Override
        public void onItemClick(@NonNull View itemView, @NonNull M model, int position) {

        }
    }

    /*==============================================================================================
    PUBLIC INTERFACES
    ==============================================================================================*/

    public interface ActionListener<M extends Modelable> {
        /** @noinspection SameReturnValue*/
        boolean onItemLongClick(@NonNull View itemView, @NonNull M model, int position);
        /** @noinspection EmptyMethod*/
        void onItemClick(@NonNull View itemView, @NonNull M model, int position);
    }

    public interface Listener extends OnMakeToastListener {
        @NonNull Listable<Modelable> getModelableListable();
    }

    public interface AdapterListener {
        @NonNull List<Modelable> getModelableList();
    }
}

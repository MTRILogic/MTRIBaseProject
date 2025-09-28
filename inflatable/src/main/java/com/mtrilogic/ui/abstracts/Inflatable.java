package com.mtrilogic.ui.abstracts;

import android.widget.AbsListView;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.ui.adapters.InflatableAdapter;

@SuppressWarnings("unused")
public abstract class Inflatable<M extends Modelable> extends Bindable<M> {

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Inflatable(@NonNull Class<M> clazz, @NonNull Listener listener, ActionListener<M> actionListener) {
        super(clazz, listener, actionListener);
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected void autoDelete() {
        if (removeModel()) {
            notifyAdapter();
        }
    }

    protected void notifyAdapter() {
        getListener().getInflatableAdapter().notifyDataSetChanged();
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private Listener getListener() {
        return (Listener) listener;
    }

    /*==============================================================================================
    PUBLIC LISTENER
    ==============================================================================================*/

    public interface Listener extends Bindable.Listener {
        @NonNull InflatableAdapter getInflatableAdapter();
        @NonNull AbsListView getListView();
    }
}

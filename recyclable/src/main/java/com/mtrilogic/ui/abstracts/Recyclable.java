package com.mtrilogic.ui.abstracts;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.ui.adapters.RecyclableAdapter;

@SuppressWarnings("unused")
public abstract class Recyclable<M extends Modelable> extends Bindable<M> {

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Recyclable(@NonNull Class<M> clazz, @NonNull Listener listener, ActionListener<M> actionListener) {
        super(clazz, listener, actionListener);
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected void autoDelete() {
        if (removeModel()) {
            notifyRemove();
        }
    }

    protected void notifyRemove() {
        getListener().getRecyclableAdapter().notifyItemRemoved(position);
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
        @NonNull RecyclableAdapter getRecyclableAdapter();
        @NonNull RecyclerView getRecyclerView();
    }
}

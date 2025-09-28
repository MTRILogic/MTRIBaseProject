package com.mtrilogic.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.ui.abstracts.Bindable;
import com.mtrilogic.ui.abstracts.Recyclable;
import com.mtrilogic.ui.classes.ViewHolder;

import java.util.List;

@SuppressWarnings("unused")
public final class RecyclableAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final LayoutInflater inflater;
    private final Listener listener;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public RecyclableAdapter(@NonNull LayoutInflater inflater, @NonNull Listener listener) {
        this.inflater = inflater;
        this.listener = listener;
        setHasStableIds(true);
    }

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Recyclable<? extends Modelable> recyclable = listener.getRecyclable(viewType);
        View itemView = recyclable.getItemView(inflater, parent);
        return new ViewHolder(itemView, recyclable);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Modelable modelable = getModelable(position);
        Recyclable<? extends Modelable> recyclable = holder.getRecyclable();
        recyclable.bindModelable(modelable, position);
    }

    @Override
    public int getItemCount() {
        return getModelableList().size();
    }

    @Override
    public long getItemId(int position) {
        return getModelable(position).getItemId();
    }

    @Override
    public int getItemViewType(int position) {
        return getModelable(position).getViewType();
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    @NonNull
    private List<Modelable> getModelableList() {
        return listener.getModelableList();
    }

    @NonNull
    private Modelable getModelable(int position) {
        return getModelableList().get(position);
    }

    /*==============================================================================================
    PUBLIC LISTENER
    ==============================================================================================*/

    public interface Listener extends Bindable.AdapterListener {
        @NonNull Recyclable<? extends Modelable> getRecyclable(int viewType);
    }
}

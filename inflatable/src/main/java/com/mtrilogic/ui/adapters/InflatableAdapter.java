package com.mtrilogic.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.ui.abstracts.Bindable;
import com.mtrilogic.ui.abstracts.Inflatable;

import java.util.List;

@SuppressWarnings("unused")
public final class InflatableAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final Listener listener;
    private final int typeCount;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public InflatableAdapter(@NonNull LayoutInflater inflater, int typeCount, @NonNull Listener listener) {
        this.typeCount = typeCount > 0 ? typeCount : 1;
        this.inflater = inflater;
        this.listener = listener;
    }

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @Override
    public int getCount() {
        return getModelableList().size();
    }

    @NonNull
    @Override
    public Modelable getItem(int position) {
        return getModelable(position);
    }

    @Override
    public long getItemId(int position) {
        return getModelable(position).getItemId();
    }

    @NonNull
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        Modelable modelable = getModelable(position);
        Inflatable<? extends Modelable> inflatable;
        if (itemView != null) {
            inflatable = (Inflatable<? extends Modelable>) itemView.getTag();
        } else {
            int viewType = modelable.getViewType();
            inflatable = listener.getInflatable(viewType);
            itemView = inflatable.getItemView(inflater, parent);
            itemView.setTag(inflatable);
        }
        inflatable.bindModelable(modelable, position);
        return itemView;
    }

    @NonNull
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return getModelable(position).getViewType();
    }

    @Override
    public int getViewTypeCount() {
        return typeCount;
    }

    @Override
    public boolean isEnabled(int position) {
        return getModelable(position).isEnabled();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean hasStableIds() {
        return true;
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
    PUBLIC INTERFACE
    ==============================================================================================*/

    public interface Listener extends Bindable.AdapterListener {
        @NonNull Inflatable<? extends Modelable> getInflatable(int viewType);
    }
}

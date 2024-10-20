package com.mtrilogic.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mtrilogic.ui.abstracts.Inflatable;
import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.ui.interfaces.Bindable;
import com.mtrilogic.ui.listeners.InflatableAdapterListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class InflatableAdapter extends BaseAdapter {

    /*==============================================================================================
    PRIVATE INMUTABLES
    ==============================================================================================*/

    private final InflatableAdapterListener listener;
    private final LayoutInflater inflater;
    private final int typeCount;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public InflatableAdapter(@NotNull LayoutInflater inflater, int typeCount, @NotNull InflatableAdapterListener listener) {
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

    @Override
    public Modelable getItem(int position) {
        return getModelable(position);
    }

    @Override
    public long getItemId(int position) {
        return getModelable(position).getItemId();
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        Modelable modelable = getModelable(position);
        Bindable bindable;
        if (itemView != null) {
            bindable = (Inflatable<? extends Modelable>) itemView.getTag();
        } else {
            int viewType = modelable.getViewType();
            bindable = listener.getInflatable(viewType);
        }
        if (bindable != null) {
            if (itemView == null) {
                itemView = bindable.getItemView(inflater, parent);
                itemView.setTag(bindable);
            }
            bindable.bindModelable(modelable, position);
        }
        return itemView;
    }

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

    private List<Modelable> getModelableList() {
        return listener.getModelableListable().getList();
    }

    private Modelable getModelable(int position) {
        return getModelableList().get(position);
    }
}

package com.mtrilogic.ui.listeners;

import android.widget.AbsListView;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.listeners.OnMakeToastListener;
import com.mtrilogic.ui.adapters.InflatableAdapter;
import com.mtrilogic.logic.classes.Listable;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public interface InflatableListener extends OnMakeToastListener {

    @NotNull Listable<Modelable> getModelableListable();

    @NotNull InflatableAdapter getInflatableAdapter();

    @NotNull AbsListView getListView();
}

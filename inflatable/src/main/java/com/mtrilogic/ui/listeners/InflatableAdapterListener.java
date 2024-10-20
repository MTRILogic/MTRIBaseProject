package com.mtrilogic.ui.listeners;

import com.mtrilogic.logic.listeners.OnMakeToastListener;
import com.mtrilogic.ui.abstracts.Inflatable;
import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.classes.Listable;

import org.jetbrains.annotations.NotNull;

public interface InflatableAdapterListener extends OnMakeToastListener {

    @NotNull Inflatable<? extends Modelable> getInflatable(int viewType);

    @NotNull Listable<Modelable> getModelableListable();
}

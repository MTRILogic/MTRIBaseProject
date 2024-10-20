package com.mtrilogic.ui.interfaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.logic.abstracts.Modelable;

import org.jetbrains.annotations.NotNull;

public interface Bindable {

    @NotNull View getItemView(@NotNull LayoutInflater inflater, @NotNull ViewGroup parent);

    void bindModelable(@NotNull Modelable modelable, int position);
}

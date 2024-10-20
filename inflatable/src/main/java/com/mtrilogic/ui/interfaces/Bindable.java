package com.mtrilogic.ui.interfaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtrilogic.logic.abstracts.Modelable;

public interface Bindable {

    View getItemView(LayoutInflater inflater, ViewGroup parent);

    void bindModelable(Modelable modelable, int position);
}

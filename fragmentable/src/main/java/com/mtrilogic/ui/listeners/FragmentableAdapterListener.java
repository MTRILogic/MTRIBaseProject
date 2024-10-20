package com.mtrilogic.ui.listeners;

import androidx.annotation.NonNull;

import com.mtrilogic.ui.abstracts.Fragmentable;
import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.logic.listeners.OnMakeToastListener;

public interface FragmentableAdapterListener extends OnMakeToastListener {

    @NonNull
    Fragmentable<? extends Paginable> getFragmentable(int viewType);

    void onPositionChanged(int oldPosition, int position);

    @NonNull
    Listable<Paginable> getPaginableListable();
}

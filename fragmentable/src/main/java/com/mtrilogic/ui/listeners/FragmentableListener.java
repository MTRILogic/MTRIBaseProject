package com.mtrilogic.ui.listeners;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.ui.adapters.FragmentableAdapter;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.logic.listeners.OnMakeToastListener;

@SuppressWarnings("unused")
public interface FragmentableListener extends OnMakeToastListener {

    @NonNull
    FragmentableAdapter getFragmentableAdapter();

    @NonNull
    Listable<Paginable> getPaginableListable();

    @NonNull
    ViewPager getViewPager();
}

package com.mtrilogic.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.ui.abstracts.Fragmentable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"deprecation", "unused"})
public final class FragmentableStateAdapter extends FragmentStatePagerAdapter {
    private final List<Fragmentable<? extends Modelable>> fragmentableList = new ArrayList<>();
    private final Listable<Paginable> paginableListable;
    private final Listener listener;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public FragmentableStateAdapter(@NonNull Listable<Paginable> paginableListable, @NonNull FragmentManager manager, @NonNull Listener listener) {
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.paginableListable = paginableListable;
        this.listener = listener;
    }

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Paginable paginable = getPaginable(position);
        int viewType = paginable.getViewType();
        Fragmentable<? extends Paginable> fragmentable = listener.getFragmentable(viewType);
        fragmentable.bindPaginable(paginable, position);
        fragmentableList.add(fragmentable);
        return fragmentable;
    }

    @Override
    public int getCount() {
        return getPaginableList().size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragmentable<? extends Paginable> fragmentable = (Fragmentable<? extends Paginable>) object;
        Paginable paginable = fragmentable.getPaginable();
        int position = getPaginableList().indexOf(paginable);
        if (position != fragmentable.getPosition()) {
            if (position >= 0) {
                updateFragmentablesFromPositions(position);
                return position;
            }
            return POSITION_NONE;
        }
        return POSITION_UNCHANGED;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getPaginable(position).getPageTitle();
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private void updateFragmentablesFromPositions(int position) {
        for (int i = position; i < getCount(); i++) {
            fragmentableList.get(i).bindPosition(i);
        }
    }

    private List<Paginable> getPaginableList(){
        return paginableListable.getList();
    }

    private Paginable getPaginable(int position){
        return getPaginableList().get(position);
    }

    /*==============================================================================================
    PUBLIC INTERFACE
    ==============================================================================================*/

    public interface Listener {
        @NonNull Fragmentable<? extends Paginable> getFragmentable(int viewType);
    }
}

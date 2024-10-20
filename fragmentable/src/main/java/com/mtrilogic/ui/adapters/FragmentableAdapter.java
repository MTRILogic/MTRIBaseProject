package com.mtrilogic.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mtrilogic.ui.abstracts.Fragmentable;
import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.ui.listeners.FragmentableAdapterListener;

import java.util.List;

@SuppressWarnings({"deprecation", "unused"})
public class FragmentableAdapter extends FragmentPagerAdapter {

    /*==============================================================================================
    INMUTABLES
    ==============================================================================================*/

    private final FragmentableAdapterListener listener;

    /*==============================================================================================
    PUBLIC CONSTRUCTOR
    ==============================================================================================*/

    public FragmentableAdapter(@NonNull FragmentManager manager, @NonNull FragmentableAdapterListener listener) {
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.listener = listener;
    }

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @NonNull
    @Override
    public Fragmentable<? extends Paginable> getItem(int position) {
        Paginable paginable = getPaginable(position);
        int viewType = paginable.getViewType();
        Fragmentable<? extends Paginable> fragmentable = listener.getFragmentable(viewType);
        fragmentable.bindPaginable(paginable, position);
        return fragmentable;
    }

    @Override
    public int getCount() {
        return getPaginableList().size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Fragmentable<? extends Paginable> fragmentable = (Fragmentable<? extends Paginable>) object;
        int oldPosition = fragmentable.getPosition();
        Paginable paginable = fragmentable.getPaginable();
        int position = getPaginableList().indexOf(paginable);
        if (oldPosition != position){
            listener.onPositionChanged(oldPosition, position);
            if (position > -1){
                fragmentable.bindPosition(position);
                return position;
            }
            return POSITION_NONE; // when delete the last item.
        }
        return POSITION_UNCHANGED; // when append an item.
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getPaginable(position).getPageTitle();
    }

    @Override
    public long getItemId(int position) {
        return getPaginable(position).getItemId();
    }

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private List<Paginable> getPaginableList(){
        return listener.getPaginableListable().getList();
    }

    private Paginable getPaginable(int position){
        return getPaginableList().get(position);
    }
}

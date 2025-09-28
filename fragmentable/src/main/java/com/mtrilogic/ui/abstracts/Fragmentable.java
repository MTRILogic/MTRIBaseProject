package com.mtrilogic.ui.abstracts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.ui.adapters.FragmentableAdapter;

@SuppressWarnings("unused")
public abstract class Fragmentable<P extends Paginable> extends BaseFragment<P> {
    protected static final String POSITION = "position";

    protected int position;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Fragmentable() {}

    /*==============================================================================================
    PUBLIC OVERRIDE METHODS
    ==============================================================================================*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new RuntimeException("<Fragmentable> No Listener attached");
        }
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public void bindPaginable(@NonNull Paginable paginable, int position) {
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        args.putInt(POSITION, position);
        setArguments(args);
    }

    public void bindPosition(int position) {
        Bundle args = getArguments();
        if (args != null) {
            args.putInt(POSITION, position);
            onUpdatePosition(position);
        }
    }

    public int getPosition() {
        return position;
    }

    /*==============================================================================================
    PROTECTED METHODS
    ==============================================================================================*/

    protected void autoDelete(){
        if (getListener().getPaginableListable().getList().remove(page)) {
            notifyChanged();
        }
    }

    protected void notifyChanged(){
        getListener().getFragmentableAdapter().notifyDataSetChanged();
    }

    protected void onUpdatePosition(int position) {
        this.position = position;
    }

    protected Listener getListener() {
        return (Listener) listener;
    }

    /*==============================================================================================
    PUBLIC LISTENER
    ==============================================================================================*/

    public interface Listener extends BaseFragment.Listener {
        @NonNull FragmentableAdapter getFragmentableAdapter();
        @NonNull Listable<Paginable> getPaginableListable();
        @NonNull ViewPager getViewPager();
    }
}

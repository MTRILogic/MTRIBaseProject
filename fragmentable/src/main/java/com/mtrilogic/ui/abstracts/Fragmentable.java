package com.mtrilogic.ui.abstracts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.ui.listeners.FragmentableListener;

@SuppressWarnings("unused")
public abstract class Fragmentable<P extends Paginable> extends Fragment {

    /*==============================================================================================
    CONSTANTS
    ==============================================================================================*/

    private static final String PAGE = "page", POSITION = "position";

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    protected FragmentableListener listener;
    protected int position;
    protected P page;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Fragmentable() {}

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentableListener){
            this.listener = (FragmentableListener) context;
        }else {
            throw new RuntimeException("BaseFragment > No FragmentableListener attached");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null){
            position = args.getInt(POSITION);
            page = args.getParcelable(PAGE);
            if (page != null){
                String tagName = page.getTagName();
                String tag = getTag();
                if (tag != null && !tag.equals(tagName)){
                    page.setTagName(tag);
                }
            }
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public final void bindPaginable(@NonNull Paginable paginable, int position) {
        Bundle args = new Bundle();
        args.putParcelable(PAGE, paginable);
        args.putInt(POSITION, position);
        setArguments(args);
    }

    public final void bindPosition(int position) {
        this.position = position;
        Bundle args = getArguments();
        if (args != null) {
            args.putInt(POSITION, position);
        }
    }

    @NonNull
    public final Paginable getPaginable() {
        return page;
    }

    public final int getPosition() {
        return position;
    }

    /*==============================================================================================
    PROTECTED FINAL METHODS
    ==============================================================================================*/

    protected final void autoDelete(){
        if (listener.getPaginableListable().getList().remove(page)) {
            notifyChanged();
        }
    }

    protected final void notifyChanged(){
        listener.getFragmentableAdapter().notifyDataSetChanged();
    }

    protected final void makeToast(String line){
        listener.onMakeToast(line);
    }
}

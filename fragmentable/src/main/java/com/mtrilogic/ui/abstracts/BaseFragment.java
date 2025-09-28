package com.mtrilogic.ui.abstracts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.logic.listeners.OnMakeToastListener;

@SuppressWarnings("unused")
public abstract class BaseFragment<P extends Paginable> extends Fragment {
    protected static final String PAGINABLE = "paginable";

    protected Listener listener;
    protected P page;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public BaseFragment() {}

    /*==============================================================================================
    OVERRIDE PUBLIC METHODS
    ==============================================================================================*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            this.listener = (Listener) context;
        }else {
            throw new RuntimeException("<BaseFragment> No Listener attached");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null){
            page = args.getParcelable(PAGINABLE);
            if (page != null){
                String tag = getTag();
                if (tag != null && !tag.equals(page.getTagName())) {
                    page.setTagName(tag);
                }
                return;
            }
            throw new RuntimeException("<BaseFragment> No paginable binded");
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

    public void bindPaginable(@NonNull Paginable paginable) {
        Bundle args = new Bundle();
        args.putParcelable(PAGINABLE, paginable);
        setArguments(args);
    }

    @NonNull
    public Paginable getPaginable() {
        return page;
    }

    /*==============================================================================================
    PROTECTED FINAL METHODS
    ==============================================================================================*/

    protected final void makeToast(String line, boolean background) {
        listener.onMakeToast(line, background);
    }

    /*==============================================================================================
    PUBLIC INTERFACE
    ==============================================================================================*/

    public interface Listener extends OnMakeToastListener {

    }
}

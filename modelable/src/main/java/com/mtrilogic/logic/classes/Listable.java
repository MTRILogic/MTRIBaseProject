package com.mtrilogic.logic.classes;

import android.os.Build;
import android.os.Bundle;

import com.mtrilogic.logic.abstracts.Modelable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Listable<M extends Modelable> {

    /*==============================================================================================
    CONSTACTS
    ==============================================================================================*/

    private static final String LIST = "list", IDX = "idx";

    /*==============================================================================================
    INMUTABLES
    ==============================================================================================*/

    private final List<M> list = new ArrayList<>();

    /*==============================================================================================
    VARIABLES
    ==============================================================================================*/

    private long idx;

    /*==============================================================================================
    PUBLIC CONSTRUCTORS
    ==============================================================================================*/

    public Listable(@NotNull Bundle data, @NotNull String key) {
        List<M> list = data.getParcelableArrayList(LIST + key);
        setListable(list, data, key);
    }

    // Just to use with Tiramisu version
    public Listable(@NotNull Bundle data, @NotNull String key, @NotNull Class<M> clazz) {
        List<M> list;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            list = data.getParcelableArrayList(LIST + key, clazz);
        } else {
            list = data.getParcelableArrayList(LIST + key);
        }
        setListable(list, data, key);
    }

    public Listable() {}

    /*==============================================================================================
    PRIVATE METHODS
    ==============================================================================================*/

    private void  setListable(List<M> list, @NotNull Bundle data, @NotNull String key) {
        if (list != null) {
            idx = data.getLong(IDX + key);
            this.list.addAll(list);
        }
    }

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public List<M> getList() {
        return list;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public long getIdx() {
        return idx;
    }

    public void clear() {
        list.clear();
        idx = 0;
    }

    public void saveToData(@NotNull Bundle data, @NotNull String key) {
        data.putParcelableArrayList(LIST + key, (ArrayList<M>) list);
        data.putLong(IDX + key, idx);
    }
}

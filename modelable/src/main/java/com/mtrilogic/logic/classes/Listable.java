package com.mtrilogic.logic.classes;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.abstracts.Modelable;

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

    public Listable(@NonNull Bundle data, @NonNull String key, @NonNull Class<M> clazz) {
        restoreFromData(data, key, clazz);
    }

    public Listable() {}

    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/

    public void restoreFromData(@NonNull Bundle data, @NonNull String key, @NonNull Class<M> clazz) {
        List<M> list;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            list = data.getParcelableArrayList(LIST + key, clazz);
        } else {
            list = data.getParcelableArrayList(LIST + key);
        }
        if (list != null) {
            idx = data.getLong(IDX + key);
            this.list.addAll(list);
        }
    }

    public void saveToData(@NonNull Bundle data, @NonNull String key) {
        data.putParcelableArrayList(LIST + key, new ArrayList<>(list));
        data.putLong(IDX + key, idx);
    }

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
}

package com.mtrilogic.classes;

import android.os.Bundle;
import android.os.Parcelable;

import com.mtrilogic.abstracts.Modelable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Listable<M extends Modelable> {

    private static final String LIST = "list", IDX = "idx";

    private final List<M> list = new ArrayList<>();

    private long idx;

    public Listable(@NotNull Bundle data, @NotNull String key) {
        List<M> list = data.getParcelableArrayList(LIST + key);
        if (list != null) {
            idx = data.getLong(IDX + key);
            this.list.addAll(list);
        }
    }

    public Listable() {}

    public List<M> getList() {
        return list;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public long getIdx() {
        return idx;
    }

    public void saveToData(@NotNull Bundle data, @NotNull String key) {
        data.putParcelableArrayList(LIST + key, (ArrayList<M>) list);
        data.putLong(IDX + key, idx);
    }
}

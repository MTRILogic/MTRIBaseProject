package com.mtrilogic.ui.classes;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.ui.abstracts.Recyclable;

@SuppressWarnings("unused")
public class ViewHolder extends RecyclerView.ViewHolder {
    private final Recyclable<? extends Modelable> recyclable;

    public ViewHolder(@NonNull View itemView, @NonNull Recyclable<? extends Modelable> recyclable) {
        super(itemView);
        this.recyclable = recyclable;
    }

    public Recyclable<? extends Modelable> getRecyclable() {
        return recyclable;
    }
}

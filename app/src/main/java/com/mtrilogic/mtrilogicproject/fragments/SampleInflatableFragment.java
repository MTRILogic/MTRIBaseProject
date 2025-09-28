package com.mtrilogic.mtrilogicproject.fragments;

import static com.mtrilogic.mtrilogicproject.utils.MainUtil.getName;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.mtrilogicproject.databinding.FragmentInflatableSampleBinding;
import com.mtrilogic.mtrilogicproject.items.SampleInflatableItem;
import com.mtrilogic.mtrilogicproject.models.SampleModel;
import com.mtrilogic.mtrilogicproject.pages.SamplePage;
import com.mtrilogic.ui.abstracts.Fragmentable;
import com.mtrilogic.ui.abstracts.Inflatable;
import com.mtrilogic.ui.adapters.InflatableAdapter;

import java.util.List;

public class SampleInflatableFragment extends Fragmentable<SamplePage> implements InflatableAdapter.Listener, Inflatable.Listener {
    private FragmentInflatableSampleBinding binding;
    private InflatableAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInflatableSampleBinding.inflate(inflater, container, false);

        if (savedInstanceState == null) {
            addDumbItems();
        }

        adapter = new InflatableAdapter(inflater, 1, this);
        binding.lvwItems.setAdapter(adapter);

        return binding.getRoot();
    }

    @NonNull
    @Override
    public Inflatable<? extends Modelable> getInflatable(int viewType) {
        return new SampleInflatableItem(this, null);
    }

    @NonNull
    @Override
    public List<Modelable> getModelableList() {
        return page.getModelableListable().getList();
    }

    @NonNull
    @Override
    public InflatableAdapter getInflatableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public AbsListView getListView() {
        return binding.lvwItems;
    }

    @NonNull
    @Override
    public Listable<Modelable> getModelableListable() {
        return page.getModelableListable();
    }

    @Override
    public void onMakeToast(String line, boolean background) {
        makeToast(line, background);
    }

    private void addDumbItems() {
        Listable<Modelable> listable = page.getModelableListable();
        long idx = 0;
        for (int i = 0; i < 10; i++) {
            SampleModel model = new SampleModel(idx);
            model.setTitle("item " + i);
            model.setContent(getName(i));
            model.setIcon(i);
            if (listable.getList().add(model)) {
                idx++;
            }
        }
        if (idx != 0) {
            listable.setIdx(idx);
        }
    }
}

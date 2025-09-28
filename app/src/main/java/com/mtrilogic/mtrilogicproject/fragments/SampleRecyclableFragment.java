package com.mtrilogic.mtrilogicproject.fragments;

import static com.mtrilogic.mtrilogicproject.utils.MainUtil.getName;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.mtrilogicproject.databinding.FragmentRecyclableSampleBinding;
import com.mtrilogic.mtrilogicproject.items.SampleRecyclableItem;
import com.mtrilogic.mtrilogicproject.models.SampleModel;
import com.mtrilogic.mtrilogicproject.pages.SamplePage;
import com.mtrilogic.ui.abstracts.Fragmentable;
import com.mtrilogic.ui.abstracts.Recyclable;
import com.mtrilogic.ui.adapters.RecyclableAdapter;

import java.util.List;

public class SampleRecyclableFragment extends Fragmentable<SamplePage> implements RecyclableAdapter.Listener, Recyclable.Listener {
    private FragmentRecyclableSampleBinding binding;
    private RecyclableAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclableSampleBinding.inflate(inflater, container, false);

        if (savedInstanceState == null) {
            addDumbItems();
        }

        adapter = new RecyclableAdapter(inflater, this);

        binding.lvwItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.lvwItems.setAdapter(adapter);

        return binding.getRoot();
    }

    @NonNull
    @Override
    public Recyclable<? extends Modelable> getRecyclable(int viewType) {
        return new SampleRecyclableItem(this, null);
    }

    @NonNull
    @Override
    public List<Modelable> getModelableList() {
        return page.getModelableListable().getList();
    }

    @NonNull
    @Override
    public RecyclableAdapter getRecyclableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public RecyclerView getRecyclerView() {
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

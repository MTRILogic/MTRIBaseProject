package com.mtrilogic.mtrilogicproject.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.mtrilogicproject.databinding.ItemSampleBinding;
import com.mtrilogic.mtrilogicproject.models.SampleModel;
import com.mtrilogic.mtrilogicproject.utils.MainUtil;
import com.mtrilogic.ui.abstracts.Inflatable;

public class SampleInflatableItem extends Inflatable<SampleModel> {
    private ItemSampleBinding binding;

    public SampleInflatableItem(@NonNull Listener listener, ActionListener<SampleModel> actionListener) {
        super(SampleModel.class, listener, actionListener);
    }

    @Override
    protected View onBindItemView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        binding = ItemSampleBinding.inflate(inflater, parent, false);
        return binding.getRoot();
    }

    @Override
    protected void onBindModel() {
        binding.ivwIcon.setImageDrawable(MainUtil.getDrawable(model.getIcon()));
        binding.lblContent.setText(model.getContent());
        binding.lblTitle.setText(model.getTitle());
    }
}

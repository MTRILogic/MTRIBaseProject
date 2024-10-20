package com.mtrilogic.mtrilogicproject.models;

import android.os.Bundle;

import com.mtrilogic.logic.abstracts.ListableModelable;
import com.mtrilogic.logic.abstracts.ModelableCreator;

public class SampleListableModel extends ListableModelable<SampleModel> {
    public static final Creator<SampleListableModel> CREATOR = new ModelableCreator<>() {
        @Override
        protected SampleListableModel createFromData(Bundle data) {
            return new SampleListableModel(data);
        }

        @Override
        public SampleListableModel[] newArray(int size) {
            return new SampleListableModel[size];
        }
    };

    public SampleListableModel(long itemId) {
        super(itemId, 2, true);
    }

    private SampleListableModel(Bundle data) {
        super(data);
    }
}

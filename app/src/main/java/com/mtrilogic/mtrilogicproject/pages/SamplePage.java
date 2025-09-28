package com.mtrilogic.mtrilogicproject.pages;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.abstracts.ModelableListablePage;
import com.mtrilogic.logic.abstracts.PaginableCreator;

public class SamplePage extends ModelableListablePage {
    public static final Creator<SamplePage> CREATOR = new PaginableCreator<>() {
        @NonNull
        @Override
        protected SamplePage createFromData(Bundle data) {
            return new SamplePage(data);
        }

        @NonNull
        @Override
        public SamplePage[] newArray(int size) {
            return new SamplePage[size];
        }
    };

    public SamplePage(long itemId, int viewType, CharSequence pageTitle, String tagName) {
        super(itemId, viewType, pageTitle, tagName);
    }

    private SamplePage(Bundle data) {
        super(data);
    }
}

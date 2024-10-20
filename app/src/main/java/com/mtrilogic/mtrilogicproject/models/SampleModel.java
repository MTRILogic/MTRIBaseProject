package com.mtrilogic.mtrilogicproject.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.abstracts.ModelableCreator;

public class SampleModel extends Modelable {
    public static final Creator<SampleModel> CREATOR = new ModelableCreator<>() {
        @Override
        protected SampleModel createFromData(Bundle data) {
            return new SampleModel(data);
        }

        @Override
        public SampleModel[] newArray(int size) {
            return new SampleModel[size];
        }
    };

    private static final String TITLE = "title", CONTENT = "content";

    private CharSequence content;
    private String title;

    public SampleModel(long itemId, String title, CharSequence content) {
        super(itemId, 1, true);
        this.content = content;
        this.title = title;
    }

    public CharSequence getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    private SampleModel(Bundle data) {
        super(data);
    }

    @Override
    protected void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        content = data.getCharSequence(CONTENT);
        title = data.getString(TITLE);
    }

    @Override
    protected void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putCharSequence(CONTENT, content);
        data.putString(TITLE, title);
    }
}

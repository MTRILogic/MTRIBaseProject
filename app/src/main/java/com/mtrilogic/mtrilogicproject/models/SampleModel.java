package com.mtrilogic.mtrilogicproject.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.logic.abstracts.Modelable;
import com.mtrilogic.logic.abstracts.ModelableCreator;

public class SampleModel extends Modelable {
    public static final Creator<SampleModel> CREATOR = new ModelableCreator<>() {
        @NonNull
        @Override
        protected SampleModel createFromData(Bundle data) {
            return new SampleModel(data);
        }

        @NonNull
        @Override
        public SampleModel[] newArray(int size) {
            return new SampleModel[size];
        }
    };

    private static final String TITLE = "title", CONTENT = "content", ICON = "icon";

    private CharSequence content;
    private String title;
    private int icon;

    public SampleModel(long itemId) {
        super(itemId, 0, true);
    }

    private SampleModel(Bundle data) {
        super(data);
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public void onRestoreFromData(@NonNull Bundle data) {
        super.onRestoreFromData(data);
        content = data.getCharSequence(CONTENT);
        title = data.getString(TITLE);
        icon = data.getInt(ICON);
    }

    @Override
    public void onSaveToData(@NonNull Bundle data) {
        super.onSaveToData(data);
        data.putCharSequence(CONTENT, content);
        data.putString(TITLE, title);
        data.putInt(ICON, icon);
    }
}

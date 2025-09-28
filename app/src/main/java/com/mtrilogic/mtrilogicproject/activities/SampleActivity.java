package com.mtrilogic.mtrilogicproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mtrilogic.logic.abstracts.Paginable;
import com.mtrilogic.logic.classes.Listable;
import com.mtrilogic.mtrilogicproject.MainActivity;
import com.mtrilogic.mtrilogicproject.R;
import com.mtrilogic.mtrilogicproject.fragments.SampleInflatableFragment;
import com.mtrilogic.mtrilogicproject.fragments.SampleRecyclableFragment;
import com.mtrilogic.mtrilogicproject.pages.SamplePage;
import com.mtrilogic.ui.abstracts.Fragmentable;
import com.mtrilogic.ui.adapters.FragmentableAdapter;

public class SampleActivity extends AppCompatActivity implements FragmentableAdapter.Listener, Fragmentable.Listener {
    private static final String LISTABLE = "listable";

    private Listable<Paginable> paginableListable;
    private FragmentableAdapter adapter;
    private ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        if (savedInstanceState != null) {
            paginableListable = new Listable<>(savedInstanceState, LISTABLE, Paginable.class);
        } else {
            paginableListable = new Listable<>();
            addFragment();
        }

        adapter = new FragmentableAdapter(paginableListable, getSupportFragmentManager(), this);

        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        paginableListable.saveToData(outState, LISTABLE);
        super.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public Fragmentable<? extends Paginable> getFragmentable(int viewType) {
        if (viewType == MainActivity.RECYCLABLE) {
            return new SampleRecyclableFragment();
        }
        return new SampleInflatableFragment();
    }

    @NonNull
    @Override
    public FragmentableAdapter getFragmentableAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public Listable<Paginable> getPaginableListable() {
        return paginableListable;
    }

    @NonNull
    @Override
    public ViewPager getViewPager() {
        return pager;
    }

    @Override
    public void onMakeToast(String line, boolean background) {
        if (background) {
            runOnUiThread(() -> makeToast(line));
        } else {
            makeToast(line);
        }
    }

    private void addFragment() {
        Intent intent = getIntent();
        if (intent != null) {
            int viewType = intent.getIntExtra(MainActivity.VIEW_TYPE, MainActivity.INFLATABLE);
            Paginable paginable = new SamplePage(0, viewType, "Inflatable Fragment", "");
            if (paginableListable.getList().add(paginable)) {
                paginableListable.setIdx(1);
            }
        }
    }

    private void makeToast(String line) {
        Toast.makeText(this, line, Toast.LENGTH_LONG).show();
    }
}

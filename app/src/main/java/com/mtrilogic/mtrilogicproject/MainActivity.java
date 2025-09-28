package com.mtrilogic.mtrilogicproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mtrilogic.mtrilogicproject.activities.SampleActivity;
import com.mtrilogic.mtrilogicproject.utils.MainUtil;

public class MainActivity extends AppCompatActivity {
    public static final String VIEW_TYPE = "viewType";
    public static final int INFLATABLE = 0;
    public static final int RECYCLABLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainUtil.initializeDrawables(this);

        Button btnRecyclable = findViewById(R.id.btnRecyclable);
        btnRecyclable.setOnClickListener(v -> startSampleActivity(RECYCLABLE));

        Button btnInflatable = findViewById(R.id.btnInflatable);
        btnInflatable.setOnClickListener(v -> startSampleActivity(INFLATABLE));
    }

    private void startSampleActivity(int viewType) {
        Intent intent = new Intent(this, SampleActivity.class);
        intent.putExtra(VIEW_TYPE, viewType);
        startActivity(intent);
    }
}
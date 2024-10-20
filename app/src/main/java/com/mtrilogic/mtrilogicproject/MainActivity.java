package com.mtrilogic.mtrilogicproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mtrilogic.mtrilogicproject.models.SampleListableModel;
import com.mtrilogic.mtrilogicproject.models.SampleModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(v -> loadData());
    }

    private void loadData() {
        Intent intent = new Intent(this, SampleActivity.class);
        intent.putExtra("model", createSampleListableModel());
        startActivity(intent);
        finish();
    }

    private SampleListableModel createSampleListableModel() {
        SampleListableModel listableModel = new SampleListableModel(0);
        long idx = 0;
        for (int i = 0; i < 20; i++) {
            SampleModel model = new SampleModel(idx,"title" + idx, "Hola mundo!");
            if (listableModel.getListable().getList().add(model)) {
                idx++;
            }
        }
        if (idx > 0) {
            listableModel.getListable().setIdx(idx);
        }
        return listableModel;
    }
}
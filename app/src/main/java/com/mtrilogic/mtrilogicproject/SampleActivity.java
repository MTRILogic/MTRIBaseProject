package com.mtrilogic.mtrilogicproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mtrilogic.mtrilogicproject.models.SampleListableModel;
import com.mtrilogic.mtrilogicproject.models.SampleModel;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        TextView lblContent = findViewById(R.id.lblContent);
        Intent intent = getIntent();
        SampleListableModel listableModel = intent.getParcelableExtra("model");
        if (listableModel != null) {
            int size = listableModel.getListable().getList().size();
            if (size > 0) {
                makeToast("Listable items: " + size);
                for (SampleModel model: listableModel.getListable().getList()) {
                    lblContent.append(getString(R.string.item, model.getTitle(), model.getContent()));
                    lblContent.append("\r\n");
                }
            } else {
                makeToast("listable no contiene items");
            }
        } else {
            makeToast("model es nulo");
        }
    }

    private void makeToast(String line) {
        Toast.makeText(this, line, Toast.LENGTH_LONG).show();
    }
}

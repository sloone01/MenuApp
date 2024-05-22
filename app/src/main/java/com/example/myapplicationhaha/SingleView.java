package com.example.myapplicationhaha;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SingleView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);
        ListData data =(ListData) getIntent().getSerializableExtra("meal");
        TextView name = findViewById(R.id.name),
        ing=findViewById(R.id.ingredients),steps=findViewById(R.id.steps),
                duration=findViewById(R.id.duration);

        name.setText(data.name);
        steps.setText(data.desc);
        ing.setText(data.ingredients);
        duration.setText(data.time);
        ImageView img = findViewById(R.id.img);
        img.setImageResource(data.image);
    }
}
package com.example.mobasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AppartmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appartments);

        findViewById(R.id.suitApart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppartmentsActivity.this,MainActivity.class));
            }
        });

        findViewById(R.id.fullApart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppartmentsActivity.this,MainActivity.class));
            }
        });

        findViewById(R.id.simiFullApart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppartmentsActivity.this,MainActivity.class));
            }
        });
    }
}
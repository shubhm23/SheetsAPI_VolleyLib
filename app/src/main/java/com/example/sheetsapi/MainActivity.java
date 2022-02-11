package com.example.sheetsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addItem, listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItem = findViewById(R.id.addItem);
        listItem = findViewById(R.id.listItem);
        addItem.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddItem.class);
            startActivity(intent);
        });
        listItem.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ListItem.class);
            startActivity(intent);
        });
    }

}
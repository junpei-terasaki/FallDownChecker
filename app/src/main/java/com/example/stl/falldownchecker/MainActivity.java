package com.example.stl.falldownchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity{
    String level = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void check (View view){
        Intent intent = new Intent(this, FallDownChecker.class);
        startActivity(intent);
    }

    public void entry (View view){
        Intent intent = new Intent(this, Entry.class);
        startActivity(intent);
    }

    public void training (View view){
        Intent g_intent = getIntent();
        level = g_intent.getStringExtra("level");
        Intent intent = new Intent(this, Exercise.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}

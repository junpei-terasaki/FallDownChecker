package com.example.stl.falldownchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity{

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

    public void video (View view){
        Intent intent = new Intent(this, Exercise.class);
        startActivity(intent);
    }
}

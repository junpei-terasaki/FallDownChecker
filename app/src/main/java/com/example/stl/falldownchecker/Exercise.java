package com.example.stl.falldownchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by STL on 2018/03/19.
 */

public class Exercise extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

    }

    public void List(View view){
        Intent intent = new Intent(this, ExerciseList.class);
        startActivity(intent);
    }
}

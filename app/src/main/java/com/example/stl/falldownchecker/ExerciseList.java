package com.example.stl.falldownchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by STL on 2018/03/19.
 */

public class ExerciseList extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_list);

    }

    public void E_result(View view){
        Intent intent = new Intent(this, ExerciseResult.class);
        startActivity(intent);
    }
}

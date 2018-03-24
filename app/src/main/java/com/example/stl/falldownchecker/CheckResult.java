package com.example.stl.falldownchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by STL on 2018/03/19.
 */

public class CheckResult extends AppCompatActivity{

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_result);

        TextView level = findViewById(R.id.level);
        Intent intent = getIntent();
        int point = intent.getIntExtra("Point", 0);
        String level_str;
        if(point >= 5){
            level_str = "高い";
            level.setText(level_str);
        }else if (point == 4){
            level_str = "やや高い";
            level.setText((level_str));
        }else{
            level_str = "低い";
            level.setText((level_str));

        }

    }

    public void list(View view){
        Intent intent = new Intent(this, ExerciseList.class);
        startActivity(intent);
    }
}
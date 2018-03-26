package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by STL on 2018/03/19.
 */

public class CheckResult extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_result);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getReadableDatabase();

        final TextView level = findViewById(R.id.level);
        final String level_str;

        Intent intent = getIntent();
        int point = intent.getIntExtra("Point", 0);

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
        Button btn_exercise = (Button) findViewById(R.id.btn_exercise);
        btn_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues insertValues = new ContentValues();
                insertValues.put("level", level_str.toString());
                long id = db.insert("result", level_str.toString(), insertValues);
            }
        });

    }

    public void exercise(View view){
        Intent intent = new Intent(this, Exercise.class);
        startActivity(intent);
    }
}
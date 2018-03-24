package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by STL on 2018/03/19.
 */

public class CheckResult extends AppCompatActivity{

    MyOpenHelper helper = new MyOpenHelper(this);
    SQLiteDatabase db = helper.getReadableDatabase();

    Cursor c = db.query("result", new String[] {"level"}, null,
            null, null, null, null);
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

        ContentValues insertValues = new ContentValues();
        insertValues.put("level", level_str);
        long id = db.insert("result", level_str, insertValues);
    }

    public void exercise(View view){
        Intent intent = new Intent(this, Exercise.class);
        startActivity(intent);
    }
}
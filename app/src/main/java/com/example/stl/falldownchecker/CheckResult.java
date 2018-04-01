package com.example.stl.falldownchecker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
    String level_str;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_result);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final TextView level = findViewById(R.id.level);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        Intent intent = getIntent();
        int point = intent.getIntExtra("Point", 0);

        if(point >= 5){
            level_str = "高い";
        }else if (point == 4){
            level_str = "やや高い";
        }else{
            level_str = "低い";
        }
        ContentValues values = new ContentValues();
        values.put("level", level_str);
        db.insert("m_person",null, values);

        Cursor mPerson_c= db.query("m_person", new String[] { "level" }, null,
                null, null, null, null);
        count = 0;
        while(mPerson_c.moveToNext()) {
            level.setText(mPerson_c.getString(0));
            count++;
        }

    }

    public void main(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}